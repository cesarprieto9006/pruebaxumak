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


    fun makeApiCall(){
        viewModelScope.launch(Dispatchers.Main){
            _userLiveData.value= repository.getList()
        }
    }

    fun logicFavorite(data:DataResponse){
        if(data.State) {
            viewModelScope.launch(Dispatchers.Main) {
                repository.saveFavorite(data)
            }
        }else {
            viewModelScope.launch(Dispatchers.Main) {
                repository.deleteFavorite(data.char_id)
            }
        }
    }
}
