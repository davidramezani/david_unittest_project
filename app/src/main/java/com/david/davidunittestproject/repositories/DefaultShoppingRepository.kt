package com.david.davidunittestproject.repositories

import androidx.lifecycle.LiveData
import com.david.davidunittestproject.BuildConfig
import com.david.davidunittestproject.data.api.PixabayAPI
import com.david.davidunittestproject.data.local.ShoppingDao
import com.david.davidunittestproject.data.local.ShoppingItem
import com.david.davidunittestproject.data.remote.responses.ImageResponse
import com.david.davidunittestproject.other.Resource
import retrofit2.Response
import javax.inject.Inject

class DefaultShoppingRepository @Inject constructor(
    private val shoppingDao: ShoppingDao,
    private val pixabayAPI: PixabayAPI
) : ShoppingRepository {

    override suspend fun insertShoppingItem(shoppingItem: ShoppingItem) {
        shoppingDao.insertShoppingItem(shoppingItem)
    }

    override suspend fun deleteShoppingItem(shoppingItem: ShoppingItem) {
        shoppingDao.deleteShoppingItem(shoppingItem)
    }

    override fun observeAllShoppingItems(): LiveData<List<ShoppingItem>> {
        return shoppingDao.observeAllShoppingItems()
    }

    override fun observeTotalPrice(): LiveData<Float> {
        return shoppingDao.observeTotalPrice()
    }

    override suspend fun searchForImage(imageQuery: String): Resource<ImageResponse> {
        return try {
            val res = pixabayAPI.searchForImage(imageQuery, BuildConfig.API_KEY)
            if (res.isSuccessful) {
                res.body()?.let {
                    return@let Resource.success(it)
                } ?: return Resource.error("An unknown error occurred!!", null)
            } else {
                return Resource.error("An unknown error occurred!!", null)
            }
        } catch (e: Exception) {
            return Resource.error("Couldn't reach the server, Check your internet connection", null)
        }
    }
}