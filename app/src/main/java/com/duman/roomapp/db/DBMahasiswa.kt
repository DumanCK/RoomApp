package com.duman.roomapp.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [Mahasiswa::class], version=1, exportSchema = false)
abstract class DBMahasiswa : RoomDatabase() {
    //Singleton (Java)- class yang hanya bisa diinit sekali dalam aplikasi
    //Companion Object (Kotlin)
    abstract fun daoMahasiswa():DaoMahasiswa
    companion object{
        @Volatile
        private var INSTANCE : DBMahasiswa? = null
        fun getInstance(context: Context):DBMahasiswa{
            synchronized(this) {
                var instance = INSTANCE
                if(instance == null){
                    instance = Room.databaseBuilder(
                        context.applicationContext,
                        DBMahasiswa::class.java,
                        "tb_mahasiswa"
                    ).build()
                }
                return instance
            }
        }
    }
}