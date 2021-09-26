package com.geras.cats.data

import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@androidx.room.Dao
interface CatsDao {

    @Query("SELECT * FROM cats ")
    fun getImagesOfCats(): Flow<List<Cat>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(cat: Cat)

    @Delete
    suspend fun delete(cat: Cat)

    @Query("DELETE FROM cats")
    suspend fun deleteAll()
}
