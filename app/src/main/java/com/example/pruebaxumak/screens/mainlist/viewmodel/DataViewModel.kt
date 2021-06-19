package com.example.pruebaxumak.screens.mainlist.viewmodel

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.*
import com.example.pruebaxumak.screens.mainlist.model.DataResponse
import com.example.pruebaxumak.screens.mainlist.repository.DataRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch


class DataViewModel @ViewModelInject constructor(
    private val repository: DataRepository
) : ViewModel() {

    private val _userLiveData = MutableLiveData<List<DataResponse>>()
    val userLiveData: LiveData<List<DataResponse>> = _userLiveData

    private val _stateData = MutableLiveData<Boolean>()
    val stateData: LiveData<Boolean> = _stateData


    fun getData() {
        viewModelScope.launch(Dispatchers.Main) {
            val list= repository.getList()
            repository.insertAll(list)
            _userLiveData.value = list
        }
    }

    fun logicFavorite(data: DataResponse) {
        if (!data.State) {
            data.State=true
            viewModelScope.launch(Dispatchers.Main) {
                repository.saveFavorite(data)
            }
        } else {
            data.State=false
            viewModelScope.launch(Dispatchers.Main) {
                repository.saveFavorite(data)
            }
        }
        _stateData.value=true
    }

    val getAllOderData: LiveData<List<DataResponse>> get() = repository.getAllFavoritesOrder()

}
