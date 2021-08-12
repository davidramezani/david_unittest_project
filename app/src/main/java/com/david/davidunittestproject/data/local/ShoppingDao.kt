package com.david.davidunittestproject.data.local

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface ShoppingDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertShoppingItem(shoppingItem: ShoppingItem)

    @Delete
    suspend fun deleteShoppingItem(shoppingItem: ShoppingItem)

    @Query("SELECT * FROM ShoppingItem")
    fun observeAllShoppingItems() : LiveData<List<ShoppingItem>>

    @Query("SELECT SUM(price * amount) FROm ShoppingItem")
    fun observeTotalPrice() : LiveData<Float>
}