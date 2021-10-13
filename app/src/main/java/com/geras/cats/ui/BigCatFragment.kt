package com.geras.cats.ui

import android.Manifest.permission.WRITE_EXTERNAL_STORAGE
import android.content.pm.PackageManager
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import coil.load
import com.geras.cats.CatApplication
import com.geras.cats.data.Cat
import com.geras.cats.databinding.FragmentBigCatBinding


class BigCatFragment : Fragment() {

    private var _binding: FragmentBigCatBinding? = null
    private val binding get() = _binding!!
    private val viewModel: BigCatFragmentViewModel by viewModels {
        ViewModelFactory(((activity as MainActivity).application as CatApplication).repository)
    }

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
        val id = arguments?.getString(CAT_ID) ?: ""
        binding.fullCatScreen.load(url)
        binding.addToGalleryBtn.setOnClickListener {
            if (url != null) {
                if (isWritePermissionGranted()) {
                    viewModel.downloadImage(url, requireContext())
                } else {
                    askPermissions()
                }
            }
        }
    }

    private fun isWritePermissionGranted(): Boolean {
        return ContextCompat.checkSelfPermission(
            requireActivity(),
            WRITE_EXTERNAL_STORAGE
        ) == PackageManager.PERMISSION_GRANTED
    }

    private fun askPermissions() {
        ActivityCompat.requestPermissions(
            requireActivity(),
            arrayOf(WRITE_EXTERNAL_STORAGE),
            REQUEST_CODE
        )
    }

    companion object {

        private const val CAT_URL = "CAT_URL"
        private const val CAT_ID = "CAT_ID"
        private const val REQUEST_CODE = 100

        fun newInstance(cat: Cat): BigCatFragment {
            val fragment = BigCatFragment()
            val args = Bundle()
            args.putString(CAT_URL, cat.url)
            args.putString(CAT_ID, cat.id)
            fragment.arguments = args
            return fragment
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}
