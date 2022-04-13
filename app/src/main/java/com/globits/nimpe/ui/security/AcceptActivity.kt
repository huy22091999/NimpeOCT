package com.globits.nimpe.ui.security

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.globits.nimpe.R
import com.globits.nimpe.core.NimpeBaseActivity
import com.globits.nimpe.databinding.ActivityAcceptBinding
import com.globits.nimpe.ui.MainActivity

class AcceptActivity : NimpeBaseActivity<ActivityAcceptBinding>() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(views.root)
        views.acceptUse.setOnClickListener {
            startActivity(Intent(this,MainActivity::class.java))
        }
    }

    override fun getBinding(): ActivityAcceptBinding {
        return ActivityAcceptBinding.inflate(layoutInflater)
    }

}