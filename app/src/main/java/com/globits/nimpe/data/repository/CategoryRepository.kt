package com.globits.nimpe.data.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.globits.nimpe.data.model.*
import com.globits.nimpe.data.network.CategoryApi
import com.globits.nimpe.data.network.HealthOrgPagingSource
import com.globits.nimpe.data.network.NewsPagingSource
import com.globits.nimpe.data.network.UserApi
import io.reactivex.Observable
import io.reactivex.schedulers.Schedulers
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class CategoryRepository @Inject constructor(
    val api: CategoryApi
) {
    fun getCategory(): Observable<Page<Category>> = api.getCategory(CategoryFilter(1,1,100)).subscribeOn(Schedulers.io())
    fun getNews(language:Int,category: Category): Flow<PagingData<News>> {
        return Pager(
            PagingConfig(
                enablePlaceholders = false,
                pageSize = 10,
                prefetchDistance = 5
            )
        ) {
            NewsPagingSource(api,language,category)
        }.flow
    }
    
}