package com.yaundecode.examenadopcionapp

import DogStatus
import java.util.Date

data class Dog(
        val name: String,
        val age: Int,
        val gender: String,
        val weight: Float,
        val description: String,
        val breed: String,
        val subBreed: String,
        val location: String,
        val creationDate: Date,
        val status: DogStatus = DogStatus.EN_ADOPCION,
        val favorite: Boolean = false
)
