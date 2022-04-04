package com.globits.nimpe.ui.feedback

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.globits.nimpe.R
import com.globits.nimpe.core.NimpeBaseFragment
import com.globits.nimpe.databinding.FragmentFeedbackBinding

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [FeedbackFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class FeedbackFragment : NimpeBaseFragment<FragmentFeedbackBinding>() {
    

    override fun getBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentFeedbackBinding {
        return FragmentFeedbackBinding.inflate(inflater,container,false)
    }


}