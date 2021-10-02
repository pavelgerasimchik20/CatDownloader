package com.geras.cats.ui

import android.app.AlertDialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import com.geras.cats.common.Common
import com.geras.cats.databinding.FragmentCatBinding
import com.geras.cats.model.Cat
import com.geras.cats.retrofit.RetrofitServices
import dmax.dialog.SpotsDialog
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class CatFragment : Fragment() {

    private var _binding: FragmentCatBinding? = null
    private val binding get() = _binding!!
    private var adapter = Adapter {}
    lateinit var mService: RetrofitServices
    lateinit var dialog: AlertDialog

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
        mService = Common.retrofitService
        binding.recyclerview.setHasFixedSize(true)
        binding.recyclerview.layoutManager = GridLayoutManager(requireContext(), 3)
        binding.recyclerview.adapter = adapter

        dialog = SpotsDialog.Builder().setCancelable(true).setContext(requireContext()).build()

        getList()
    }

    private fun getList() {
        dialog.show()
        mService.getListOfCats().enqueue(object : Callback<List<Cat>> {
            override fun onResponse(call: Call<List<Cat>>, response: Response<List<Cat>>) {
                adapter = Adapter {}
                adapter.notifyDataSetChanged()
                binding.recyclerview.adapter = adapter

                dialog.dismiss()
            }

            override fun onFailure(call: Call<List<Cat>>, t: Throwable) {
            }
        })
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    companion object {

        fun newInstance(): CatFragment {
            val fragment = CatFragment()
            val args = Bundle()
            fragment.arguments = args
            return fragment
        }
    }
}
