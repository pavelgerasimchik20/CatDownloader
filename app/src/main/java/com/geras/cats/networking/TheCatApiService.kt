package com.geras.cats.networking

import com.geras.cats.data.Cat
import retrofit2.Call
import retrofit2.http.GET

interface TheCatApiService {
    // Наверное функция должна быть suspend?
    // Если добавить CoroutineCallAdapterFactory, можно будет возвращать сразу List<Cat>.
    @GET("images/search?limit=19")
    fun getCats(): Call<List<Cat>>
}