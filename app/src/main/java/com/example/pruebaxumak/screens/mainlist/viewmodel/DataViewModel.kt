package com.example.pruebaxumak.screens.mainlist.viewmodel

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import com.example.pruebaxumak.screens.mainlist.repository.DataRepository

class DataViewModel @ViewModelInject constructor(
    repository: DataRepository
) : ViewModel() {

    val dataList = repository.getList().cachedIn(viewModelScope)

}
