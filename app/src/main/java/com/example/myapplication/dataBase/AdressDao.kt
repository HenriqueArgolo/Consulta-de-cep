package com.example.myapplication.dataBase

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.myapplication.model.Adress

@Dao
interface AdressDao {

    @Query("SELECT * FROM adress WHERE cep=:cep")
    fun getAdress(cep: String): Adress?
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun saveAdress(adress: Adress)
    @Query("DELETE FROM adress WHERE cep=:cep")
    fun deleteAdress(cep:String)
}