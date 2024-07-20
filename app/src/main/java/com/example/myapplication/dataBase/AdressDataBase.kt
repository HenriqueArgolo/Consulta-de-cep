package com.example.myapplication.dataBase

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.myapplication.model.Adress

@Database(entities = [Adress::class], version = 3)
abstract class AdressDataBase:RoomDatabase() {

    companion object{
        const val NAME = "Adress_db"
    }

    abstract fun getAdressDao(): AdressDao
}