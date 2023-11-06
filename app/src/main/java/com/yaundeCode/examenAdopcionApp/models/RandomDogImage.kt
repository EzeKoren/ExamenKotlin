package com.yaundecode.examenadopcionapp.models

import com.google.gson.annotations.SerializedName

data class RandomDogImage(
    @SerializedName("status") val status: String,
    @SerializedName("message") val imageUrl: String
)
