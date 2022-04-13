package com.globits.nimpe.ui.home

import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.airbnb.mvrx.*
import com.globits.nimpe.core.NimpeViewModel
import com.globits.nimpe.data.model.Category
import com.globits.nimpe.data.model.HealthOrganization
import com.globits.nimpe.data.model.News
import com.globits.nimpe.data.model.User
import com.globits.nimpe.data.repository.CategoryRepository
import com.globits.nimpe.data.repository.HealthOrganizationRepository
import com.globits.nimpe.data.repository.UserRepository
import dagger.assisted.Assisted
import dagger.assisted.AssistedFactory
import dagger.assisted.AssistedInject
import kotlinx.coroutines.flow.Flow

class HomeViewmodel @AssistedInject constructor(
    @Assisted state: HomeViewState,
    val repository: UserRepository,
    val categoryRepository: CategoryRepository,
    val healthOrganizationRepository: HealthOrganizationRepository
) : NimpeViewModel<HomeViewState,HomeViewAction,HomeViewEvent>(state) {
    private var category:Category=Category()
    override fun handle(action: HomeViewAction) {
        when(action)
        {
            is HomeViewAction.GetCategorys->handleGetCategory()
        }
    }
    fun setCategory(category: Category)
    {
        this.category=category
    }
    fun getCategory()=this.category


    private fun handleGetCategory() {
        setState {
            copy(asyncCategory=Loading())
        }
        categoryRepository.getCategory().execute {
            copy(asyncCategory=it)
        }
    }

    fun getString()="test"
    fun getHealthOrgs( language:Int): Flow<PagingData<HealthOrganization>> {
        val newPageData = healthOrganizationRepository.getHealthOrg(language).cachedIn(viewModelScope)
        return newPageData
    }
    fun getNews( language:Int,category: Category): Flow<PagingData<News>> {
        val newPageData = categoryRepository.getNews(language,category).cachedIn(viewModelScope)
        return newPageData
    }

    @AssistedFactory
    interface Factory {
        fun create(initialState: HomeViewState): HomeViewmodel
    }

    companion object : MvRxViewModelFactory<HomeViewmodel, HomeViewState> {
        @JvmStatic
        override fun create(viewModelContext: ViewModelContext, state: HomeViewState): HomeViewmodel {
            val factory = when (viewModelContext) {
                is FragmentViewModelContext -> viewModelContext.fragment as? Factory
                is ActivityViewModelContext -> viewModelContext.activity as? Factory
            }
            return factory?.create(state) ?: error("You should let your activity/fragment implements Factory interface")
        }
    }

}