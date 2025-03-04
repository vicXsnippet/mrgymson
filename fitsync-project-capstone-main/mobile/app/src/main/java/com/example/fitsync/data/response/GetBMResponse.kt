package com.example.fitsync.data.response

import com.google.gson.annotations.SerializedName

data class GetBMResponse(

	@field:SerializedName("beratBadan")
	val beratBadan: Int? = null,

	@field:SerializedName("tinggiBadan")
	val tinggiBadan: Int? = null
)
