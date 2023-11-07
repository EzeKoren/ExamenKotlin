package com.yaundeCode.examenAdopcionApp.models

import DogStatus
import androidx.room.ColumnInfo
import androidx.room.Entity
import java.util.Date

@Entity(tableName = "dogs")
public class Dog(
        image: String,
        name: String,
        age: Int,
        gender: String,
        publishedDate: Date,
        weight: Double,
        description: String,
        breed: String,
        subBreed: String,
        location: String,
) {
        @ColumnInfo(name = "image")
        var image : String

        @ColumnInfo(name = "name")
        val name: String

        @ColumnInfo(name = "age")
        val age: Int

        @ColumnInfo(name = "gender")
        val gender: String

        @ColumnInfo(name = "publishedDate")
        val publishedDate: Date

        @ColumnInfo(name = "weight")
        val weight: Double

        @ColumnInfo(name = "description")
        val description: String

        @ColumnInfo(name = "breed")
        val breed: String

        @ColumnInfo(name = "subBreed")
        val subBreed: String

        @ColumnInfo(name = "location")
        val location: String

        @ColumnInfo(name = "status")
        val status: DogStatus

        @ColumnInfo(name = "favorite")
        val favorite: Boolean

        init {
                this.image = image
                this.name = name
                this.age = age
                this.gender = gender
                this.publishedDate = publishedDate
                this.weight = weight
                this.description = description
                this.breed = breed
                this.subBreed = subBreed
                this.location = location
                this.status = DogStatus.EN_ADOPCION
                this.favorite = false
        }
}
