/*
 * Copyright (c) 2017 xjliao.me created by xjliao
 * ProjectName: xjl
 * ModuleName: xjlib
 * FileName: HttpClient.java
 * ClassName: HttpClient
 * LastModified: 12/5/17 10:45 AM
 */

package me.xjliao.xjlib.net;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.databind.MapperFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Collections;
import java.util.concurrent.TimeUnit;

import okhttp3.CipherSuite;
import okhttp3.ConnectionSpec;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.TlsVersion;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.jackson.JacksonConverterFactory;
import retrofit2.converter.simplexml.SimpleXmlConverterFactory;

/**
 * Created by xjliao on 1/5/16.
 */
public class HttpClient {
	static volatile HttpClient singleton = null;

	private Retrofit retrofit;
	private OkHttpClient okHttpClient;
	private HttpLoggingInterceptor loggingInterceptor;
	private Long timeout;
	private Boolean loggingEnabled;
	private String baseURL;
	private String[] headers;
	private Boolean ssl;
	private ConnectionSpec connectionSpec;

	public HttpClient(Retrofit retrofit, OkHttpClient okHttpClient,
	                  HttpLoggingInterceptor loggingInterceptor, Long timeout, String baseURL,
	                  Boolean loggingEnabled, String[] headers, Boolean ssl,
	                  ConnectionSpec connectionSpec) {
		loggingEnabled(loggingEnabled);
		timeout(timeout);
		baseURL(baseURL);
		loggingInterceptor(loggingInterceptor);
		okHttpClient(okHttpClient);
		retrofit(retrofit);
		headers(headers);
		ssl(ssl);
		connectionSpec(connectionSpec);
	}

	public static HttpClient getInstance() {
		if (singleton == null) {
			synchronized (HttpClient.class) {
				if (singleton == null) {
					singleton = new Builder().build();
				}
			}
		}

		return singleton;
	}

	public HttpClient timeout(Long timeout) {
		if (timeout == null) {
			timeout = 30L;
		}

		this.timeout = timeout;
		return this;
	}

	public HttpClient baseURL(String baseURL) {
		if (baseURL == null) {
			baseURL = "https://xjliao.com";
		}

		this.baseURL = baseURL;
		return this;
	}

	public HttpClient headers(String[] headers) {
		if (headers == null) {
			headers = new String[]{};
		}

		this.headers = headers;
		return this;
	}

	public HttpClient loggingInterceptor(HttpLoggingInterceptor loggingInterceptor) {
		this.loggingInterceptor = loggingInterceptor;
		return this;
	}

	private HttpLoggingInterceptor getLoggingInterceptor() {
		if (this.loggingInterceptor == null) {
			this.loggingInterceptor = new HttpLoggingInterceptor();

			// Set log level
			if (this.isLoggingEnabled()) {
				this.loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
			} else {
				this.loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.NONE);
			}
		}

