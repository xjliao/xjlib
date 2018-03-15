/*
 * Copyright (c) 2017 xjliao.me created by xjliao
 * ProjectName: xjl
 * ModuleName: xjlib
 * FileName: BaseXWebActivity.java
 * ClassName: BaseXWebActivity
 * LastModified: 2/6/18 5:13 PM
 */

package me.xjliao.xjlib.base;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.webkit.JsPromptResult;
import android.webkit.JsResult;
import android.webkit.WebChromeClient;
import android.webkit.WebResourceError;
import android.webkit.WebResourceRequest;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.EditText;

import java.net.URISyntaxException;

import butterknife.BindView;
import me.xjliao.xjlib.R;
import me.xjliao.xjlib.R2;
import me.xjliao.xjlib.common.Constants;

/**
 * Created by xjliao on 1/30/18.
 */

public class BaseXWebActivity extends BaseActivity {

    public static final String LOAD_TYPE = "LOAD_TYPE";

    public static final int LOAD_URL = 0;

    public static final int LOAD_DATA = 1;

    public static final int LOAD_DATA_WITH_BASE_URL = 2;

    private int loadTypeFlag = -1;

    @BindView(R2.id.webView)
    WebView webView;

    private String url;

    private String data;

    private BaseXWebListener baseXWebListener;

    @Override
    public void setupComponent() {

    }

    @Override
    protected int getLayout() {
        return R.layout.activity_base_xweb;
    }

    @Override
    protected void initViews() {
        WebSettings settings = webView.getSettings();
        settings.setSupportZoom(true);
        settings.setDisplayZoomControls(false);
        settings.setJavaScriptEnabled(true);
        settings.setRenderPriority(WebSettings.RenderPriority.HIGH);
        settings.setAllowFileAccess(true);
        settings.setNeedInitialFocus(true);
        settings.setLoadsImagesAutomatically(true);
        settings.setBlockNetworkImage(true);
        settings.setCacheMode(WebSettings.LOAD_NO_CACHE);
        webView.setWebViewClient(new WebClient());
        webView.setWebChromeClient(new ChromeClient());

        switch (loadTypeFlag) {
            case BaseXWebActivity.LOAD_URL:
                webView.loadUrl(url);
                break;
            case LOAD_DATA:
                webView.loadData(data, "text/html", "UTF-8");
                break;
            case LOAD_DATA_WITH_BASE_URL:
                webView.loadDataWithBaseURL(null, data, "text/html", "UTF-8", null);
                break;
            default:
                webView.loadUrl(url);
                break;
        }
    }

    @Override
    protected void initListeners() {

    }

    @Override
    protected void initAdapters() {

    }

    @Override
    protected void initData() {
        loadTypeFlag = getIntent().getIntExtra(LOAD_TYPE, -1);
        url = getIntent().getStringExtra(Constants.URL);
        data = getIntent().getStringExtra(Constants.DATA);
    }

    @Override
    public void destroyView() {

    }

    public class WebClient extends WebViewClient {

        @Override
        public boolean shouldOverrideUrlLoading(WebView view, String url) {
            Context context = webView.getContext();
            if(url.startsWith("tel:")&&call(context,url)){
                return true;
            }
            if(url.startsWith("Intent:")&& startIntent(context,url)){
                return true;
            }
            view.loadUrl(url);
            return true;
        }

        private boolean call(Context context, String tel){
            Intent intent = new Intent(Intent.ACTION_DIAL, Uri.parse(tel.replaceAll("//","")));
            context.startActivity(intent);
            return true;
        }

        private boolean startIntent(Context context, String intentStr){
            Intent intent = null;
            try {
                intent = Intent.parseUri(intentStr, Intent.FLAG_ACTIVITY_NEW_TASK);
                context.startActivity(intent);
                return true;
            } catch (URISyntaxException e) {
                e.printStackTrace();
            }
            return false;
        }

        @Override
        public void onPageStarted(WebView view, String url, Bitmap favicon) {
            view.getSettings().setBlockNetworkImage(true);
            super.onPageStarted(view, url, favicon);
        }

        @Override
        public void onPageFinished(WebView view, String url) {
            super.onPageFinished(view, url);
            view.getSettings().setBlockNetworkImage(false);
        }

        @SuppressWarnings("deprecation")
        @Override
        public void onReceivedError(WebView view, int errorCode, String description, String failingUrl) {
            view.loadUrl("file:///android_asset/network_error.html");
        }

        @TargetApi(android.os.Build.VERSION_CODES.M)
        @Override
        public void onReceivedError(WebView view, WebResourceRequest req, WebResourceError rerr) {
            // Redirect to deprecated method, so you can use it in all SDK versions
            onReceivedError(view, rerr.getErrorCode(), rerr.getDescription().toString(), req.getUrl().toString());
        }
    }

    public class ChromeClient extends WebChromeClient {
        @Override
        public boolean onJsAlert(WebView view, String url, String message, final JsResult result) {
            AlertDialog.Builder builder = new AlertDialog.Builder(view.getContext());
            builder.setTitle(view.getResources().getString(R.string.app_name));
            builder.setMessage(message);
            builder.setPositiveButton("确定", new DialogInterface.OnClickListener(){

                @Override
                public void onClick(DialogInterface dialog, int which) {
                    result.confirm();
                }
            });
            builder.setNegativeButton("取消", new DialogInterface.OnClickListener(){

                @Override
                public void onClick(DialogInterface dialog, int which) {
                    result.cancel();
                }
            });
            builder.show();
            return super.onJsAlert(view, url, message, result);
        }

        @Override
        public boolean onJsConfirm(WebView view, String url, String message, final JsResult result) {
            AlertDialog.Builder builder = new AlertDialog.Builder(view.getContext());
            builder.setTitle(view.getResources().getString(R.string.app_name));
            builder.setMessage(message);
            builder.setPositiveButton("确定", new DialogInterface.OnClickListener(){

                @Override
                public void onClick(DialogInterface dialog, int which) {
                    result.confirm();
                }
            });
            builder.show();
            return super.onJsConfirm(view, url, message, result);
        }

        @Override
        public boolean onJsPrompt(WebView view, String url, String message, final String defaultValue, final JsPromptResult result) {
            final EditText inputServer = new EditText(view.getContext());
            inputServer.setFocusable(true);
            AlertDialog.Builder builder = new AlertDialog.Builder(view.getContext());
            builder.setTitle(view.getResources().getString(R.string.app_name));
            builder.setView(inputServer);
            inputServer.setText(message);
            inputServer.setHint(defaultValue);
            builder.setPositiveButton("确定", new DialogInterface.OnClickListener(){

                @Override
                public void onClick(DialogInterface dialog, int which) {
                    String data = inputServer.getText().toString();
                    data = "".equals(data) ?  defaultValue : data;
                    result.confirm(data);
                }
            });
            builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    result.cancel();
                }
            });
            builder.show();
            return super.onJsPrompt(view, url, message, defaultValue, result);
        }

    }

    interface BaseXWebListener {
        void load(String url);

        void loadData(String data, String mimeType, String encoding);

        void loadDataWithBaseURL(String baseUrl, String data, String mimeType, String encoding, String historyUrl);
    }

}
