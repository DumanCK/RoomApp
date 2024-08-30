package com.duman.roomapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.LinearLayout
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.duman.roomapp.db.DBMahasiswa
import com.duman.roomapp.db.Mahasiswa

class MainActivity : AppCompatActivity() {

    private lateinit var inputNama : EditText
    private lateinit var inputEmail : EditText
    private lateinit var tombolSave : Button
    private lateinit var tombolReset : Button
    private lateinit var tombolUpdate : Button
    private lateinit var tombolDelete : Button
    private lateinit var rViewMahasiswa : RecyclerView
    private lateinit var adapter : RViewAdapterMahasiswa
    private lateinit var grup1 : LinearLayout
    private lateinit var grup2 : LinearLayout

    private var isListCLick = false

    private lateinit var mahasiswaPilih : Mahasiswa

    private lateinit var viewModel : ViewModelMahasiswa

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        inputNama = findViewById(R.id.etNama)
        inputEmail = findViewById(R.id.etEmail)
        tombolSave = findViewById(R.id.btSave)
        tombolReset = findViewById(R.id.btReset)
        tombolUpdate = findViewById(R.id.btUpdate)
        tombolDelete = findViewById(R.id.btDelete)
        rViewMahasiswa = findViewById(R.id.rvMahasiswa)
        grup1 = findViewById(R.id.grup1)
        grup2 = findViewById(R.id.grup2)

        grup2.visibility = View.GONE

        val dao = DBMahasiswa.getInstance(application).daoMahasiswa()
        val factory = ViewModelMahasiswaFactory(dao)
        viewModel = ViewModelProvider(this, factory).get(ViewModelMahasiswa::class.java)

        tombolSave.setOnClickListener {
            saveDataMahasiswa()
            resetInput()
        }

        tombolUpdate.setOnClickListener {
            updateDataMahasiswa()
        }

        tombolDelete.setOnClickListener {
            deleteDataMahasiswa()
        }

        tombolReset.setOnClickListener {
            resetInput()
        }

        initRV()
    }

    private fun resetInput() {
        inputNama.setText("")
        inputEmail.setText("")
    }


    private fun initRV(){
        rViewMahasiswa.layoutManager = LinearLayoutManager(this)
        adapter = RViewAdapterMahasiswa{
            selectedItem:Mahasiswa -> listItemClick(selectedItem)
        }
        rViewMahasiswa.adapter = adapter

        tampilDataMahasiswa()
    }

    private fun listItemClick(mahasiswa: Mahasiswa) {
        mahasiswaPilih = mahasiswa
        //ambil data di sini pindah intent di fungsi on create / atau fungsi lain
        //Toast.makeText(this,"Nama : ${mahasiswaPilih.nama}",Toast.LENGTH_LONG).show()
        grup2.visibility = View.VISIBLE
        grup1.visibility = View.GONE
        isListCLick = true

        inputNama.setText(mahasiswaPilih.nama)
        inputEmail.setText(mahasiswaPilih.email)
    }


    private fun tampilDataMahasiswa(){
        viewModel.isiMahasiswa.observe(this){
            adapter.isiList(it)
            adapter.notifyDataSetChanged()
        }
    }

    private fun saveDataMahasiswa() {
        val nama = inputNama.text.toString()
        val email = inputEmail.text.toString()
        val mahasiswa = Mahasiswa(0,nama,email)
        viewModel.insertMahasiswa(mahasiswa)
    }

    private fun deleteDataMahasiswa() {
        val mahasiswa = Mahasiswa(mahasiswaPilih.id,
            inputNama.text.toString(),
            inputEmail.text.toString())
        viewModel.deleteMahasiswa(mahasiswa)

        grup2.visibility = View.GONE
        grup1.visibility = View.VISIBLE
        resetInput()
        isListCLick = false
    }

    private fun updateDataMahasiswa() {
        val nama = inputNama.text.toString()
        val email = inputEmail.text.toString()
        val mahasiswa = Mahasiswa(mahasiswaPilih.id,nama,email)
        viewModel.updateMahasiswa(mahasiswa)

        grup2.visibility = View.GONE
        grup1.visibility = View.VISIBLE
        resetInput()
        isListCLick = false
    }

}