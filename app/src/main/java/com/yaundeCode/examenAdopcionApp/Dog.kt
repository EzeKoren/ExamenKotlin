package com.yaundecode.examenadopcionapp

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "dogs")
class Dog(
        id : Int,
        name: String,
        age: Int,
        gender: String,
        weight: Float,
        description: String,
        breed: String,
        subBreed: String,
        location: String,
) {
        @PrimaryKey(autoGenerate = true)
        @ColumnInfo(name = "id")
        var id : Int

        @ColumnInfo(name = "name")
        val name: String

        @ColumnInfo(name = "age")
        val age: Int

        @ColumnInfo(name = "gender")
        val gender: String

        @ColumnInfo(name = "weight")
        val weight: Float

        @ColumnInfo(name = "description")
        val description: String

        @ColumnInfo(name = "breed")
        val breed: String

        @ColumnInfo(name = "subBreed")
        val subBreed: String

        @ColumnInfo(name = "location")
        val location: String

        init {
                this.id = id
                this.name = name
                this.age = age
                this.gender = gender
                this.weight = weight
                this.description = description
                this.breed = breed
                this.subBreed = subBreed
                this.location = location
        }
}
