package com.edofa.kotlinkalkulator.ApiByRetrofit.ApiModel

import com.google.gson.annotations.SerializedName

data class RequestApi(

	@field:SerializedName("images")
	val images: List<String?>? = null,

	@field:SerializedName("price")
	val price: Int? = null,

	@field:SerializedName("description")
	val description: String? = null,

	@field:SerializedName("title")
	val title: String? = null,

	@field:SerializedName("categoryId")
	val categoryId: Int? = null
)
