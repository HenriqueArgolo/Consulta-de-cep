package com.example.myapplication.viewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.myapplication.infra.RetrofitClient
import com.example.myapplication.model.Adress
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class MainViewModel: ViewModel() {

    private val _adressInfo = MutableLiveData<Adress?>()
    val adressInfo: MutableLiveData<Adress?> get() = _adressInfo

    init {
        _adressInfo.value = null
    }

    suspend fun fetchAdress(cep: String){
        viewModelScope.apply {
            val adressData = getCityinfo(cep)
            _adressInfo.postValue(adressData)
        }
    }


    suspend fun getCityinfo(cep: String): Adress? {
        return withContext(Dispatchers.IO) {
            try {
                val response = RetrofitClient().apiService.getLocation(cep)
                response.body()
            } catch (e: Exception) {
                e.printStackTrace()
                null
            }
        }
    }
}