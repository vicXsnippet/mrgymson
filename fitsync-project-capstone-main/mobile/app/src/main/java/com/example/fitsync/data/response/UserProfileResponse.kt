package com.example.fitsync.data.response

import com.google.gson.annotations.SerializedName

data class UserProfileResponse(

	@field:SerializedName("firstName")
	val firstName: String? = null,

	@field:SerializedName("lastName")
	val lastName: String? = null,

	@field:SerializedName("weight")
	val weight: Int? = null,

	@field:SerializedName("height")
	val height: Int? = null
)
