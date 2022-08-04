package com.saiful.testingplayground.shoppingtest.local

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.SmallTest
import com.google.common.truth.Truth.assertThat
import com.saiful.testingplayground.shoppingtest.model.ShoppingItem
import com.saiful.testingplayground.util.getOrAwaitValue
import kotlinx.coroutines.runBlocking
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
@SmallTest
class ShoppingDaoTest {

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    private lateinit var database: ShoppingDB
    private lateinit var dao: ShoppingDao

    @Before
    fun setup() {
        database = Room.inMemoryDatabaseBuilder(
            ApplicationProvider.getApplicationContext(),
            ShoppingDB::class.java
        ).allowMainThreadQueries().build()

        dao = database.shoppingDao()
    }

    @After
    fun tearDown() {
        database.close()
    }

    @Test
    fun insetShoppingItem() = runBlocking {
        val shoppingItem = ShoppingItem(1, "a", 22f)
        dao.insertItem(shoppingItem)

        val allItem = dao.observeAll().getOrAwaitValue()
        assertThat(allItem).contains(shoppingItem)
    }

    @Test
    fun deleteShoppingItem() = runBlocking {
        val shoppingItem = ShoppingItem(1, "a", 22f)
        dao.insertItem(shoppingItem)
        dao.deleteItem(shoppingItem)

        val allItem = dao.observeAll().getOrAwaitValue()
        assertThat(allItem).doesNotContain(shoppingItem)

    }

    @Test
    fun observeTotalPrice() = runBlocking {
        val shoppingItem1 = ShoppingItem(1, "a", 22f)
        val shoppingItem2 = ShoppingItem(2, "b", 22f)
        val shoppingItem3 = ShoppingItem(3, "c", 22f)
        dao.insertItem(shoppingItem1)
        dao.insertItem(shoppingItem2)
        dao.insertItem(shoppingItem3)

        val total = dao.observeTotal().getOrAwaitValue()
        assertThat(total).isEqualTo(66f)
    }

    @Test
    fun updateItem() = runBlocking {
        val shoppingItem = ShoppingItem(1, "a", 22f)
        dao.insertItem(shoppingItem)

        shoppingItem.name = "b"
        dao.updateItem(shoppingItem)

        val allItem = dao.selectItem(1).getOrAwaitValue()
        assertThat(allItem.name).isEqualTo("b")
    }

}