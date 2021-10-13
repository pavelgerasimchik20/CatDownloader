package com.geras.cats.networking

import android.app.DownloadManager
import android.content.Context
import android.net.Uri
import android.os.Environment

private const val DELIMITER = "/"

class CatDownloadManager {
    fun downloadImage(url: String, context: Context) {
        val filename = url.substringAfterLast(DELIMITER)
        val request = DownloadManager.Request(Uri.parse(url))
            .setTitle(filename)
            .setDestinationInExternalPublicDir(Environment.DIRECTORY_DOWNLOADS, filename)
            .setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED)
        (context.getSystemService(Context.DOWNLOAD_SERVICE) as DownloadManager)
            .enqueue(request)
    }
}