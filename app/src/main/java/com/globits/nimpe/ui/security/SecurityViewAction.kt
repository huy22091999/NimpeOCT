package com.globits.nimpe.ui.security

import com.globits.nimpe.core.NimpeViewModelAction
import com.globits.nimpe.data.model.UserCredentials

sealed class SecurityViewAction :NimpeViewModelAction{
 data class LogginAction(var userName:String, var password:String ):SecurityViewAction()
}