package com.mgrsys.blankproject.application.di

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.mgrsys.blankproject.model.datasource.rest.UsersRestClient
import com.mgrsys.blankproject.model.datasource.rest.config.ServerEndpoint
import com.mgrsys.blankproject.model.datasource.rest.config.SimpleServerEndpoint
import com.mgrsys.blankproject.model.datasource.rest.constant.DateConst
import com.mgrsys.blankproject.model.datasource.rest.constant.RestOptions
import com.mgrsys.blankproject.model.repository.session.SessionRepository
import dagger.Module
import dagger.Provides
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

/**
 * Developed by Magora Team (magora-systems.com)
 * 2018
 *
 * @author Viktor Zemtsov
 */
@Module
class RetrofitModule {

  @Singleton
  @Provides
  fun provideUsersRestClient(retrofit: Retrofit): UsersRestClient {
    return retrofit.create(UsersRestClient::class.java)
  }

//  @Singleton
//  @Provides
//  fun provideNotificationRestClient(retrofit: Retrofit): NotificationRestClient {
//    return retrofit.create(NotificationRestClient::class.java)
//  }

  @Singleton
  @Provides
  fun provideRetrofit(endpoint: ServerEndpoint, gson: Gson, client: OkHttpClient): Retrofit {
    return Retrofit.Builder()
        .baseUrl(endpoint.url())
        .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
        .addConverterFactory(GsonConverterFactory.create(gson))
        .client(client)
        .build()
  }

  @Singleton
  @Provides
  fun provideServerEndpoint(): ServerEndpoint {
    return SimpleServerEndpoint()
  }

  @Singleton
  @Provides
  fun provideGson(): Gson {
    return GsonBuilder()
        .setDateFormat(DateConst.PATTERN_FULL_DATE)
        .create()
  }

  @Singleton
  @Provides
  fun provideOkHttpClient(loggingInterceptor: HttpLoggingInterceptor,
                          authInterceptor: Interceptor): OkHttpClient {
    return OkHttpClient.Builder()
        .addInterceptor(loggingInterceptor)
        .addInterceptor(authInterceptor)
        .retryOnConnectionFailure(true)
        .connectTimeout(RestOptions.TIMEOUT_CONNECTION_SECONDS, TimeUnit.SECONDS)
        .readTimeout(RestOptions.TIMEOUT_READ_SECONDS, TimeUnit.SECONDS)
        .writeTimeout(RestOptions.TIMEOUT_WRITE_SECONDS, TimeUnit.SECONDS)
        .build()
  }

  @Singleton
  @Provides
  fun provideHttpLoggingInterceptor(): HttpLoggingInterceptor {
    return HttpLoggingInterceptor().apply {
      level = HttpLoggingInterceptor.Level.BODY
    }
  }

  @Singleton
  @Provides
  fun provideAuthInterceptor(sessionRepository: SessionRepository): Interceptor {
    return Interceptor { chain ->
      if (sessionRepository.isAuthorized()) {
        val originalRequest = chain.request()
        var modifiedRequest = originalRequest

        val header = originalRequest.header(RestOptions.HEADER_KEY_MARKER)
        if (header == RestOptions.HEADER_VALUE_MARKER_NON_AUTH) {
          /*Do nothing*/
        } else {
          modifiedRequest = originalRequest.newBuilder()
              .header(RestOptions.HEADER_KEY_AUTH, RestOptions.HEADER_VALUE_BEARER + sessionRepository.sessionInfo().accessToken)
              .build()
        }

        chain.proceed(modifiedRequest)
      } else {
        val originalRequest = chain.request()
        chain.proceed(originalRequest)
      }
    }
  }
}
