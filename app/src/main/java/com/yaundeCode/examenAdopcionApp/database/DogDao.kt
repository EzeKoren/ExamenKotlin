package com.yaundeCode.examenAdopcionApp.database

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.yaundeCode.examenAdopcionApp.models.Dog

@Dao
interface DogDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertDog(dog: Dog?)

    @Update
    fun updateDog(dog: Dog?)

    @Delete
    fun delete(dog: Dog?)

    @Query("SELECT * FROM dogs")
    fun getAll(): List<Dog>

    @Query("SELECT COUNT(*) FROM dogs")
    fun getDogCount(): Int

    @Query("DELETE FROM dogs")
    fun wipeDB()

    @Query("SELECT * FROM dogs WHERE owner = :ownerName AND status = 1")
    fun getAdoptedDogsByOwner(ownerName: String): List<Dog>
}