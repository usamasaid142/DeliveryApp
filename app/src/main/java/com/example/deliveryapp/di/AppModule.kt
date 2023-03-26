package com.example.deliveryapp.di
import android.util.Log
import com.example.deliveryapp.BuildConfig
import com.example.deliveryapp.api.ApiService
import com.example.deliveryapp.utils.Constans.BASE_URL
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.net.SocketTimeoutException
import javax.inject.Singleton
@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun getGson(): Gson {
        return GsonBuilder().serializeNulls().setLenient().create()
    }



    private val logging: HttpLoggingInterceptor =
        HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)


    private fun getOkHttpClient(): OkHttpClient {


        val okHttpClientBuilder = OkHttpClient.Builder()

        if (BuildConfig.DEBUG) {

            okHttpClientBuilder
                .addInterceptor(logging)

        }

        okHttpClientBuilder.addInterceptor { chain ->

            try {
                val originalRequest = chain.request()
                val requestBuilder =
                    originalRequest.newBuilder()

                requestBuilder.addHeader("Authorization", "")

                chain.proceed(requestBuilder.build())
            } catch (exception: SocketTimeoutException) {
                exception.printStackTrace()
                chain.proceed(chain.request())
            }

        }

        return okHttpClientBuilder.build()
    }


    @Provides
    @Singleton
    fun provideRetrofitInstance(
        // Potential dependencies of this type
    ): ApiService {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(getOkHttpClient())
            .build()
            .create(ApiService::class.java)
    }

}