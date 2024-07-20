package com.example.myapplication.infra

import com.example.myapplication.model.Adress
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface ApiService {

    @GET("/ws/{cep}/json/")
    suspend fun getLocation(@Path("cep") cep: String): Response<Adress>

}