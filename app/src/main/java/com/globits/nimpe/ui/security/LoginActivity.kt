package com.globits.nimpe.ui.security

import android.os.Bundle
import androidx.activity.viewModels
import androidx.fragment.app.add
import androidx.fragment.app.commit
import androidx.fragment.app.replace
import com.airbnb.mvrx.viewModel
import com.globits.nimpe.NimpeApplication
import com.globits.nimpe.R
import com.globits.nimpe.core.NimpeBaseActivity
import com.globits.nimpe.databinding.ActivityLoginBinding
import com.globits.nimpe.ui.home.HomeViewmodel
import com.globits.nimpe.utils.addFragmentToBackstack
import javax.inject.Inject

class LoginActivity : NimpeBaseActivity<ActivityLoginBinding>(), SecurityViewModel.Factory {

    val viewModel: SecurityViewModel by viewModel()
    @Inject
    lateinit var securityviewmodelFactory: SecurityViewModel.Factory
    override fun onCreate(savedInstanceState: Bundle?) {
        (applicationContext as NimpeApplication).nimpeComponent.inject(this)
        super.onCreate(savedInstanceState)
        setContentView(views.root)
        supportFragmentManager.commit {
            add<LoginFragment>(R.id.frame_layout)
        }
        viewModel.observeViewEvents {
            if (it != null) {
                handleEvent(it)
            }
        }
        print(viewModel.getString())

    }

    private fun handleEvent(event: SecurityViewEvent) {
        when(event){
            is SecurityViewEvent.ReturnSigninEvent ->{
                addFragmentToBackstack(R.id.frame_layout,SigninFragment::class.java)
//                supportFragmentManager.commit {
//                    replace<SigninFragment>(R.id.frame_layout)
//                }
            }
            is SecurityViewEvent.ReturnResetpassEvent->{
                addFragmentToBackstack(R.id.frame_layout,ResetPasswordFragment::class.java)
//                supportFragmentManager.commit {
//                    replace<ResetPasswordFragment>(R.id.frame_layout)
//                }
            }

        }
    }

    override fun getBinding(): ActivityLoginBinding {
        return ActivityLoginBinding.inflate(layoutInflater)
    }

    override fun create(initialState: SecurityViewState): SecurityViewModel {
        return securityviewmodelFactory.create(initialState)
    }

}