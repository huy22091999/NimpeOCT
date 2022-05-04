package com.globits.nimpe.ui.category

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.airbnb.mvrx.activityViewModel
import com.globits.nimpe.R
import com.globits.nimpe.core.NimpeBaseFragment
import com.globits.nimpe.data.model.Category
import com.globits.nimpe.databinding.FragmentDetailNewsBinding
import com.globits.nimpe.databinding.FragmentListNewsBinding
import com.globits.nimpe.ui.MainActivity
import com.globits.nimpe.ui.home.HomeViewEvent
import com.globits.nimpe.ui.home.HomeViewmodel
import com.globits.nimpe.ui.medical.MedicalAdapter
import com.globits.nimpe.ui.medical.MedicalLoadStateAdapter
import kotlinx.coroutines.flow.collectLatest


class ListNewsFragment :NimpeBaseFragment<FragmentListNewsBinding>() {
    private lateinit var pagingAdapter: NewsAdapter
    private var category:Category? = null
    val viewModel:HomeViewmodel by activityViewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        category=viewModel.getCategory()
    }

    override fun getBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentListNewsBinding {
        return FragmentListNewsBinding.inflate(inflater,container,false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.observeViewEvents {
            handleEvent(it)
        }

        views.back.setOnClickListener {
            findNavController().popBackStack()
        }
        populateData()
    }
    fun populateData()
    {
        views.titleCategory.text=category?.title
        pagingAdapter =NewsAdapter(requireContext()){_,item ->
            viewModel.setNew(item)
            (activity as MainActivity).navigateTo(R.id.action_listNewsFragment_to_detailNewsFragment)
        }
        views.recyclerViewNews.apply {
            layoutManager = LinearLayoutManager(requireContext())

        }
        views.recyclerViewNews.adapter = pagingAdapter.withLoadStateHeaderAndFooter(
            header = MedicalLoadStateAdapter { pagingAdapter.retry() },
            footer = MedicalLoadStateAdapter { pagingAdapter.retry() }
        )
        lifecycleScope.launchWhenCreated  {
            viewModel.getNews(viewModel.language,category!!).collectLatest { pagingData ->
                pagingAdapter.submitData(pagingData)
            }
        }
    }

    private fun handleEvent(it: HomeViewEvent) {
        when(it)
        {
            is HomeViewEvent.ResetLanguege->{
                populateData()
            }
        }
    }
}