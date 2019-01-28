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
import java.util.concurrent.TimeUnit;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
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
    private String host;
    private Integer port;
    private Boolean loggingEnabled;
    private String[] headers;

    public HttpClient(Retrofit retrofit, OkHttpClient okHttpClient,
                      HttpLoggingInterceptor loggingInterceptor, Long timeout, String host,
                      Integer port, Boolean loggingEnabled, String[] headers) {
        loggingEnabled(loggingEnabled);
        timeout(timeout);
        host(host);
        port(port);
        loggingInterceptor(loggingInterceptor);
        okHttpClient(okHttpClient);
        retrofit(retrofit);
        headers(headers);
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

    public HttpClient host(String host) {
        if (host == null) {
            host = "xjliao.me";
        }
        this.host = host;

        return this;
    }

    public HttpClient port(Integer port) {
        if (port == null) {
            port = 80;
        }
        this.port = port;

        return this;
    }

    public HttpClient headers(String[] headers) {
    	if (headers == null) {
    		headers= new String[]{};
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

    public HttpClient okHttpClient(OkHttpClient okHttpClient) {
        this.okHttpClient = okHttpClient;
        return this;
    }

    private OkHttpClient getOkHttpClient() {
        if (this.okHttpClient == null) {
            this.okHttpClient = new OkHttpClient.Builder()
                    .connectTimeout(this.timeout, TimeUnit.SECONDS)
                    .readTimeout(this.timeout, TimeUnit.SECONDS)
                    .writeTimeout(this.timeout, TimeUnit.SECONDS)
                    .retryOnConnectionFailure(true)
                    .addInterceptor(this.getLoggingInterceptor())
                    .addInterceptor(new Interceptor() {
                        @Override
                        public Response intercept(Chain chain) throws IOException {
                            Request original = chain.request();
	                        Request.Builder requestBuilder = original.newBuilder();

                            for (int i =0, n = headers.length; i < n; ++i) {
                            	if (i != 0 && i % 2 == 0) {
                            	    continue;
                                }
                                requestBuilder.addHeader(headers[i], headers[i+1]);
                            }

                            Request request = requestBuilder
                                    .header("Content-Encoding", "gzip")
                                    .method(original.method(), original.body())
                                    .build();
                            return chain.proceed(request);
                        }
                    }).build();
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
            this.retrofit = new Retrofit.Builder()
                    .baseUrl("http://" + this.host + ":" + this.port)
                    .client(this.getOkHttpClient())
                    .addConverterFactory(JacksonConverterFactory.create(mapper))
                    .addConverterFactory(SimpleXmlConverterFactory.create())
                    .build();
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
        private String host;
        private Integer port;
        private Boolean loggingEnabled;
        private String[] headers;

        public Builder() {

        }

        public HttpClient build() {
            return new HttpClient(retrofit, okHttpClient, logInterceptor, timeout, host, port, loggingEnabled, headers);
        }

        public HttpClient.Builder retrofit(Retrofit retrofit) {
            if (retrofit == null) {
                throw new IllegalArgumentException("Retrofit must not be null.");
            }
            if (this.retrofit != null) {
                throw new IllegalStateException("Retrofit already set.");
            }
            this.retrofit = retrofit;
            return this;
        }

        public HttpClient.Builder okHttpClient(OkHttpClient okHttpClient) {
            if (okHttpClient == null) {
                throw new IllegalArgumentException("OkHttpClient must not be null.");
            }
            if (this.okHttpClient != null) {
                throw new IllegalStateException("OkHttpClient already set.");
            }
            this.okHttpClient = okHttpClient;
            return this;
        }

        public HttpClient.Builder logInterceptor(HttpLoggingInterceptor logInterceptor) {
            if (logInterceptor == null) {
                throw new IllegalArgumentException("HttpLoggingInterceptor must not be null.");
            }
            if (this.logInterceptor != null) {
                throw new IllegalStateException("HttpLoggingInterceptor already set.");
            }
            this.logInterceptor = logInterceptor;
            return this;
        }

        public HttpClient.Builder timeout(Long timeout) {
            if (timeout == null) {
                throw new IllegalArgumentException("Timeout must not be null.");
            }
            if (this.timeout != null) {
                throw new IllegalStateException("Timeout already set.");
            }
            this.timeout = timeout;
            return this;
        }

        public HttpClient.Builder host(String host) {
            if (host == null) {
                throw new IllegalArgumentException("Host must not be null.");
            }
            if (this.host != null) {
                throw new IllegalStateException("Host already set.");
            }
            this.host = host;
            return this;
        }

        public HttpClient.Builder port(Integer port) {
            if (port == null) {
                throw new IllegalArgumentException("Port must not be null.");
            }
            if (this.port != null) {
                throw new IllegalStateException("Port already set.");
            }
            this.port = port;
            return this;
        }


        public Builder loggingEnabled(Boolean loggingEnabled) {
            if (loggingEnabled == null) {
                throw new IllegalArgumentException("LoggingEnabled must not be null.");
            }
            if (this.loggingEnabled != null) {
                throw new IllegalStateException("LoggingEnabled already set.");
            }
            this.loggingEnabled = loggingEnabled;
            return this;
        }

        public Builder headers(String[] headers) {
            if (loggingEnabled != null && headers.length % 2 != 0) {
                throw new IllegalArgumentException("Headers unaviable, it's a string array that must be a key with a value.");
            }
            if (this.headers != null) {
                throw new IllegalStateException("Headers already set.");
            }
            this.headers = headers;
            return this;
        }
    }
}