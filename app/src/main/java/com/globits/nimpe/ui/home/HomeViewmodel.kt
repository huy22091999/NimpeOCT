package com.globits.nimpe.ui.home

import com.airbnb.mvrx.*
import com.globits.nimpe.data.model.User
import com.globits.nimpe.data.repository.UserRepository
import dagger.assisted.Assisted
import dagger.assisted.AssistedFactory
import dagger.assisted.AssistedInject

class HomeViewmodel @AssistedInject constructor(
    @Assisted state: HomeViewState,
    val repository: UserRepository
) : BaseMvRxViewModel<HomeViewState>(state,false) {
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

    fun getUser():List<User>{
        return listOf(User("1","Huy","0123456789"),User("2","Mai","0123459876"))
    }
    fun getString()=repository.getString()

}