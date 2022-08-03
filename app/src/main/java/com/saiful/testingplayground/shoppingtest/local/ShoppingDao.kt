package com.saiful.testingplayground.shoppingtest.local

import androidx.lifecycle.LiveData
import androidx.room.*
import com.saiful.testingplayground.shoppingtest.model.ShoppingItem

@Dao
interface ShoppingDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertItem(shoppingItem: ShoppingItem)

    @Delete
    suspend fun deleteItem(shoppingItem: ShoppingItem)

    @Query("SELECT * FROM  shopping_items")
    fun observeAll() : LiveData<List<ShoppingItem>>

    @Query("SELECT SUM(price) FROM shopping_items ")
    fun observeTotal() : LiveData<Float>
}