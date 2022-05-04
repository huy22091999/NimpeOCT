package com.globits.nimpe.ui.category

import android.graphics.Path
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.findNavController
import com.airbnb.mvrx.Success
import com.airbnb.mvrx.activityViewModel
import com.airbnb.mvrx.withState
import com.globits.nimpe.R
import com.globits.nimpe.core.NimpeBaseFragment
import com.globits.nimpe.data.model.Category
import com.globits.nimpe.databinding.FragmentNewsBinding
import com.globits.nimpe.ui.MainActivity
import com.globits.nimpe.ui.home.HomeViewAction
import com.globits.nimpe.ui.home.HomeViewEvent
import com.globits.nimpe.ui.home.HomeViewmodel

class CategoryFragment :NimpeBaseFragment<FragmentNewsBinding>() {
    val viewModel:HomeViewmodel by activityViewModel()
    private lateinit var adapter: CategoryAdapter
    private lateinit var list: List<Category>

    override fun getBinding(inflater: LayoutInflater, container: ViewGroup?): FragmentNewsBinding {
        return FragmentNewsBinding.inflate(inflater,container,false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.handle(HomeViewAction.GetCategorys)
        list= listOf()
        adapter= CategoryAdapter(requireContext(),list)
        views.gridLayout.adapter=adapter
        views.gridLayout.setOnItemClickListener { _, _, position, _ ->
            viewModel.setCategory(list[position])
            (activity as MainActivity).navigateTo(R.id.action_nav_newsFragment_to_listNewsFragment)
        }
        viewModel.observeViewEvents {
            handleEvent(it)
        }
    }
    fun handleEvent(event: HomeViewEvent)
    {
        when(event)
        {
            is HomeViewEvent.ResetLanguege->{
                views.title.text=getString(R.string.category_theme)
                viewModel.handle(HomeViewAction.GetCategorys)
            }
        }

    }

    override fun invalidate() = withState(viewModel){
        when(it.asyncCategory)
        {
            is Success->{
                it.asyncCategory.invoke().let {
                    list=it.content!!
                    adapter= CategoryAdapter(requireContext(),list)
                    views.gridLayout.adapter=adapter
                    //adapter.notifyDataSetChanged()
                }

            }
        }
    }

}