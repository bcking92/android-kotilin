package com.example.myfirstapplication

import okhttp3.HttpUrl
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Response
import okhttp3.logging.HttpLoggingInterceptor
import java.io.IOException
import java.util.concurrent.TimeUnit


object OkHttp3Manager{
    private val ALL_TIMEOUT = 10L

    private var okHttpClient: OkHttpClient

    private  class HeaderSettingInterceptor : Interceptor {

        @Throws(IOException::class)
        override fun intercept(chain: Interceptor.Chain): Response {

            val chainRequest = chain.request()
            /*
             * Response Type과 SK 개발자Key을 등록한다
             */
            val request = chainRequest.newBuilder().apply{
                addHeader("Accept", "application/json")
            }.build()

            return chain.proceed(request)
        }
    }
    init{
        /*
         * HTTP 상태를 알아보기 위해 Http Login Interceptor를 설정한다
         */
        val httpLogging = HttpLoggingInterceptor()
        httpLogging.level = HttpLoggingInterceptor.Level.BASIC

        /*
         * OkHttp3 통신에 Interceptor와 Socket(I/O) 환경을 설정
         */
        okHttpClient = OkHttpClient().newBuilder().apply {

            addInterceptor(httpLogging)
            addInterceptor(HeaderSettingInterceptor())
            connectTimeout(ALL_TIMEOUT, TimeUnit.SECONDS)
            writeTimeout(ALL_TIMEOUT, TimeUnit.SECONDS)
            readTimeout(ALL_TIMEOUT, TimeUnit.SECONDS)

        }.build()
    }
    fun getOkHttpClient() = okHttpClient

    const val SCHEME = "https"
    const val HOST = "6uqljnm1pb.execute-api.ap-northeast-2.amazonaws.com"

    fun makeHttpURL(targetURL:String) : HttpUrl {
        return HttpUrl.Builder().apply{
            scheme(SCHEME)
            host(HOST)
            addPathSegments(targetURL)
        }.build()
    }
}