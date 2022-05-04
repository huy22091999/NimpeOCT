package com.globits.nimpe.ui.security

import android.content.Intent
import android.location.Location
import android.os.Bundle
import android.util.Base64
import com.airbnb.mvrx.viewModel
import com.globits.nimpe.NimpeApplication
import com.globits.nimpe.core.NimpeBaseActivity
import com.globits.nimpe.data.model.Patient
import com.globits.nimpe.data.model.Vector
import com.globits.nimpe.data.network.RemoteDataSource
import com.globits.nimpe.databinding.ActivitySplashBinding
import com.google.gson.Gson
import okhttp3.ResponseBody
import org.json.JSONArray
import org.json.JSONObject
import org.json.JSONTokener
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.nio.charset.Charset
import javax.crypto.Cipher
import javax.crypto.spec.IvParameterSpec
import javax.crypto.spec.SecretKeySpec
import javax.inject.Inject


class SplashActivity : NimpeBaseActivity<ActivitySplashBinding>(), SecurityViewModel.Factory {

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
        Thread.sleep(1000)
        startActivity(Intent(this,AcceptActivity::class.java))
        finish()

    }
    override fun getBinding(): ActivitySplashBinding {
        return ActivitySplashBinding.inflate(layoutInflater)
    }

    override fun create(initialState: SecurityViewState): SecurityViewModel {
        return securityviewmodelFactory.create(initialState)
    }
}