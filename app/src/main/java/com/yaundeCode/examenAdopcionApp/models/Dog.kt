package com.yaundeCode.examenAdopcionApp.models

import DogStatus
import java.util.Date

data class Dog(
        val image: String,
        val name: String,
        val breed: String,
        val subBreed: String,
        val age: Int,
        val gender: String,
        val publishedDate: Date,
        val weight: Double,
        val location: String,
        val description: String,
        val status: DogStatus = DogStatus.EN_ADOPCION,
        val favorite: Boolean = false,
        val owner: String,
)
