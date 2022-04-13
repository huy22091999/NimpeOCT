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
import com.globits.nimpe.core.NimpeBaseFragment
import com.globits.nimpe.data.model.Category
import com.globits.nimpe.databinding.FragmentNewsBinding
import com.globits.nimpe.ui.home.HomeViewAction
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
            val direction= list[position].id?.let {
                CategoryFragmentDirections.actionNavNewsFragmentToListNewsFragment(
                    it
                )
            }
            viewModel.setCategory(list[position])
            if (direction != null) {
                view.findNavController().navigate(direction)
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