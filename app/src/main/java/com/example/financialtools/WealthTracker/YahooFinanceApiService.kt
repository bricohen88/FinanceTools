package com.example.financialtools.WealthTracker

import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Query

private const val BASE_URL = "https://yfapi.net/"

val interceptor: HttpLoggingInterceptor = HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)
val client: OkHttpClient = OkHttpClient.Builder().addInterceptor(interceptor).build()



private val moshi = Moshi.Builder()
    .add(KotlinJsonAdapterFactory())
    .build()

private val retrofit = Retrofit.Builder()
    .baseUrl(BASE_URL)
    .client(client)
    .addConverterFactory(MoshiConverterFactory.create(moshi))
    .build()


interface YahooFinanceApiService {

    @Headers("accept: application/json","x-api-key: ")
    @GET("v6/finance/quote?region=US&lang=en")
    suspend fun getQuote(@Query("symbols", encoded = true) symbol: String): StockData
}

object YahooFinanceApi {
    val retrofitService: YahooFinanceApiService by lazy { retrofit
        .create(YahooFinanceApiService::class.java)}
}