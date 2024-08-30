package com.duman.roomapp

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.duman.roomapp.db.Mahasiswa

class RViewAdapterMahasiswa(
    private val clickListener:(Mahasiswa)->Unit
) : RecyclerView.Adapter<ViewHolderMahasiswa>(){

    private val listMahasiswa = ArrayList<Mahasiswa>()

    fun isiList(mahasiswas:List<Mahasiswa>){
        listMahasiswa.clear()
        listMahasiswa.addAll(mahasiswas)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolderMahasiswa {
        val layoutInflater = LayoutInflater.from(parent.context)
        val lisItem = layoutInflater.inflate(R.layout.list_item, parent, false)
        return ViewHolderMahasiswa(lisItem)
    }

    override fun getItemCount(): Int {
        return listMahasiswa.size
    }

    override fun onBindViewHolder(holder: ViewHolderMahasiswa, position: Int) {
        holder.bind(listMahasiswa[position],clickListener)
    }
}

class ViewHolderMahasiswa(private val view: View):RecyclerView.ViewHolder(view){
    fun bind(mahasiswa: Mahasiswa, clickListener: (Mahasiswa) -> Unit){
        val textNama = view.findViewById<TextView>(R.id.tvNama)
        val textEmail = view.findViewById<TextView>(R.id.tvEmail)
        textNama.text = mahasiswa.nama
        textEmail.text = mahasiswa.email
        view.setOnClickListener {
            clickListener(mahasiswa)
        }
    }
}