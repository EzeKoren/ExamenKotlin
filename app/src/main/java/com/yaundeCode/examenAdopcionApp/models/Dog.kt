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
        var image : String

        @ColumnInfo(name = "name")
        var name: String

        @ColumnInfo(name = "age")
        var age: Int

        @ColumnInfo(name = "gender")
        var gender: String

        @ColumnInfo(name = "publishedDate")
        var publishedDate: String

        @ColumnInfo(name = "weight")
        var weight: Double

        @ColumnInfo(name = "description")
        var description: String

        @ColumnInfo(name = "breed")
        var breed: String

        @ColumnInfo(name = "subBreed")
        var subBreed: String

        @ColumnInfo(name = "location")
        var location: String

        @ColumnInfo(name = "owner")
        var owner: String

        @ColumnInfo(name = "status")
        var status: Boolean

        @ColumnInfo(name = "favorite")
        var favorite: Boolean

        @PrimaryKey(autoGenerate = true)
        @ColumnInfo(name = "id")
        var id: Int?

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
