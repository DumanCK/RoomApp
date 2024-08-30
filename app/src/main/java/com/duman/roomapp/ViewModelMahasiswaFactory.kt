package com.duman.roomapp

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.duman.roomapp.db.DaoMahasiswa

class ViewModelMahasiswaFactory(
    private val dao : DaoMahasiswa) : ViewModelProvider.Factory{
    override fun <T:ViewModel> create(modelClass: Class<T>): T{
        if(modelClass.isAssignableFrom(ViewModelMahasiswa::class.java)){
            return ViewModelMahasiswa(dao) as T
        }
        throw IllegalAccessException("View Model tidak ada")
    }
}