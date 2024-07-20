package com.example.myapplication.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
@Entity(tableName = "adress")
data class Adress(
    @PrimaryKey(autoGenerate = false)
    @SerializedName("cep") val cep: String,

    @SerializedName("logradouro") val logradouro: String? = null,
    @SerializedName("complemento") val complemento: String? = null,
    @SerializedName("unidade") val unidade: String? = null,
    @SerializedName("bairro") val bairro: String? = null,
    @SerializedName("localidade") val localidade: String,
    @SerializedName("uf") val uf: String? = null,
    @SerializedName("ibge") val ibge: String? = null,
    @SerializedName("gia") val gia: String? = null,
    @SerializedName("ddd") val ddd: String? = null,
    @SerializedName("siafi") val siafi: String? = null
)
