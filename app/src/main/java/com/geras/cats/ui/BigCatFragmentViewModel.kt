package com.geras.cats.ui

import android.content.Context
import androidx.lifecycle.ViewModel
import com.geras.cats.networking.CatDownloadManager

class BigCatFragmentViewModel : ViewModel() {
    private val manager: CatDownloadManager = CatDownloadManager()
    fun downloadImage(url: String, context: Context) {
        manager.downloadImage(url, context)
    }
}