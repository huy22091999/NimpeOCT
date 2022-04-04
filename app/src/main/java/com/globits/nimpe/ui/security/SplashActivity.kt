package com.globits.nimpe.ui.security

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.globits.nimpe.R
import com.globits.nimpe.core.NimpeBaseActivity
import com.globits.nimpe.databinding.ActivitySplashBinding
import com.globits.nimpe.ui.MainActivity

class SplashActivity : NimpeBaseActivity<ActivitySplashBinding>() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(views.root)
        startActivity(Intent(this,MainActivity::class.java))
        finish()
    }

    override fun getBinding(): ActivitySplashBinding {
        return ActivitySplashBinding.inflate(layoutInflater)
    }
}