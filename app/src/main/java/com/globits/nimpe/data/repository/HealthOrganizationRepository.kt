package com.globits.nimpe.data.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.globits.nimpe.data.model.*
import com.globits.nimpe.data.network.HealthOrgPagingSource
import com.globits.nimpe.data.network.HealthOrganizationApi
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class HealthOrganizationRepository @Inject constructor(
    val api: HealthOrganizationApi
) {
    fun getHealthOrg(language:Int): Flow<PagingData<HealthOrganization>> {
        return Pager(
            PagingConfig(
                enablePlaceholders = false,
                pageSize = 10,
                prefetchDistance = 5
            )
        ) {
            HealthOrgPagingSource(api,language)
        }.flow
    }
    //fun getHealthOrg(): Observable<Page<HealthOrganization>> = api.getHealthOrganization(HealthOrganizationFilter(1,100)).subscribeOn(Schedulers.io())
}