package com.example.evaintkt.model

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [Entity::class], version = 1)
abstract class ItemDatabase: RoomDatabase() {

    abstract fun getDao(): Dao


    companion object{
        @Volatile
        private var INSTANCE : ItemDatabase? = null

        fun getDataBase (context: Context): ItemDatabase{
            val tempInstance = INSTANCE
            if (tempInstance!= null){
                return tempInstance
            }
            synchronized(this){
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    ItemDatabase ::class.java,
                    "item_db").build()
                INSTANCE = instance
                return instance

            }
        }

    }
}