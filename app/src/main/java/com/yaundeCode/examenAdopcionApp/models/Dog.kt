package com.yaundeCode.examenAdopcionApp.models

import java.time.LocalDate

data class Dog(
    val saved: Boolean,
    val image: String,
    val name: String,
    val breed: String,
    val subBreed: List<String>,
    val age: Int,
    val gender: String,
    val publishedDate: String,
    val favorite: Boolean,
    val weight: Double,
    val location: String,
    val description: String,
    val state: String
)
