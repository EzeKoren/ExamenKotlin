package com.yaundecode.examenadopcionapp.models

import DogStatus
import java.time.LocalDate
import java.util.Date

data class Dog(
    val saved: Boolean = false,
    val image: String,
    val name: String,
    val breed: String,
    val subBreed: String,
    val age: Int,
    val gender: String,
    val publishedDate: Date,
    val favorite: Boolean,
    val weight: Double,
    val location: String,
    val description: String,
    val status: DogStatus = DogStatus.EN_ADOPCION,
)
