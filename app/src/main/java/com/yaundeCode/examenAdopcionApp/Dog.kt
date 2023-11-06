package com.yaundecode.examenadopcionapp

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "dogs")
data class Dog(
        @PrimaryKey(autoGenerate = true)
        @ColumnInfo(name = "id")
        var id : Int,
        @ColumnInfo(name = "name")
        val name: String,
        @ColumnInfo(name = "age")
        val age: Int,
        @ColumnInfo(name = "gender")
        val gender: String,
        @ColumnInfo(name = "weight")
        val weight: Int,
        @ColumnInfo(name = "description")
        val description: String,
        @ColumnInfo(name = "breed")
        val breed: String,
        @ColumnInfo(name = "subBreed")
        val subBreed: String,
        @ColumnInfo(name = "location")
        val location: String,
)
