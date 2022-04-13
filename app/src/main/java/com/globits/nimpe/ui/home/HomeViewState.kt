package com.globits.nimpe.ui.home

import com.airbnb.mvrx.Async
import com.airbnb.mvrx.Loading
import com.airbnb.mvrx.MvRxState
import com.airbnb.mvrx.Uninitialized
import com.globits.nimpe.data.model.Category
import com.globits.nimpe.data.model.News
import com.globits.nimpe.data.model.Page

data class HomeViewState(
    val asyncCategory: Async<Page<Category>> = Uninitialized,
    val asyncNews: Async<Page<News>> = Uninitialized,
) : MvRxState {
    fun isLoadding() = asyncCategory is Loading ||
            asyncNews is Loading

}