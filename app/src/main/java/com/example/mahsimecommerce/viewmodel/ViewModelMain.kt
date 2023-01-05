package com.example.mahsimecommerce.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mahsimecommerce.RetrofitClient
import com.example.mahsimecommerce.models.ChildProduct
import com.example.mahsimecommerce.models.ChildSlider
import com.example.mahsimecommerce.repository.Repository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.lang.Exception

class ViewModelMain(private val repository: Repository = Repository(RetrofitClient.instance))
    : ViewModel() {

        private var _liveDataVar = MutableLiveData<List<ChildSlider>>()
        val liveDataVar : LiveData<List<ChildSlider>>
            get() = _liveDataVar

        init {
            getCharacter()
        }

    private fun getCharacter(){
        viewModelScope.launch(Dispatchers.IO) {
            try {
                val client = repository.getData()
                _liveDataVar.postValue(client.results)
                Log.d("viewModelScope","viewModelScope -> $_liveDataVar")

            }catch (e: Exception){
                Log.d("Exception","Exception -> $e")

            }
        }
    }

    private var _liveDataVar2 = MutableLiveData<List<ChildProduct>>()
    val liveDataVar2 : LiveData<List<ChildProduct>>
        get() = _liveDataVar2

    init {
        getCharacter2()
    }

    private fun getCharacter2(){
        viewModelScope.launch(Dispatchers.IO) {
            try {
                val client = repository.getProductData()
                _liveDataVar2.postValue(client.results)
                Log.d("viewModelScope","viewModelScope -> $_liveDataVar2")

            }catch (e: Exception){
                Log.d("Exception","Exception -> $e")
            }
        }
    }

}