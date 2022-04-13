package com.globits.nimpe.ui.category

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.globits.nimpe.R
import com.globits.nimpe.core.NimpeBaseFragment
import com.globits.nimpe.databinding.FragmentDetailNewsBinding


class DetailNewsFragment : NimpeBaseFragment<FragmentDetailNewsBinding>() {
    private var category_id: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            category_id = it.getString("category_id").toString()
        }
    }

    override fun getBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentDetailNewsBinding {
        return FragmentDetailNewsBinding.inflate(inflater,container,false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        views.tv.text=category_id
    }

}