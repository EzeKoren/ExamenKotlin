package com.yaundeCode.examenAdopcionApp.database

import androidx.room.Database
import androidx.room.TypeConverters
import com.yaundeCode.examenAdopcionApp.database.dao.DogDao
import com.yaundecode.examenadopcionapp.Dog

@Database(entities = [Dog::class], version = 1, exportSchema = false)
@TypeConverters(Converters::class)
abstract class AppDatabase {
    abstract fun dogDao(): DogDao
}