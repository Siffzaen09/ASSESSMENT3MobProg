package org.d3if4081.ass1.ui.histori

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import org.d3if4081.ass1.R
import org.d3if4081.ass1.databinding.ItemHistoriBinding
import org.d3if4081.ass1.db.JajargenjangEntity
import org.d3if4081.ass1.model.hitungJajargenjang
import java.text.SimpleDateFormat
import java.util.*

class HistoriAdapter :
    ListAdapter<JajargenjangEntity, HistoriAdapter.ViewHolder>(DIFF_CALLBACK) {

    companion object {
        private val DIFF_CALLBACK =
            object : DiffUtil.ItemCallback<JajargenjangEntity>() {
                override fun areItemsTheSame(
                    oldData: JajargenjangEntity, newData: JajargenjangEntity
                ): Boolean {
                    return oldData.id == newData.id
                }
                override fun areContentsTheSame(
                    oldData: JajargenjangEntity, newData: JajargenjangEntity
                ): Boolean {
                    return oldData == newData
                }
            }
    }

    override fun onCreateViewHolder(
        parent: ViewGroup, viewType: Int
    ): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemHistoriBinding.inflate(inflater, parent, false)
        return ViewHolder(binding)
    }
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    class ViewHolder(
        private val binding: ItemHistoriBinding
    ) : RecyclerView.ViewHolder(binding.root) {
        private val dateFormatter = SimpleDateFormat(
            "dd MMMM yyyy",
            Locale("id", "ID")
        )

        fun bind(item: JajargenjangEntity) = with(binding) {
            val hasilJajargenjang = item.hitungJajargenjang()

            tanggalTextView.text = dateFormatter.format(Date(item.tanggal))
            jajargenjangTextView.text = root.context.getString(R.string.hasil_x, hasilJajargenjang.luasJajarGenjang)
        }
    }
}