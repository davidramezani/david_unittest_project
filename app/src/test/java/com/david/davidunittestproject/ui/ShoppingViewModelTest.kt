package com.david.davidunittestproject.ui

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.david.davidunittestproject.MainCoroutineRule
import com.david.davidunittestproject.getOrAwaitValueTest
import com.david.davidunittestproject.other.AppConstants
import com.david.davidunittestproject.other.Status
import com.david.davidunittestproject.repositories.FakeShoppingRepositories
import com.google.common.truth.Truth.assertThat
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.junit.Before
import org.junit.Rule
import org.junit.Test

@ExperimentalCoroutinesApi
class ShoppingViewModelTest {

    @get:Rule
    var instantTaskExecutor = InstantTaskExecutorRule()

    @get:Rule
    var mainCoroutineRule = MainCoroutineRule()

    private lateinit var viewModel: ShoppingViewModel

    @Before
    fun setup() {
        viewModel = ShoppingViewModel(FakeShoppingRepositories())
    }

    @Test
    fun `insert shopping item with empty field, return error`() {
        viewModel.insertShoppingItem("name","","3.0")

        val value = viewModel.insertShoppingItemStatus.getOrAwaitValueTest()

        assertThat(value.getContentIfNotHandled()?.status).isEqualTo(Status.ERROR)
    }

    @Test
    fun `insert shopping item with too long name, return error`() {

        val string = buildString {
            for(i in 1..AppConstants.MAX_NAME_LENGTH+1){
                append("a")
            }
        }

        viewModel.insertShoppingItem(string,"5","3.0")

        val value = viewModel.insertShoppingItemStatus.getOrAwaitValueTest()

        assertThat(value.getContentIfNotHandled()?.status).isEqualTo(Status.ERROR)
    }

    @Test
    fun `insert shopping item with too long price, return error`() {

        val priceString = buildString {
            for(i in 1..AppConstants.MAX_PRICE_LENGTH+1){
                append(2)
            }
        }

        viewModel.insertShoppingItem("name","5",priceString)

        val value = viewModel.insertShoppingItemStatus.getOrAwaitValueTest()

        assertThat(value.getContentIfNotHandled()?.status).isEqualTo(Status.ERROR)
    }

    @Test
    fun `insert shopping item with too long amount, return error`() {

        viewModel.insertShoppingItem("name","999999999999999999","3.0")

        val value = viewModel.insertShoppingItemStatus.getOrAwaitValueTest()

        assertThat(value.getContentIfNotHandled()?.status).isEqualTo(Status.ERROR)
    }

    @Test
    fun `insert shopping item with valid input, return success`() {

        viewModel.insertShoppingItem("name","5","3.0")

        val value = viewModel.insertShoppingItemStatus.getOrAwaitValueTest()

        assertThat(value.getContentIfNotHandled()?.status).isEqualTo(Status.SUCCESS)
    }

    @Test
    fun `at the end of insert shopping item current image url is empty, return true`() {

        viewModel.setCurrentImageUrl("url")

        viewModel.insertShoppingItem("name","5","3.0")

        viewModel.insertShoppingItemStatus.getOrAwaitValueTest()

        val imageUrl = viewModel.curImageUrl.getOrAwaitValueTest()

        assertThat(imageUrl).isEmpty()
    }

}