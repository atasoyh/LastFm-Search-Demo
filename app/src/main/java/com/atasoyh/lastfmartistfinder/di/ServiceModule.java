package com.atasoyh.lastfmartistfinder.di;

import android.content.Context;

import com.atasoyh.lastfmartistfinder.BuildConfig;
import com.atasoyh.lastfmartistfinder.R;
import com.atasoyh.lastfmartistfinder.interactor.LastFmApi;
import com.atasoyh.lastfmartistfinder.util.RxErrorHandlingCallAdapterFactory;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.IOException;
import java.lang.annotation.Annotation;

import javax.inject.Named;
import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import okhttp3.HttpUrl;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Converter;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.jackson.JacksonConverterFactory;

/**
 * Created by atasoyh on 09/07/2017.
 */
@Module
public class ServiceModule {

    @Provides
    @Singleton
    public OkHttpClient provideLoggingCapableAndApiKeyHttpClient(@Named("apikey") final String apiKey) {
        HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
        logging.setLevel(BuildConfig.DEBUG ? HttpLoggingInterceptor.Level.BODY : HttpLoggingInterceptor.Level.NONE);

        Interceptor interceptor = new Interceptor() {
            @Override
            public Response intercept(Interceptor.Chain chain) throws IOException {
                Request original = chain.request();
                HttpUrl originalHttpUrl = original.url();
                HttpUrl url = originalHttpUrl.newBuilder()
                        .addQueryParameter("api_key", apiKey)
                        .addQueryParameter("format","json")
                        .build();
                // Request customization: add request headers
                Request.Builder requestBuilder = original.newBuilder()
                        .url(url);
                Request request = requestBuilder.build();
                return chain.proceed(request);
            }
        };
        return new OkHttpClient.Builder()
                .addInterceptor(logging).addInterceptor(interceptor)
                .build();
    }

    @Named("apikey")
    @Provides
    public String provideApiKey(Context context) {
        return context.getString(R.string.lastfm_apikey);
    }


    @Named("url")
    @Provides
    public String provideApiUrl() {
        return LastFmApi.ENDPOINT;
    }


    @Provides
    @Singleton
    public Retrofit provideRetrofit(OkHttpClient okHttpClient, @Named("url") String url) {
        return new Retrofit.Builder()
                .baseUrl(url).addConverterFactory(JacksonConverterFactory.create())
                .addCallAdapterFactory(RxErrorHandlingCallAdapterFactory.create())
                .client(okHttpClient)
                .build();
    }

    @Provides
    @Singleton
    public LastFmApi provideAPI(Retrofit retrofit) {
        return retrofit.create(LastFmApi.class);
    }
}
