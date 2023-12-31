package com.yaundeCode.examenAdopcionApp.database

import androidx.lifecycle.LiveData
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

    @Query("SELECT * FROM Dogs WHERE gender = :gender")
    fun getDogsByGender(gender: String): List<Dog>

    @Query("SELECT * FROM Dogs WHERE age = :age")
    fun getDogsByAge(age: Int): List<Dog>

    @Query("DELETE FROM dogs")
    fun wipeDB()

    @Query("SELECT COUNT(*) FROM dogs WHERE favorite = 1")
    fun getFavoriteDogsCount(): LiveData<Int>

    @Query("SELECT COUNT(*) FROM dogs WHERE owner = :ownerName")
    fun getDogsByOwnerCount(ownerName: String): LiveData<Int>
}