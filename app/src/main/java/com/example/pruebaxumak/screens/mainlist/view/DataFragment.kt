package com.example.pruebaxumak.screens.mainlist.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.view.isVisible

import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.example.pruebaxumak.databinding.DataFragmentBinding
import com.example.pruebaxumak.screens.mainlist.viewmodel.DataViewModel
import androidx.lifecycle.observe
import androidx.paging.LoadState
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.pruebaxumak.screens.mainlist.model.MainResponse
import com.example.pruebaxumak.screens.mainlist.view.adapter.DataAdapter
import com.example.pruebaxumak.utils.autoCleared
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DataFragment : Fragment(), DataAdapter.OnItemClickListener {

    private var binding: DataFragmentBinding by autoCleared()
    private val viewModel: DataViewModel by viewModels()
    val adapter = DataAdapter(this)

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
        binding.dataRv.layoutManager = LinearLayoutManager(requireContext())
        binding.dataRv.adapter = adapter

        adapter.addLoadStateListener { loadState ->
            binding.apply {
                progressBar.isVisible = loadState.source.refresh is LoadState.Loading
                dataRv.isVisible = loadState.source.refresh is LoadState.NotLoading
                dataRv.isVisible = !(loadState.source.refresh is LoadState.NotLoading &&
                        loadState.append.endOfPaginationReached &&
                        adapter.itemCount < 1)
            }
        }
    }

    private fun setupObservers() {
        viewModel.dataList.observe(viewLifecycleOwner) {
            adapter.submitData(viewLifecycleOwner.lifecycle, it)
        }
    }

    override fun onItemClick(data: MainResponse) {
        Toast.makeText(requireContext(), data.name, Toast.LENGTH_SHORT).show()
    }
}
