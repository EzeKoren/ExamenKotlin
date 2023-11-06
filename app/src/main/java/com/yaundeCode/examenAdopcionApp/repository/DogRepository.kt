package com.yaundeCode.examenAdopcionApp.repository

import android.content.Context
import androidx.room.Room
import com.yaundeCode.examenAdopcionApp.database.AppDatabase
import com.yaundeCode.examenAdopcionApp.database.dao.DogDao
import com.yaundecode.examenadopcionapp.Dog

class DogRepository private constructor(appDatabase: AppDatabase) {
    private val dogDao: DogDao = appDatabase.dogDao()

    suspend fun addDog(dog: Dog) {
        dogDao.insertDog(dog)
    }

    suspend fun removeDog(dog: Dog) {
        dogDao.delete(dog)
    }

    suspend fun getAllDogs(): MutableList<Dog> {
        return dogDao.getAllDogs()
    }

    companion object {
        private var dogRepository: DogRepository? = null

        fun getInstance(context: Context): DogRepository {
            return dogRepository ?: kotlin.run {

                val db = Room.databaseBuilder(
                    context.applicationContext,
                    AppDatabase::class.java, "dog-database"
                ).build()

                val createdTaskRepository = DogRepository(db)
                dogRepository = DogRepository(db)
                createdTaskRepository
            }
        }
    }
}