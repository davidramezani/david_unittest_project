package com.david.davidunittestproject.data.local

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.SmallTest
import com.david.davidunittestproject.getOrAwaitValue
import com.google.common.truth.Truth.assertThat
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@ExperimentalCoroutinesApi
@RunWith(AndroidJUnit4::class)
@SmallTest
class ShoppingDaoTest {

    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()
    private lateinit var dataBase: ShoppingItemDataBase
    private lateinit var dao: ShoppingDao

    @Before
    fun setup() {
        dataBase = Room.inMemoryDatabaseBuilder(
            ApplicationProvider.getApplicationContext(),
            ShoppingItemDataBase::class.java
        ).allowMainThreadQueries().build()
        dao = dataBase.shoppingDao()
    }

    @After
    fun tearDown() {
        dataBase.close()
    }

    @Test
    fun insertShoppingItemTest() = runBlockingTest {
        val shoppingItem = ShoppingItem(1, "name", 1, 1f, "url")
        dao.insertShoppingItem(shoppingItem)
        val allShoppingItem = dao.observeAllShoppingItems().getOrAwaitValue()
        assertThat(allShoppingItem).contains(shoppingItem)
    }

    @Test
    fun deleteShoppingItemTest() = runBlockingTest {
        val shoppingItem = ShoppingItem(1, "name", 1, 1f, "url")
        dao.insertShoppingItem(shoppingItem)
        dao.deleteShoppingItem(shoppingItem)
        val allShoppingItem = dao.observeAllShoppingItems().getOrAwaitValue()
        assertThat(allShoppingItem).doesNotContain(shoppingItem)
    }

    @Test
    fun observeTotalPriceTest() = runBlockingTest {
        val shoppingItem = ShoppingItem(1, "banana", 2, 200f, "url")
        val shoppingItem2 = ShoppingItem(2, "coconut", 2, 400f, "url")
        dao.insertShoppingItem(shoppingItem)
        dao.insertShoppingItem(shoppingItem2)

        val totalPrice = dao.observeTotalPrice().getOrAwaitValue()
        assertThat(totalPrice).isEqualTo(1200)
    }

}