package com.abunayla.shoppinglist

import androidx.lifecycle.LiveData
import androidx.room.*


//Data Access Object DAO
//https://en.wikipedia.org/wiki/Data_access_object

// ** SQL does'nt allow writing in the main thread, thus operations need to be async i.e.
// (threads or coroutines).
//Add 'suspend' to functions that need to run in async.

@Dao
interface ShoppingDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun upsert(item: ShoppingItem)

    @Delete
    suspend fun delete(item: ShoppingItem)

    @Query("SELECT * FROM shopping_items")
    fun getAllShoppingItems():LiveData<List<ShoppingItem>>
}