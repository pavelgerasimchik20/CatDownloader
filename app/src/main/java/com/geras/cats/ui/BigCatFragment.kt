package com.geras.cats.ui

import android.app.DownloadManager
import android.net.Uri
import android.os.Bundle
import android.os.Environment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import coil.load
import com.geras.cats.data.Cat
import com.geras.cats.databinding.FragmentBigCatBinding
import java.io.File


class BigCatFragment : Fragment() {

    private var _binding: FragmentBigCatBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentBigCatBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val url = arguments?.getString(CAT_URL) ?: ""
        binding.fullCatScreen.load(url)
        binding.addToGalleryBtn.setOnClickListener {
            downloadImageToGallery(url, url)
        }
    }

    private fun downloadImageToGallery(filename: String, downloadUrlOfImage: String) {
        try {
            val downloadUri = Uri.parse(downloadUrlOfImage)
            val request = DownloadManager.Request(downloadUri)
            request.setAllowedNetworkTypes(DownloadManager.Request.NETWORK_WIFI or DownloadManager.Request.NETWORK_MOBILE)
                .setAllowedOverRoaming(false)
                .setTitle(filename)
                .setMimeType("image/jpeg")
                .setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED)
                .setDestinationInExternalPublicDir(
                    Environment.DIRECTORY_PICTURES,
                    File.separator + filename + ".jpg"
                )
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    companion object {

        private const val CAT_URL = "CAT_URL"

        fun newInstance(cat: Cat): BigCatFragment {
            val fragment = BigCatFragment()
            val args = Bundle()
            args.putString(CAT_URL, cat.url)
            fragment.arguments = args
            return fragment
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}
