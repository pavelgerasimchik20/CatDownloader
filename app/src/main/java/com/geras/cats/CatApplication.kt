package com.geras.cats

import android.app.Application
import com.geras.cats.data.CatRoomDatabase
import com.geras.cats.data.Repository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.SupervisorJob

class CatApplication : Application() {

    val database by lazy { CatRoomDatabase.getDatabase(this) }
    val repository by lazy { Repository(database.dao()) }
}