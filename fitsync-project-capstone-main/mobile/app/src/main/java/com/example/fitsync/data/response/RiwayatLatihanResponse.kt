package com.example.fitsync.data.response

import com.google.gson.annotations.SerializedName

data class ResponseRiwayatLatihan(

	@field:SerializedName("lastName")
	val lastName: String? = null,

	@field:SerializedName("kaloriPerMinggu")
	val kaloriPerMinggu: Int? = null,

	@field:SerializedName("metrikLatihan")
	val metrikLatihan: List<MetrikLatihanItem?>? = null,

	@field:SerializedName("durasiPerMinggu")
	val durasiPerMinggu: Double? = null
)

data class MetrikLatihanItem(

	@field:SerializedName("kalori")
	val kalori: Int? = null,

	@field:SerializedName("tanggalBulanLatihan")
	val tanggalBulanLatihan: String? = null,

	@field:SerializedName("bagianLatihan")
	val bagianLatihan: String? = null,

	@field:SerializedName("tingkatanLatihan")
	val tingkatanLatihan: String? = null,

	@field:SerializedName("imageUrl")
	val imageUrl: String? = null,

	@field:SerializedName("durasi")
	val durasi: Double? = null,

	@field:SerializedName("jamLatihan")
	val jamLatihan: String? = null
)
