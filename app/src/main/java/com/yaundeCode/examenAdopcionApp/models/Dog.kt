package com.yaundeCode.examenAdopcionApp.models

import DogStatus
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.Date

@Entity(tableName = "dogs")
class Dog(
        image: String,
        name: String,
        age: Int,
        gender: String,
        publishedDate: String,
        weight: Double,
        description: String,
        breed: String,
        subBreed: String,
        location: String,
        owner: String,
        status: Boolean = false,
        favorite: Boolean = false,
        id: Int? = null
) {

        @ColumnInfo(name = "image")
        val image : String

        @ColumnInfo(name = "name")
        val name: String

        @ColumnInfo(name = "age")
        val age: Int

        @ColumnInfo(name = "gender")
        val gender: String

        @ColumnInfo(name = "publishedDate")
        val publishedDate: String

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

        @ColumnInfo(name = "owner")
        var owner: String

        @ColumnInfo(name = "status")
        var status: Boolean

        @ColumnInfo(name = "favorite")
        var favorite: Boolean

        @PrimaryKey(autoGenerate = true)
        @ColumnInfo(name = "id")
        val id: Int?

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
                this.owner = owner
                this.status = status
                this.favorite = favorite
                this.id = id
        }
}
