package com.example.fitsync.ui.adapter

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.fitsync.R
import com.example.fitsync.data.response.MetrikLatihanItem
import com.example.fitsync.databinding.HistoryLatihanItemBinding
import java.util.Locale

class HistoryLatihanAdapter(
   private val listRiwayatLatihan: List<MetrikLatihanItem?>?,
   private val context: Context
) :
   RecyclerView.Adapter<HistoryLatihanAdapter.ListViewHolder>() {
//
//   class ListViewHolder(view: View) : RecyclerView.ViewHolder(view) {
//      val imgLatihan: ImageView = view.findViewById(R.id.image_latihan)
//      val namaLatihan: TextView = view.findViewById(R.id.tv_nama_latihan)
//   }

   inner class ListViewHolder(private val binding: HistoryLatihanItemBinding) :
      RecyclerView.ViewHolder(binding.root) {
      @SuppressLint("SetTextI18n")
      fun bind(listRiwayatLatihan: MetrikLatihanItem) {
         with(binding) {
            tvNamaLatihan.text =
               "${listRiwayatLatihan.bagianLatihan} • ${listRiwayatLatihan.tingkatanLatihan}"
            tvJamLatihan.text = listRiwayatLatihan.jamLatihan
            tvTanggalLatihan.text = listRiwayatLatihan.tanggalBulanLatihan
            Glide.with(binding.imageLatihan)
               .load(listRiwayatLatihan.imageUrl)
               .centerCrop()
               .into(binding.imageLatihan)
            tvValueKalori.text = listRiwayatLatihan.kalori.toString()
            tvValueDurasi.text = context.getString(
               R.string.durasi_latihan,
               String.format(Locale.US,"%.1f", listRiwayatLatihan.durasi)
            )
         }
      }
   }

   override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListViewHolder {
      val binding =
         HistoryLatihanItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
      return ListViewHolder(binding)
   }

   override fun getItemCount(): Int = listRiwayatLatihan!!.size

   override fun onBindViewHolder(holder: ListViewHolder, position: Int) {
      holder.bind(listRiwayatLatihan!![position]!!)
   }
}