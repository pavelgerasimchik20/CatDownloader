package com.geras.cats.common

import com.geras.cats.retrofit.RetrofitClient
import com.geras.cats.retrofit.RetrofitServices

object Common {
    private val BASE_URL = "https://api.thecatapi.com/"
    val retrofitService: RetrofitServices
        get() = RetrofitClient.getClient(BASE_URL).create(RetrofitServices::class.java)
}