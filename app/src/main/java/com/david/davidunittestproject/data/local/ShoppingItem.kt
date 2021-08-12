package com.david.davidunittestproject.data.local

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize

@Entity
@Parcelize
data class ShoppingItem(
    @PrimaryKey(autoGenerate = true)
    val id: Int? = 0,
    var name: String,
    var amount : Int,
    var price: Float,
    var imageUrl: String
) : Parcelable