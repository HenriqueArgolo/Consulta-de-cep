package com.example.myapplication.view

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.example.myapplication.databinding.ActivityMainBinding
import com.example.myapplication.viewModel.MainViewModel
import androidx.activity.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.lifecycleScope
import com.example.myapplication.model.Adress
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private val viewModel: MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        search()
        viewModel.adressInfo.observe(this, Observer {data ->
            if (data != null) {
                binding.information.visibility = View.GONE
                binding.dataContainer.visibility = View.VISIBLE
                bindLayout(data)}
            })

    }

    private fun search(){
        binding.searchBtn.setOnClickListener {
            val cep = binding.searchInput.text.toString()
            lifecycleScope.launch(Dispatchers.IO){
                viewModel.fetchAdress(cep)
            }
        }
    }

    private fun bindLayout(adressData: Adress){
        binding.localidade.text = String.format("Localidade: %s", adressData.localidade)
        binding.bairro.text = String.format("Bairro: %s", adressData.bairro)
        binding.logradouro.text = String.format("Logradouro: %s", adressData.logradouro)
        binding.complemento.text = String.format("Complemento: %s", adressData.complemento)
        binding.cep.text = String.format("CEP: %s", adressData.cep)
        binding.uf.text = String.format("UF: %s", adressData.uf)
        binding.ibge.text = String.format("IBGE: %s", adressData.ibge)
        binding.gia.text = String.format("GIA: %s", adressData.gia)
        binding.ddd.text = String.format("DDD: %s", adressData.ddd)
        binding.siafi.text = String.format("SIAFI: %s", adressData.siafi)
    }
}
