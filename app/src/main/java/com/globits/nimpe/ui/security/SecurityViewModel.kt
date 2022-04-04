package com.globits.nimpe.ui.security
import androidx.lifecycle.viewModelScope
import com.airbnb.mvrx.Loading
import com.globits.nimpe.core.NimpeViewModel
import com.globits.nimpe.data.model.TokenResponse
import com.globits.nimpe.data.repository.AuthRepository
import dagger.assisted.Assisted
import dagger.assisted.AssistedInject
import kotlinx.coroutines.async


class SecurityViewModel @AssistedInject constructor(
    @Assisted state: SecurityViewState,
    val repository: AuthRepository
) :
    NimpeViewModel<SecurityViewState,SecurityViewAction,SecurityViewEvent>(state) {

    override fun handle(action: SecurityViewAction) {
        when(action){
            is SecurityViewAction.LogginAction->handleLogin(action.userName,action.password)
            is SecurityViewAction.LogginAction->handleLogin(action.userName,action.password)
        }
    }
    fun handleLogin(userName:String,password: String){
        setState {
            copy(asyncLogin=Loading())
        }
        repository.login(userName,password).execute {
            copy(asyncLogin=it)

        }
    }
    fun handleSaveToken(tokenResponse: TokenResponse)
    {
        this.viewModelScope.async {
            repository.saveAccessTokens(tokenResponse)
        }

    }

}