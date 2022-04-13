package com.globits.nimpe.ui.security

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.airbnb.mvrx.Success
import com.airbnb.mvrx.activityViewModel
import com.airbnb.mvrx.withState
import com.globits.nimpe.core.NimpeBaseFragment
import com.globits.nimpe.data.network.SessionManager
import com.globits.nimpe.databinding.FragmentLoginBinding
import com.globits.nimpe.ui.MainActivity
import timber.log.Timber
import javax.inject.Inject


class LoginFragment @Inject constructor() : NimpeBaseFragment<FragmentLoginBinding>() {
    val viewModel:SecurityViewModel by activityViewModel()
    override fun getBinding(inflater: LayoutInflater, container: ViewGroup?): FragmentLoginBinding {
        return FragmentLoginBinding.inflate(inflater,container,false)
    }
    lateinit var username:String
    lateinit var password:String
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        views.loginSubmit.setOnClickListener {
            loginSubmit()
        }
        views.labelSigin.setOnClickListener {
            viewModel.handleReturnSignin()
        }
        views.labelResetPassword.setOnClickListener {
            viewModel.handleReturnResetPass()
        }
        super.onViewCreated(view, savedInstanceState)
    }
    fun loginSubmit()
    {
        username=views.username.text.toString()
        password=views.password.text.toString()
        viewModel.handle(SecurityViewAction.LogginAction(username,password))
    }

    override fun invalidate(): Unit = withState(viewModel){
        when(it.asyncLogin){
            is Success ->{
                it.asyncLogin.invoke()?.let { token->
                    val sessionManager = context?.let { it1 -> SessionManager(it1.applicationContext) }
                    token.accessToken?.let { it1 -> sessionManager!! .saveAuthToken(it1) }
                    viewModel.handle(SecurityViewAction.SaveTokenAction(token!!))
                }
                startActivity(Intent(requireContext(),AcceptActivity::class.java))
                activity?.finish()
            }
            else->{

            }
        }

    }
}