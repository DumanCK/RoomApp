package com.duman.roomapp.db

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "tb_mahasiswa")
data class Mahasiswa(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name="nim")
    var id:Int,
    @ColumnInfo(name="nama_mahasiswa")
    var nama:String,
    @ColumnInfo(name="email_mahasiswa")
    var email:String
)