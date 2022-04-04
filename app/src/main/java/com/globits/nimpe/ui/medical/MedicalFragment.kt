package com.globits.nimpe.ui.medical


import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.ViewGroup
import com.globits.nimpe.core.NimpeBaseFragment
import com.globits.nimpe.databinding.FragmentMedicalBinding

/**
 * A simple [Fragment] subclass.
 * Use the [MedicalFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class MedicalFragment : NimpeBaseFragment<FragmentMedicalBinding>() {
    override fun getBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentMedicalBinding {
        return FragmentMedicalBinding.inflate(inflater,container,false)
    }

}