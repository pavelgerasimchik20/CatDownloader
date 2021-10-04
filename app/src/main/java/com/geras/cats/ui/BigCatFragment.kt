package com.geras.cats.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import coil.load
import com.geras.cats.data.Cat
import com.geras.cats.databinding.FragmentBigCatBinding

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
