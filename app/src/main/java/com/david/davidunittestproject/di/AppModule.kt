package com.david.davidunittestproject.di

import android.content.Context
import androidx.room.Room
import com.david.davidunittestproject.data.api.PixabayAPI
import com.david.davidunittestproject.data.local.ShoppingItemDataBase
import com.david.davidunittestproject.other.AppConstants.BASE_URL
import com.david.davidunittestproject.other.AppConstants.DATABASE_NAME
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import dagger.hilt.android.qualifiers.ApplicationContext
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(ApplicationComponent::class)
object AppModule {

    @Singleton
    @Provides
    fun provideShoppingItemDataBase(
        @ApplicationContext context : Context
    ) = Room.databaseBuilder(context, ShoppingItemDataBase::class.java, DATABASE_NAME).build()

    @Singleton
    @Provides
    fun provideShoppingDao(
        dataBase: ShoppingItemDataBase
    ) = dataBase.shoppingDao()

    @Singleton
    @Provides
    fun providePixabayApi() : PixabayAPI {
        return Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl(BASE_URL)
            .build()
            .create(PixabayAPI::class.java)
    }
}