package com.globits.nimpe.ui.home

import com.globits.nimpe.core.NimpeViewModelAction
import com.globits.nimpe.data.model.Feedback

sealed class HomeViewAction:NimpeViewModelAction{

    object GetCurrentUser:HomeViewAction()
    object GetCategorys:HomeViewAction()
    object ResetLang:HomeViewAction()
    data class SaveFeedback(val feedback: Feedback):HomeViewAction()

}