package com.globits.nimpe.ui.medical

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import com.airbnb.mvrx.viewModel
import com.globits.nimpe.NimpeApplication
import com.globits.nimpe.R
import com.globits.nimpe.core.NimpeBaseActivity
import com.globits.nimpe.databinding.ActivityMedicalBinding
import com.globits.nimpe.ui.security.SecurityViewAction
import com.globits.nimpe.ui.security.SecurityViewModel
import com.globits.nimpe.ui.security.SecurityViewState
import javax.inject.Inject

class MedicalActivity : NimpeBaseActivity<ActivityMedicalBinding>(),SecurityViewModel.Factory {

    val viewModel: SecurityViewModel by viewModel()
    @Inject
    lateinit var securityviewmodelFactory: SecurityViewModel.Factory

    override fun onCreate(savedInstanceState: Bundle?) {
        (applicationContext as NimpeApplication).nimpeComponent.inject(this)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_medical)
        viewModel.handle(SecurityViewAction.GetUserCurrent)
    }

    override fun getBinding(): ActivityMedicalBinding {
        return ActivityMedicalBinding.inflate(layoutInflater)
    }

    override fun create(initialState: SecurityViewState): SecurityViewModel {
        return securityviewmodelFactory.create(initialState)
    }
}