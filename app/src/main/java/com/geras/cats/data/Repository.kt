package com.geras.cats.data

import androidx.annotation.WorkerThread
import com.geras.cats.networking.TheCatApiService
import com.geras.cats.networking.theCatApiService
import kotlinx.coroutines.flow.Flow

class Repository(private val dao: CatsDao) {

    val cats: Flow<List<Cat>> = dao.getImagesOfCats()
    private val service: TheCatApiService = theCatApiService

    @WorkerThread
    suspend fun updateCatsFromRemote() {
        val cats = service.getCats().execute().body() ?: emptyList()
        dao.deleteAll()
        dao.insertAll(cats)
    }

    @WorkerThread
    suspend fun delete(cat: Cat) {
        dao.delete(cat)
    }

    @WorkerThread
    suspend fun insert(cat: Cat) {
        dao.insert(cat)
    }
}
