package com.example.myapplication

import android.app.Application
import android.util.Log
import androidx.room.Room
import com.example.myapplication.dataBase.AdressDataBase
import com.example.myapplication.model.Adress
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers

class MyAplication: Application() {

  companion object {
      lateinit var database: AdressDataBase
  }
    override fun onCreate() {
        super.onCreate()
        database = Room.databaseBuilder(
            applicationContext,
            AdressDataBase::class.java,
            AdressDataBase.NAME
        ).fallbackToDestructiveMigration()
            .build()

    }
}