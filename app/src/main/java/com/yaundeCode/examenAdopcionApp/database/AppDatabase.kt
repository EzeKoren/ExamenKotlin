package com.yaundecode.examenadopcionapp.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.yaundecode.examenadopcionapp.Dog

@Database(entities = [Dog::class], version = 4, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {
    abstract fun DogDao(): DogDao

    companion object {

        var INSTANCE: AppDatabase? = null

        fun getAppDataBase(context: Context): AppDatabase? {
            if (INSTANCE == null) {
                synchronized(AppDatabase::class) {
                    INSTANCE =
                            Room.databaseBuilder(
                                            context.applicationContext,
                                            AppDatabase::class.java,
                                            "adopcionDB"
                                    )
                                    .fallbackToDestructiveMigration()
                                    .addMigrations()
                                    .allowMainThreadQueries()
                                    .build()
                }
            }
            return INSTANCE
        }

        fun destroyDataBase() {
            INSTANCE = null
        }
    }
}
