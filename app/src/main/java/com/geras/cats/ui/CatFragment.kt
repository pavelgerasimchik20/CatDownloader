package com.geras.cats.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.GridLayoutManager
import com.geras.cats.CatApplication
import com.geras.cats.databinding.FragmentCatBinding

class CatFragment : Fragment() {

    private var _binding: FragmentCatBinding? = null
    private val binding get() = _binding!!
    private val adapter = Adapter { openNewFragment() }

    private fun openNewFragment() {
        val activity = activity as? MainActivity
        /*activity?.openBigCatFragment()
    */
    }

    private val viewModel: CatViewModel by viewModels {
        ViewModelFactory((requireActivity().application as CatApplication).repository)
    }


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentCatBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.recyclerview.layoutManager = GridLayoutManager(requireContext(), 3)
        binding.recyclerview.adapter = adapter

        viewModel.cats.observe(viewLifecycleOwner) { cats ->
            adapter.updateCats(cats)
        }
    }

    companion object {

        fun newInstance(): CatFragment {
            val fragment = CatFragment()
            return fragment
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
