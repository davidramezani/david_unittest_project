package com.david.davidunittestproject.ui

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.david.davidunittestproject.data.local.ShoppingItem
import com.david.davidunittestproject.data.remote.responses.ImageResponse
import com.david.davidunittestproject.other.AppConstants
import com.david.davidunittestproject.other.Event
import com.david.davidunittestproject.other.Resource
import com.david.davidunittestproject.repositories.ShoppingRepository
import kotlinx.coroutines.launch

class ShoppingViewModel @ViewModelInject constructor(
    private val shoppingRepository: ShoppingRepository
) : ViewModel() {

    val shoppingItems = shoppingRepository.observeAllShoppingItems()

    val totalPrice = shoppingRepository.observeTotalPrice()

    private val _images = MutableLiveData<Event<Resource<ImageResponse>>>()
    val images: LiveData<Event<Resource<ImageResponse>>> = _images

    private val _curImageUrl = MutableLiveData<String>()
    val curImageUrl: LiveData<String> = _curImageUrl

    private val _insertShoppingItemStatus = MutableLiveData<Event<Resource<ShoppingItem>>>()
    val insertShoppingItemStatus: LiveData<Event<Resource<ShoppingItem>>> =
        _insertShoppingItemStatus

    fun setCurrentImageUrl(url: String) {
        _curImageUrl.postValue(url)
    }

    fun deleteShoppingItem(shoppingItem: ShoppingItem) = viewModelScope.launch {
        shoppingRepository.deleteShoppingItem(shoppingItem)
    }

    fun insertShoppingItemIntoDb(shoppingItem: ShoppingItem) = viewModelScope.launch {
        shoppingRepository.insertShoppingItem(shoppingItem)
    }

    fun insertShoppingItem(name: String, amountString: String, priceString: String) {
        if (name.isEmpty() || amountString.isEmpty() || priceString.isEmpty()) {
            _insertShoppingItemStatus.postValue(
                Event(
                    Resource.error(
                        "The fields must not be empty",
                        null
                    )
                )
            )
            return
        }
        if (name.length > AppConstants.MAX_NAME_LENGTH) {
            _insertShoppingItemStatus.postValue(
                Event(
                    Resource.error(
                        "The name of item must not exceed ${AppConstants.MAX_NAME_LENGTH} characters",
                        null
                    )
                )
            )
            return
        }
        if (priceString.length > AppConstants.MAX_PRICE_LENGTH) {
            _insertShoppingItemStatus.postValue(
                Event(
                    Resource.error(
                        "The price of item must not exceed ${AppConstants.MAX_PRICE_LENGTH} characters",
                        null
                    )
                )
            )
            return
        }
        val amount = try {
            amountString.toInt()
        } catch (ex: Exception) {
            _insertShoppingItemStatus.postValue(
                Event(
                    Resource.error(
                        "Please enter a valid amount",
                        null
                    )
                )
            )
            return
        }

        val shoppingItem =
            ShoppingItem(0, name, amount, priceString.toFloat(), _curImageUrl.value ?: "")
        insertShoppingItemIntoDb(shoppingItem)
        setCurrentImageUrl("")
        _insertShoppingItemStatus.postValue(Event(Resource.success(shoppingItem)))
    }

    fun searchForImage(imageQuery: String) {
        if (imageQuery.isEmpty()) {
            return
        }
        viewModelScope.launch {
            val response = shoppingRepository.searchForImage(imageQuery)
            _images.value = Event(response)
        }

    }

}