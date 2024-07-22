package com.example.myapplication.viewModel
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.myapplication.MyAplication
import com.example.myapplication.infra.RetrofitClient
import com.example.myapplication.model.Adress
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MainViewModel: ViewModel() {
    val dataBase = MyAplication.database.getAdressDao()

    private val _adressInfo = MutableLiveData<Adress?>()
    val adressInfo: MutableLiveData<Adress?> get() = _adressInfo

    init {
        _adressInfo.value = null
    }

    suspend fun fetchAdress(cep: String){
        viewModelScope.apply {
            val adressData = dataBase.getAdress(cep)
            if( adressData != null) {
                _adressInfo.postValue(adressData)
            }else{
                getCityInfo(cep)
                val newAdressData = dataBase.getAdress(cep)
                _adressInfo.postValue(newAdressData)
            }



        }
    }


    suspend fun getCityInfo(cep: String) {
        CoroutineScope(Dispatchers.IO).launch {
            try {
                val response = RetrofitClient().apiService.getLocation(cep)
                if (response.isSuccessful) {
                    response.body()?.let {
                        dataBase.saveAdress(it)
                    }
                }
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }

}