package com.david.davidunittestproject.repositories

import androidx.lifecycle.LiveData
import com.david.davidunittestproject.data.local.ShoppingItem
import com.david.davidunittestproject.data.remote.responses.ImageResponse
import com.david.davidunittestproject.other.Resource
import retrofit2.Response

interface ShoppingRepository {

    suspend fun insertShoppingItem(shoppingItem: ShoppingItem)

    suspend fun deleteShoppingItem(shoppingItem: ShoppingItem)

    fun observeAllShoppingItems(): LiveData<List<ShoppingItem>>

    fun observeTotalPrice(): LiveData<Float>

    suspend fun searchForImage(imageQuery: String): Resource<ImageResponse>

}