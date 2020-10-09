package com.example.testk24

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.testk24.model.Member

class MemberAdapter(private val listItems: ArrayList<Member>,
private val listener: Listener
) : RecyclerView.Adapter<MemberAdapter.KeuanganViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): KeuanganViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_member, parent, false)
        return KeuanganViewHolder(view)
    }

    override fun getItemCount(): Int {
        return listItems.size
    }

    override fun onBindViewHolder(holder: KeuanganViewHolder, position: Int) {
        val item = listItems[position]
        holder.nama.text = item.nama
        holder.alamat.text = item.alamat
        holder.no.text = item.memberID.toString()
        holder.tgl.text = item.tgllahir
        holder.itemView.setOnClickListener {
            listener.OnItemClicked(item)
        }
    }

    class KeuanganViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var nama = itemView.findViewById<TextView>(R.id.tv_nama)
        var alamat = itemView.findViewById<TextView>(R.id.tv_alamat)
        var no = itemView.findViewById<TextView>(R.id.tv_no)
        var tgl = itemView.findViewById<TextView>(R.id.tv_tgl)
    }

    interface Listener {
        fun OnItemClicked(member: Member)
    }
}
