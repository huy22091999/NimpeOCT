package com.globits.nimpe.ui.security

import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import com.airbnb.mvrx.Fail
import com.airbnb.mvrx.Success
import com.airbnb.mvrx.viewModel
import com.globits.nimpe.NimpeApplication
import com.globits.nimpe.core.NimpeBaseActivity
import com.globits.nimpe.data.network.SessionManager
import com.globits.nimpe.databinding.ActivitySplashBinding
import com.globits.nimpe.ui.MainActivity
import javax.inject.Inject

class SplashActivity : NimpeBaseActivity<ActivitySplashBinding>(),SecurityViewModel.Factory {

    val viewmodel: SecurityViewModel by viewModel()

    @Inject
    lateinit var securityviewmodelFactory: SecurityViewModel.Factory

    override fun onCreate(savedInstanceState: Bundle?) {
        (applicationContext as NimpeApplication).nimpeComponent.inject(this)
        super.onCreate(savedInstanceState)
        setContentView(views.root)
    }

    override fun onResume() {
        super.onResume()
        viewmodel.handle(SecurityViewAction.GetUserCurrent)
        viewmodel.subscribe(this){
            when(it.userCurrent)
            {
                is Success ->{
                    startActivity(Intent(this,MainActivity::class.java))
                }
                is Fail->
                {
                    val sessionManager =SessionManager(applicationContext)
                    sessionManager.saveAuthToken("")
                    startActivity(Intent(this,LoginActivity::class.java))
                }
            }
        }
    }


    override fun getBinding(): ActivitySplashBinding {
        return ActivitySplashBinding.inflate(layoutInflater)
    }

    override fun create(initialState: SecurityViewState): SecurityViewModel {
        return securityviewmodelFactory.create(initialState)
    }


}