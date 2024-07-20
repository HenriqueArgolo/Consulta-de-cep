package com.example.myapplication.viewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.myapplication.MyAplication
import com.example.myapplication.infra.RetrofitClient
import com.example.myapplication.model.Adress
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.coroutineScope

class MainViewModel: ViewModel() {
    val dataBase = MyAplication.database.getAdressDao()

    private val _adressInfo = MutableLiveData<Adress?>()
    val adressInfo: MutableLiveData<Adress?> get() = _adressInfo

    init {
        _adressInfo.value = null
    }

    suspend fun fetchAdress(cep: String){
        viewModelScope.apply {
            getCityinfo(cep)
            val adressData = dataBase.getAdress(cep)
            _adressInfo.postValue(adressData)
        }
    }


    suspend fun getCityinfo(cep: String){
        CoroutineScope(Dispatchers.IO).apply {
                val response = RetrofitClient().apiService.getLocation(cep)
                if (response.isSuccessful){
                    response.body().let {
                        if (it != null) {
                            dataBase.saveAdress(it)
                        }
                    }
                }

        }
    }
}