package com.globits.nimpe.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.airbnb.mvrx.activityViewModel
import com.globits.nimpe.R
import com.globits.nimpe.core.NimpeBaseFragment
import com.globits.nimpe.databinding.FragmentHomeBinding
import com.globits.nimpe.ui.MainActivity
import javax.inject.Inject

class HomeFragment @Inject constructor() : NimpeBaseFragment<FragmentHomeBinding>() {

    private val viewModel:HomeViewmodel by activityViewModel()

    override fun getBinding(inflater: LayoutInflater, container: ViewGroup?): FragmentHomeBinding {
        return FragmentHomeBinding.inflate(inflater,container,false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        views.homeToCategory.setOnClickListener {
            (activity as MainActivity).navigateTo(R.id.action_FirstFragment_to_newsFragment)
        }

    }

}