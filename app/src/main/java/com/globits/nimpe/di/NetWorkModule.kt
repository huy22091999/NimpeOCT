package com.globits.nimpe.di

import android.content.Context
import androidx.lifecycle.ViewModelProvider
import com.globits.nimpe.data.network.AuthApi
import com.globits.nimpe.data.network.RemoteDataSource
import com.globits.nimpe.data.network.UserApi
import com.globits.nimpe.data.repository.AuthRepository
import com.globits.nimpe.data.repository.UserRepository
import com.globits.nimpe.ui.security.UserPreferences
import dagger.Binds
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
object NetWorkModule {

    @Provides
    fun providerRemoteDateSource(): RemoteDataSource = RemoteDataSource()


    @Provides
    fun providerUserPreferences(context: Context): UserPreferences= UserPreferences(context)


    @Provides
    fun providerAuthApi(
        remoteDataSource: RemoteDataSource,
        context: Context
    ) = remoteDataSource.buildApi(AuthApi::class.java, context)


    @Provides
    fun providerAuthRepository(
        userPreferences: UserPreferences,
        api: AuthApi
    ) :AuthRepository=AuthRepository(api,userPreferences)


    @Provides
    fun providerUserApi(
        remoteDataSource: RemoteDataSource,
        context: Context
    ) = remoteDataSource.buildApi(UserApi::class.java, context)


    @Provides
    fun providerUserRepository(
        api: UserApi
    ) : UserRepository =UserRepository(api)

}