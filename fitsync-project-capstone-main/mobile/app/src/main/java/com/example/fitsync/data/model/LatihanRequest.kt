package com.example.fitsync.data.model

data class LatihanRequest(
   val durasi: Double,
   val kalori: Int,
   val jamLatihan: String,
   val tanggalBulanLatihan: String,
   val bagianLatihan: String?,
   val tingkatanLatihan: String?
)