		return this.loggingInterceptor;
	}

	public HttpClient ssl(Boolean ssl) {
		if (ssl == null) {
			ssl = false;
		}

		this.ssl = ssl;
		return this;
	}

	public HttpClient connectionSpec(ConnectionSpec connectionSpec) {
		if (ssl && connectionSpec == null) {
			connectionSpec = new ConnectionSpec.Builder(ConnectionSpec.MODERN_TLS)
					.tlsVersions(TlsVersion.TLS_1_2)
					.cipherSuites(
							CipherSuite.TLS_ECDHE_RSA_WITH_AES_256_GCM_SHA384,
							CipherSuite.TLS_DHE_RSA_WITH_AES_256_GCM_SHA384,
							CipherSuite.TLS_ECDHE_RSA_WITH_AES_256_CBC_SHA384)
					.build();
			this.connectionSpec = connectionSpec;
		}
		return this;
	}

	public HttpClient okHttpClient(OkHttpClient okHttpClient) {
		this.okHttpClient = okHttpClient;
		return this;
	}

	private OkHttpClient getOkHttpClient() {
		if (this.okHttpClient == null) {
			OkHttpClient.Builder builder = new OkHttpClient.Builder()
					.connectTimeout(this.timeout, TimeUnit.SECONDS)
					.readTimeout(this.timeout, TimeUnit.SECONDS)
					.writeTimeout(this.timeout, TimeUnit.SECONDS)
					.retryOnConnectionFailure(true)
					.addInterceptor(this.getLoggingInterceptor())
					.addInterceptor(new Interceptor() {
						@Override
						public Response intercept(Chain chain) throws IOException {
							Request original = chain.request();
							Request.Builder requestBuilder = original.newBuilder().header("Content-Encoding", "gzip");

							for (int i = 0, n = headers.length; i < n; ++i) {
								requestBuilder.header(headers[i].split(":")[0],
										headers[i].split(":")[1]);
							}

							Request request = requestBuilder
									.method(original.method(), original.body())
									.build();
							return chain.proceed(request);
						}
					});

			if (this.connectionSpec != null) {
				builder.connectionSpecs(Collections.singletonList(this.connectionSpec));
			}

			this.okHttpClient = builder.build();
		}

		return this.okHttpClient;
	}

	public HttpClient retrofit(Retrofit retrofit) {
		this.retrofit = retrofit;
		return this;
	}

	private Retrofit getRetrofit() {
		if (this.retrofit == null) {
			ObjectMapper mapper = new ObjectMapper();
			mapper.configure(MapperFeature.AUTO_DETECT_GETTERS, false);
			mapper.configure(MapperFeature.AUTO_DETECT_SETTERS, false);
			mapper.configure(MapperFeature.AUTO_DETECT_IS_GETTERS, false);
			mapper.setDateFormat(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"));
			mapper.setVisibilityChecker(mapper.getSerializationConfig()
					.getDefaultVisibilityChecker()
					.withFieldVisibility(JsonAutoDetect.Visibility.ANY));
			Retrofit.Builder builder = new Retrofit.Builder()
					.baseUrl(this.baseURL)
					.client(this.getOkHttpClient())
					.addConverterFactory(JacksonConverterFactory.create(mapper))
					.addConverterFactory(SimpleXmlConverterFactory.create());
			this.retrofit = builder.build();
		}

		return this.retrofit;
	}

	public HttpClient loggingEnabled(Boolean loggingEnabled) {
		this.loggingEnabled = loggingEnabled;
		return this;
	}

	private Boolean isLoggingEnabled() {
		if (this.loggingEnabled == null) {
			this.loggingEnabled = false;
		}

		return this.loggingEnabled;
	}

	public <T> T createService(Class<T> T) {
		return this.getRetrofit().create(T);
	}

	public static class Builder {
		private Retrofit retrofit;
		private OkHttpClient okHttpClient;
		private HttpLoggingInterceptor logInterceptor;
		private Long timeout;
		private String baseURL;
		private Boolean loggingEnabled;
		private String[] headers;
		private Boolean ssl;
		private ConnectionSpec connectionSpec;

		public Builder() {

		}

		public HttpClient build() {
			return new HttpClient(retrofit, okHttpClient, logInterceptor, timeout, baseURL, loggingEnabled, headers, ssl, connectionSpec);
		}

		public HttpClient.Builder retrofit(Retrofit retrofit) {
			this.retrofit = retrofit;
			return this;
		}

		public HttpClient.Builder okHttpClient(OkHttpClient okHttpClient) {
			this.okHttpClient = okHttpClient;
			return this;
		}

		public HttpClient.Builder logInterceptor(HttpLoggingInterceptor logInterceptor) {
			this.logInterceptor = logInterceptor;
			return this;
		}

		public HttpClient.Builder timeout(Long timeout) {
			this.timeout = timeout;
			return this;
		}

		public HttpClient.Builder baseURL(String baseURL) {
			this.baseURL = baseURL;
			return this;
		}

		public Builder loggingEnabled(Boolean loggingEnabled) {
			this.loggingEnabled = loggingEnabled;
			return this;
		}

		public Builder headers(String[] headers) {
			if (headers != null && headers.length > 0) {
				for (int i = 0, n = headers.length; i < n; ++i) {
					if (headers[i].split(":").length != 2) {
						throw new IllegalArgumentException("Headers unaviable, it's a string array that must be a key with a value. value=" + headers[i]);
					}
				}
			}

			this.headers = headers;
			return this;
		}

		public Builder ssl(Boolean ssl) {
			this.ssl = ssl;
			return this;
		}

		public Builder connectionSpec(ConnectionSpec connectionSpec) {
			this.connectionSpec = connectionSpec;
			return this;
		}
	}
}