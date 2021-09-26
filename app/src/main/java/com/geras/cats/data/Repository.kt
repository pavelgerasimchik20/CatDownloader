package com.geras.cats.data

import androidx.annotation.WorkerThread
import kotlinx.coroutines.flow.Flow

class Repository(private val dao: CatsDao) {

    val cats: Flow<List<Cat>> = dao.getImagesOfCats()

    @WorkerThread
    suspend fun delete(cat: Cat){
        dao.delete(cat)
    }

    @WorkerThread
    suspend fun insert(cat: Cat) {
        dao.insert(cat)
    }
}
