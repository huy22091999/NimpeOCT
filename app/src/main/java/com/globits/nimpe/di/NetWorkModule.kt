package com.globits.nimpe.di

import android.content.Context
import com.globits.nimpe.data.network.*
import com.globits.nimpe.data.repository.*
import com.globits.nimpe.ui.security.UserPreferences
import com.globits.nimpe.utils.LocalHelper
import dagger.Module
import dagger.Provides

@Module
object NetWorkModule {
    @Provides
    fun providerLocaleHelper():LocalHelper=LocalHelper()

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

    @Provides
    fun providerCategoryRepository(
        api: CategoryApi
    ) : CategoryRepository =CategoryRepository(api)
    @Provides
    fun providerCategoryApi(
        remoteDataSource: RemoteDataSource,
        context: Context
    ) = remoteDataSource.buildApi(CategoryApi::class.java, context)
    @Provides
    fun providerHealthRepository(
        api: HealthOrganizationApi
    ) : HealthOrganizationRepository =HealthOrganizationRepository(api)
    @Provides
    fun providerHealthApi(
        remoteDataSource: RemoteDataSource,
        context: Context
    ) = remoteDataSource.buildApi(HealthOrganizationApi::class.java, context)

    @Provides
    fun providerReDengueRepository(
        api: ReDengueLocationApi
    ) : ReDengueRepository =ReDengueRepository(api)
    @Provides
    fun providerReDengueApi(
        remoteDataSource: RemoteDataSource,
        context: Context
    ) = remoteDataSource.buildApi(ReDengueLocationApi::class.java, context)


}