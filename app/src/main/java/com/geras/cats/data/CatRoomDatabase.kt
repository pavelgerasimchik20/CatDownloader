package com.geras.cats.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [Cat::class], version = 1)
abstract class CatRoomDatabase : RoomDatabase() {
    abstract fun dao(): CatsDao

    companion object {
        @Volatile
        private var INSTANCE: CatRoomDatabase? = null

        fun getDatabase(
            context: Context,
        ): CatRoomDatabase {

            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    CatRoomDatabase::class.java,
                    "cats_bd"
                )
                 .build()
                INSTANCE = instance
                instance
            }
        }

    }
}
