package com.yaundeCode.examenAdopcionApp.database

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.yaundecode.examenadopcionapp.Dog

@Dao
interface DogDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertDog(dog: Dog?)

    @Update
    fun updateDog(dog: Dog?)

    @Delete
    fun delete(dog: Dog?)

    @Query("SELECT * FROM dogs WHERE name = :name ORDER BY id")
    fun loadAllDogsByName(name: String): MutableList<Dog>
}