/*
 * Copyright (c) 2017 xjliao.me created by xjliao
 * ProjectName: xjl
 * ModuleName: xjlib
 * FileName: HttpClient.java
 * ClassName: HttpClient
 * LastModified: 11/28/17 5:16 PM
 */

package me.xjliao.xjlib.net;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import me.xjliao.xjlib.BuildConfig;
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

    private static long TIMEOUT = 20;

    public static final String BASE_URL = "http://" + BuildConfig.HOST + ":" + BuildConfig.PORT + "/";

    private static HttpClient INSTANCE = null;

    private static Retrofit RETROFIT = null;

    public static HttpClient getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new HttpClient();
            // Http log interceptor
            HttpLoggingInterceptor logInterceptor = new HttpLoggingInterceptor();

            // Set log level
            if (BuildConfig.LOG_DEBUG) {
                logInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
            } else {
                logInterceptor.setLevel(HttpLoggingInterceptor.Level.NONE);
            }

            // Http client
            OkHttpClient httpClient = new OkHttpClient.Builder()
                    .connectTimeout(TIMEOUT, TimeUnit.SECONDS)
                    .readTimeout(TIMEOUT, TimeUnit.SECONDS)
                    .writeTimeout(TIMEOUT, TimeUnit.SECONDS)
                    .retryOnConnectionFailure(true)
                    .addInterceptor(logInterceptor)
                    .addInterceptor(new Interceptor() {
                        @Override
                        public Response intercept(Chain chain) throws IOException {
                            Request original = chain.request();
                            Request request = original.newBuilder()
                                    .header("Content-Encoding", "gzip")
                                    .method(original.method(), original.body())
                                    .build();

                            return chain.proceed(request);
                        }
                    }).build();

            // Build retrofit
            RETROFIT = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .client(httpClient)
                    .addConverterFactory(JacksonConverterFactory.create())
                    .addConverterFactory(SimpleXmlConverterFactory.create())
                    .build();
        }

        return INSTANCE;
    }

    // Create service instance
    public <T> T createService(Class<T> T) {
        return RETROFIT.create(T);
    }

}