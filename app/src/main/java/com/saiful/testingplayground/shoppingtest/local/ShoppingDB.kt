package com.saiful.testingplayground.shoppingtest.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.saiful.testingplayground.shoppingtest.model.ShoppingItem

@Database(
    entities = [ShoppingItem::class],
    version = 1
)
abstract class ShoppingDB : RoomDatabase()  {

    abstract fun shoppingDao(): ShoppingDao
}