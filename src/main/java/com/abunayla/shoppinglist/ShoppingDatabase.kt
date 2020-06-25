package com.abunayla.shoppinglist

import android.content.Context
import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.Room
// DB_VERSION need to be updated (e.g. DB_VERSION+=1) each time the db gets an update.
const val DB_VERSION:Int = 1


@Database(entities = [ShoppingItem::class], version = DB_VERSION)
abstract class ShoppingDatabase:RoomDatabase() {
    abstract fun getShoppingDao():ShoppingDao

    companion object{
        @Volatile
        private var instance:ShoppingDatabase? = null
        private val LOCK = Any()


        operator fun invoke(context: Context)= instance?: synchronized(LOCK){
            instance?: createDatabase(context).also { instance= it }
        }
        // Instantiate the DB
        private fun createDatabase(context: Context)=
            Room.databaseBuilder(context.applicationContext,
            ShoppingDatabase::class.java, "ShoppingDB.db").build()
    }
}