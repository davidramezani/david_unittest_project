package com.david.davidunittestproject.data.remote.responses

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class ImageResult(
    var collections: Int,
    var comments: Int,
    var downloads: Int,
    var id: Int,
    var imageHeight: Int,
    var imageSize: Int,
    var imageWidth: Int,
    var largeImageURL: String,
    var likes: Int,
    var pageURL: String,
    var previewHeight: Int,
    var previewURL: String,
    var previewWidth: Int,
    var tags: String,
    var type: String,
    var user: String,
    var userImageURL: String,
    @SerializedName("user_id")
    var userId: Int,
    var views: Int,
    var webformatHeight: Int,
    var webformatURL: String,
    var webformatWidth: Int
) : Parcelable