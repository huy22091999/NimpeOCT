package com.globits.nimpe.ui.medical


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.lifecycleScope
import androidx.paging.LoadState
import androidx.recyclerview.widget.LinearLayoutManager
import com.airbnb.mvrx.activityViewModel
import com.globits.nimpe.R
import com.globits.nimpe.core.NimpeBaseFragment
import com.globits.nimpe.databinding.FragmentMedicalBinding
import com.globits.nimpe.ui.home.HomeViewEvent
import com.globits.nimpe.ui.home.HomeViewmodel
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

/**
 * A simple [Fragment] subclass.
 * Use the [MedicalFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class MedicalFragment : NimpeBaseFragment<FragmentMedicalBinding>() {

    val homeViewmodel: HomeViewmodel by activityViewModel()
    private lateinit var pagingAdapter: MedicalAdapter
    override fun getBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentMedicalBinding {
        return FragmentMedicalBinding.inflate(inflater, container, false)
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        pagingAdapter = MedicalAdapter(requireContext())
        homeViewmodel.observeViewEvents {
            handleEvent(it)
        }
        views.recyclerView.apply {
            layoutManager = LinearLayoutManager(requireContext())

        }
        views.recyclerView.adapter = pagingAdapter.withLoadStateHeaderAndFooter(
            header = MedicalLoadStateAdapter { pagingAdapter.retry() },
            footer = MedicalLoadStateAdapter { pagingAdapter.retry() }
        )
        lifecycleScope.launchWhenCreated  {
            homeViewmodel.getHealthOrgs(homeViewmodel.language).collectLatest { pagingData ->
                pagingAdapter.submitData(pagingData)
            }
        }

    }
    private fun handleEvent(it: HomeViewEvent) {
        when(it)
        {
            is HomeViewEvent.ResetLanguege->{
                lifecycleScope.launchWhenCreated  {
                    homeViewmodel.getHealthOrgs(homeViewmodel.language).collectLatest { pagingData ->
                        pagingAdapter.submitData(pagingData)
                    }
                }
            }
        }
    }

}