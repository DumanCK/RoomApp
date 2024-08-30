package com.duman.roomapp

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.duman.roomapp.db.DaoMahasiswa
import com.duman.roomapp.db.Mahasiswa
import kotlinx.coroutines.launch

class ViewModelMahasiswa(private val dao:DaoMahasiswa) : ViewModel() {

    val isiMahasiswa = dao.getAllMahasiswa()

    fun insertMahasiswa(mahasiswa: Mahasiswa) = viewModelScope.launch {
        dao.insertMahasiswa(mahasiswa)
    }

    fun updateMahasiswa(mahasiswa: Mahasiswa) = viewModelScope.launch {
        dao.updateMahasiswa(mahasiswa)
    }

    fun deleteMahasiswa(mahasiswa: Mahasiswa) = viewModelScope.launch {
        dao.deleteMahasiswa(mahasiswa)
    }

}