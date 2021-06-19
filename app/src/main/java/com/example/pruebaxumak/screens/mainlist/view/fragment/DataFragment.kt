package com.example.pruebaxumak.screens.mainlist.view.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast

import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import com.example.pruebaxumak.databinding.DataFragmentBinding
import com.example.pruebaxumak.screens.mainlist.viewmodel.DataViewModel
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.pruebaxumak.screens.mainlist.model.DataResponse
import com.example.pruebaxumak.screens.mainlist.view.adapter.DataAdapter
import com.example.pruebaxumak.utils.autoCleared
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DataFragment : Fragment(), DataAdapter.DataItemListener {

    private var binding: DataFragmentBinding by autoCleared()
    private val viewModel: DataViewModel by viewModels()
    private lateinit var adapter: DataAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupRecyclerView()
        setupObservers()
    }

    private fun setupRecyclerView() {
        adapter = DataAdapter(this)
        binding.dataRv.layoutManager = LinearLayoutManager(requireContext())
        binding.dataRv.adapter = adapter
    }

    private fun setupObservers() {
        viewModel.userLiveData.observe(viewLifecycleOwner, Observer { getList ->
            binding.progressBar.visibility = View.GONE
            if (getList != null) {
                adapter.setItems(ArrayList(getList))
            } else {
                Toast.makeText(requireContext(), "Something went wrong", Toast.LENGTH_SHORT).show()
            }
        })

        viewModel.makeApiCall()
    }

    override fun onClickedFavorite(dataResponse: DataResponse) {
            viewModel.logicFavorite(dataResponse)
    }
}
