package com.example.myfirstapp.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [Contact::class],version = 1, exportSchema = false)
abstract class Database : RoomDatabase(){
    abstract val databaseDao: DatacontactDao
    companion object{
        @Volatile
        private var INSTANCE: com.example.myfirstapp.database.Database? = null
        fun getInstance(context: Context): com.example.myfirstapp.database.Database {
            synchronized(this) {
                var instance = INSTANCE
                if (instance == null) {
                    instance = Room.databaseBuilder(
                        context.applicationContext,
                        com.example.myfirstapp.database.Database::class.java,
                        "database"
                    )
                        .fallbackToDestructiveMigration()
                        .build()
                    INSTANCE = instance
                }
                return instance
            }
        }
    }
}