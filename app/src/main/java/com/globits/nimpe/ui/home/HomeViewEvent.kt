package com.globits.nimpe.ui.home

import com.globits.nimpe.core.NimpeViewEvents

sealed class HomeViewEvent:NimpeViewEvents{
    object ResetLanguege:HomeViewEvent()
    object SaveFeedback:HomeViewEvent()
}