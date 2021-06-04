package com.test.radioplayer.network

import android.content.Context
import com.test.radioplayer.BuildConfig
import com.test.radioplayer.R
import com.test.radioplayer.utils.Utils
import okhttp3.JavaNetCookieJar
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.net.ConnectException
import java.net.CookieManager
import java.net.CookiePolicy
import java.util.concurrent.TimeUnit

object WsWaClient {

   private const val BASE_URL = "https://api.itmwpb.com/nowplaying/v3/"
   private const val timeOutConnect = 60L
   private const val timeOutRead = 60L
   private const val timeOutWrite = 60L

    private lateinit var okHttpClient: OkHttpClient

    fun getWsWaClient(context: Context, webUrl: String= BASE_URL): WebServices {

        val cookieManager = CookieManager()
        cookieManager.setCookiePolicy(CookiePolicy.ACCEPT_ALL)

        val client = OkHttpClient().newBuilder()
                .connectTimeout(timeOutConnect, TimeUnit.SECONDS)
                .readTimeout(timeOutRead, TimeUnit.SECONDS)
                .writeTimeout(timeOutWrite, TimeUnit.SECONDS)
                .cookieJar(JavaNetCookieJar(cookieManager))

        if (BuildConfig.DEBUG) {
            val loggingInterceptor = HttpLoggingInterceptor()
            loggingInterceptor.level = HttpLoggingInterceptor.Level.BODY
            client.addInterceptor(loggingInterceptor)
        }

        client.addInterceptor { chain ->
            if (Utils.isNetworkAvailable(context)) {
                chain.proceed(chain.request())
            } else {
                throw ConnectException(context.getString(R.string.em_no_network))
            }
        }
        okHttpClient = client.build()

        val retrofit = Retrofit.Builder()
                .baseUrl(webUrl)
                .client(okHttpClient)
                .addConverterFactory(GsonConverterFactory.create())
                .build()

        return retrofit.create(WebServices::class.java)
    }

    fun getOkHttpClient() : OkHttpClient?{
        return okHttpClient
    }
}