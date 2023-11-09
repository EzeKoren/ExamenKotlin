package com.yaundeCode.examenAdopcionApp.models

class Breed(breed: String,selected: Boolean = false) {
    var selected: Boolean
    val breed: String

    init {
        this.breed = breed
        this.selected = selected
    }
}