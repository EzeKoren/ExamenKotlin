package com.yaundeCode.examenAdopcionApp.models

import com.google.gson.annotations.SerializedName

data class ListAllBreeds(
        @SerializedName("status") var status: String,
        @SerializedName("message") var breeds: Map<String, List<String>>
)
