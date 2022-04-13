package com.globits.nimpe.ui.home

import com.globits.nimpe.core.NimpeViewModelAction

sealed class HomeViewAction:NimpeViewModelAction{
    object GetCategorys:HomeViewAction()
}