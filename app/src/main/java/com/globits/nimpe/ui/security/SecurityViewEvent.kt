package com.globits.nimpe.ui.security

import com.globits.nimpe.core.NimpeViewEvents

sealed class SecurityViewEvent:NimpeViewEvents {
    object ReturnSigninEvent:SecurityViewEvent()
    object ReturnResetpassEvent:SecurityViewEvent()
}