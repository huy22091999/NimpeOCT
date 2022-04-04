package com.globits.nimpe.ui.security


import android.view.LayoutInflater
import android.view.ViewGroup
import com.globits.nimpe.core.NimpeBaseFragment
import com.globits.nimpe.databinding.FragmentResetPasswordBinding


class ResetPasswordFragment :NimpeBaseFragment<FragmentResetPasswordBinding>() {
    override fun getBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentResetPasswordBinding {
        return FragmentResetPasswordBinding.inflate(inflater,container,false)
    }

}