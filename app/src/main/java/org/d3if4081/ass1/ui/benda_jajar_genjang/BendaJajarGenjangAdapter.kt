package org.d3if4081.ass1.ui.benda_jajar_genjang

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import org.d3if4081.ass1.R
import org.d3if4081.ass1.databinding.ItemBendaJajarGenjangBinding
import org.d3if4081.ass1.model.BendaJajarGenjang
import org.d3if4081.ass1.network.BendaJajarGenjangApi

class BendaJajarGenjangAdapter : RecyclerView.Adapter<BendaJajarGenjangAdapter.ViewHolder>() {
    private val data = mutableListOf<BendaJajarGenjang>()
    fun updateData(newData: List<BendaJajarGenjang>) {
        data.clear()
        data.addAll(newData)
        notifyDataSetChanged()
    }
    class ViewHolder(
        private val binding: ItemBendaJajarGenjangBinding
    ) : RecyclerView.ViewHolder(binding.root) {
        fun bind(bendaJajarGenjang: BendaJajarGenjang) = with(binding) {
            nama.text = bendaJajarGenjang.nama
            Glide.with(gambar.context)
                .load(BendaJajarGenjangApi.getBendaJajarGenjangUrl(bendaJajarGenjang.gambar))
                .error(R.drawable.ic_launcher_foreground)
                .into(gambar)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemBendaJajarGenjangBinding.inflate(inflater, parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(data[position])
    }

    override fun getItemCount(): Int {
        return data.size
    }
}