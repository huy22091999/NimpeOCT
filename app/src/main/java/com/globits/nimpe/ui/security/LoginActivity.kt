package com.globits.nimpe.ui.security

import android.os.Bundle
import com.globits.nimpe.core.NimpeBaseActivity
import com.globits.nimpe.databinding.ActivityLoginBinding

class LoginActivity : NimpeBaseActivity<ActivityLoginBinding>() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(views.root)

    }

    override fun getBinding(): ActivityLoginBinding {
        return ActivityLoginBinding.inflate(layoutInflater)
    }
}