package me.xjliao.xjlib.auth

import android.content.Context
import android.content.SharedPreferences
import android.hardware.fingerprint.FingerprintManager
import android.os.Build
import android.os.Bundle
import android.os.Handler
import android.security.keystore.KeyGenParameterSpec
import android.security.keystore.KeyPermanentlyInvalidatedException
import android.security.keystore.KeyProperties
import android.support.annotation.RequiresApi
import android.util.Log
import android.view.View
import android.widget.Toast
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_authentication.*
import me.xjliao.xjlib.R
import me.xjliao.xjlib.base.BaseActivity
import java.io.IOException
import java.security.*
import java.security.cert.CertificateException
import javax.crypto.*

@RequiresApi(Build.VERSION_CODES.M)
class AuthenticationActivity : BaseActivity(),
        FingerprintAuthenticationDialogFragment.Callback {

    private lateinit var keyStore: KeyStore
    private lateinit var keyGenerator: KeyGenerator
    private lateinit var sharedPreferences: SharedPreferences
    private var username: String? = null
    private var avatarUrl: String? = null

    override fun setupComponent() {
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        setTheme(R.style.AuthenticationTheme)
        super.onCreate(savedInstanceState)
        setupKeyStoreAndKeyGenerator()

        val defaultCipher: Cipher = setupCipher()
        sharedPreferences = getSharedPreferences(AUTH_PREFERENCES, Context.MODE_PRIVATE)
        setFingerPrintButtons(defaultCipher)

        // Delay to show fingerprint dialog
        Handler().postDelayed({
            runOnUiThread {
                click_to_unlock_image_view.performClick()
            }
        }, 500L)
    }

    /**
     * Enables or disables purchase buttons and sets the appropriate click listeners.
     *
     * @param defaultCipher the default cipher, used for the purchase button
     */
    private fun setFingerPrintButtons(defaultCipher: Cipher) {
        createKey(DEFAULT_KEY_NAME)
        createKey(KEY_NAME_NOT_INVALIDATED, false)
        click_to_unlock_image_view.run {
            click_to_unlock_image_view.setOnClickListener(FingerPrintButtonClickListener(defaultCipher,
                    DEFAULT_KEY_NAME))
        }
    }

    /**
     * Sets up KeyStore and KeyGenerator
     */
    private fun setupKeyStoreAndKeyGenerator() {
        try {
            keyStore = KeyStore.getInstance(ANDROID_KEY_STORE)
        } catch (e: KeyStoreException) {
            throw RuntimeException("Failed to get an instance of KeyStore", e)
        }

        try {
            keyGenerator = KeyGenerator.getInstance(KeyProperties.KEY_ALGORITHM_AES, ANDROID_KEY_STORE)
        } catch (e: Exception) {
            when (e) {
                is NoSuchAlgorithmException,
                is NoSuchProviderException ->
                    throw RuntimeException("Failed to get an instance of KeyGenerator", e)
                else -> throw e
            }
        }
    }

    /**
     * Sets up default cipher and a non-invalidated cipher
     */
    private fun setupCipher(): Cipher {
        val defaultCipher: Cipher
        try {
            val cipherString = "${KeyProperties.KEY_ALGORITHM_AES}/${KeyProperties.BLOCK_MODE_CBC}/${KeyProperties.ENCRYPTION_PADDING_PKCS7}"
            defaultCipher = Cipher.getInstance(cipherString)
        } catch (e: Exception) {
            when (e) {
                is NoSuchAlgorithmException,
                is NoSuchPaddingException ->
                    throw RuntimeException("Failed to get an instance of Cipher", e)
                else -> throw e
            }
        }
        return defaultCipher
    }

    override fun getLayout(): Int {
        return R.layout.activity_authentication
    }

    override fun initViews() {
        avatar_image_view.clipToOutline = true
        Picasso.with(this).load(avatarUrl).placeholder(R.drawable.default_auth_avatar)
                .error(R.drawable.default_auth_avatar).into(avatar_image_view)
        username_text_view.text = username
    }

    override fun initListeners() {
        signin_other_account_text_view.setOnClickListener{
            setResult(AUTH_SIGN_IN_OTHER_ACCOUNT_RESULT_CODE)
            this@AuthenticationActivity.finish()
        }
    }


    override fun onBackPressed() {
        return
    }

    override fun initAdapters() {
    }

    override fun initData() {
        username = intent.getStringExtra(AUTH_USERNAME)
        avatarUrl = intent.getStringExtra(AUTH_AVATAR)
    }

    override fun destroyView() {
    }

    /**
     * Initialize the [Cipher] instance with the created key in the [createKey] method.
     *
     * @param keyName the key name to init the cipher
     * @return `true` if initialization succeeded, `false` if the lock screen has been disabled or
     * reset after key generation, or if a fingerprint was enrolled after key generation.
     */
    private fun initCipher(cipher: Cipher, keyName: String): Boolean {
        try {
            keyStore.load(null)
            cipher.init(Cipher.ENCRYPT_MODE, keyStore.getKey(keyName, null) as SecretKey)
            return true
        } catch (e: Exception) {
            when (e) {
                is KeyPermanentlyInvalidatedException -> return false
                is KeyStoreException,
                is CertificateException,
                is UnrecoverableKeyException,
                is IOException,
                is NoSuchAlgorithmException,
                is InvalidKeyException -> throw RuntimeException("Failed to init Cipher", e)
                else -> throw e
            }
        }
    }

    /**
     * Proceed with the purchase operation
     *
     * @param withFingerprint `true` if the purchase was made by using a fingerprint
     * @param crypto the Crypto object
     */
    override fun onAuthenticated(withFingerprint: Boolean,
                                  crypto: FingerprintManager.CryptoObject?,
                                  usePassword: Boolean?) {
        if (withFingerprint) {
            // If the user authenticated with fingerprint, verify using cryptography and then show
            // the confirmation message.
            if (crypto != null) {
                tryEncrypt(crypto.cipher)
            }
        } else {
            // Authentication happened with backup password. Just show the confirmation message.
            setResult(AUTH_SIGN_IN_WITH_PASSWORD)
            finish()
        }
    }

    /**
     * Tries to encrypt some data with the generated key from [createKey]. This only works if the
     * user just authenticated via fingerprint.
     */
    private fun tryEncrypt(cipher: Cipher) {
        try {
            cipher.doFinal(SECRET_MESSAGE.toByteArray())
            finish()
        } catch (e: Exception) {
            when (e) {
                is BadPaddingException,
                is IllegalBlockSizeException -> {
                    Toast.makeText(this, "Failed to encrypt the data with the generated key. "
                            + "Retry the purchase", Toast.LENGTH_LONG).show()
                    Log.e(TAG, "Failed to encrypt the data with the generated key. ${e.message}")
                }
                else -> throw e
            }
        }
    }

    /**
     * Creates a symmetric key in the Android Key Store which can only be used after the user has
     * authenticated with a fingerprint.
     *
     * @param keyName the name of the key to be created
     * @param invalidatedByBiometricEnrollment if `false` is passed, the created key will not be
     * invalidated even if a new fingerprint is enrolled. The default value is `true` - the key will
     * be invalidated if a new fingerprint is enrolled.
     */
    override fun createKey(keyName: String, invalidatedByBiometricEnrollment: Boolean) {
        // The enrolling flow for fingerprint. This is where you ask the user to set up fingerprint
        // for your flow. Use of keys is necessary if you need to know if the set of enrolled
        // fingerprints has changed.
        try {
            keyStore.load(null)

            val keyProperties = KeyProperties.PURPOSE_ENCRYPT or KeyProperties.PURPOSE_DECRYPT
            val builder = KeyGenParameterSpec.Builder(keyName, keyProperties)
                    .setBlockModes(KeyProperties.BLOCK_MODE_CBC)
                    .setUserAuthenticationRequired(true)
                    .setEncryptionPaddings(KeyProperties.ENCRYPTION_PADDING_PKCS7)

            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                builder.setInvalidatedByBiometricEnrollment(invalidatedByBiometricEnrollment)
            }
            keyGenerator.run {
                init(builder.build())
                generateKey()
            }
        } catch (e: Exception) {
            when (e) {
                is NoSuchAlgorithmException,
                is InvalidAlgorithmParameterException,
                is CertificateException,
                is IOException -> throw RuntimeException(e)
                else -> throw e
            }
        }
    }

    private inner class FingerPrintButtonClickListener internal constructor(
            internal var cipher: Cipher,
            internal var keyName: String
    ) : View.OnClickListener {

        override fun onClick(view: View) {
            val fragment = FingerprintAuthenticationDialogFragment()
            fragment.setCryptoObject(FingerprintManager.CryptoObject(cipher))
            fragment.setCallback(this@AuthenticationActivity)

            // Set up the crypto object for later, which will be authenticated by fingerprint usage.
            if (initCipher(cipher, keyName)) {

                // Show the fingerprint dialog. The user has the option to use the fingerprint with
                // crypto, or can fall back to using a server-side verified password.
                val useFingerprintPreference = sharedPreferences
                        .getBoolean(getString(R.string.use_fingerprint_to_authenticate_key), true)
                if (useFingerprintPreference) {
                    fragment.setStage(Stage.FINGERPRINT)
                } else {
                    fragment.setStage(Stage.PASSWORD)
                }
            } else {
                // This happens if the lock screen has been disabled or or a fingerprint was
                // enrolled. Thus, show the dialog to authenticate with their password first and ask
                // the user if they want to authenticate with a fingerprint in the future.
                fragment.setStage(Stage.NEW_FINGERPRINT_ENROLLED)
            }
            fragment.show(fragmentManager, DIALOG_FRAGMENT_TAG)
        }
    }

    companion object {
        private val ANDROID_KEY_STORE = "AndroidKeyStore"
        private val DIALOG_FRAGMENT_TAG = "AUTHENTICATION_DIALOG_FRAGMENT"
        private val KEY_NAME_NOT_INVALIDATED = "密钥未失效"
        private val SECRET_MESSAGE = "非常私密信息"
        private val TAG = AuthenticationActivity::class.java.simpleName
    }

}