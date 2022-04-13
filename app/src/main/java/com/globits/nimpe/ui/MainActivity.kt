package com.globits.nimpe.ui

import android.annotation.SuppressLint
import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.content.Intent
import android.content.res.Configuration
import android.content.res.Resources
import android.os.Build
import android.os.Bundle
import android.util.DisplayMetrics
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.view.WindowManager
import android.widget.Switch
import androidx.annotation.RequiresApi
import androidx.appcompat.widget.Toolbar
import androidx.core.view.GravityCompat
import androidx.core.view.MenuItemCompat
import androidx.drawerlayout.widget.DrawerLayout
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.ui.*
import androidx.work.PeriodicWorkRequest
import androidx.work.WorkManager
import androidx.work.WorkRequest
import com.airbnb.mvrx.viewModel
import com.globits.nimpe.NimpeApplication
import com.globits.nimpe.R
import com.globits.nimpe.core.NimpeBaseActivity
import com.globits.nimpe.databinding.ActivityMainBinding
import com.globits.nimpe.ui.home.HomeViewState
import com.globits.nimpe.ui.home.HomeViewmodel
import com.globits.nimpe.ui.home.request_location.NotificationWorker
import com.google.android.material.navigation.NavigationView
import java.util.*
import java.util.concurrent.TimeUnit
import javax.inject.Inject


class MainActivity : NimpeBaseActivity<ActivityMainBinding>(), HomeViewmodel.Factory {

    private val homeViewModel: HomeViewmodel by viewModel()

    @Inject
    lateinit var homeViewModelFactory: HomeViewmodel.Factory

    private lateinit var navController: NavController
    private lateinit var appBarConfiguration: AppBarConfiguration
    private lateinit var drawerLayout: DrawerLayout
    private lateinit var toolbar: Toolbar

    @RequiresApi(Build.VERSION_CODES.N)
    override fun onCreate(savedInstanceState: Bundle?) {
        (applicationContext as NimpeApplication).nimpeComponent.inject(this)
        super.onCreate(savedInstanceState)
        setContentView(views.root)
        setupToolbar()
        setupDrawer()
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            createNotificationChannel()
        }
        val request: WorkRequest =
            PeriodicWorkRequest.Builder(
                NotificationWorker::class.java,
                10000,
                TimeUnit.MILLISECONDS
            )
                .build()
        WorkManager.getInstance().enqueue(request)
        homeViewModel.subscribe(this){
            if(it.isLoadding())
            {
                views.appBarMain.contentMain.waitingView.visibility=View.VISIBLE
            }else
                views.appBarMain.contentMain.waitingView.visibility=View.GONE
        }


    }

    @RequiresApi(Build.VERSION_CODES.O)
    private fun createNotificationChannel() {
        // Create the NotificationChannel, but only on API 26+ because
        // the NotificationChannel class is new and not in the support library
        val name = getString(R.string.app_name)
        val descriptionText = getString(R.string.channel_description)
        val importance = NotificationManager.IMPORTANCE_DEFAULT
        val channel = NotificationChannel(55.toString(), name, importance).apply {
            description = descriptionText
        }
        // Register the channel with the system
        val notificationManager: NotificationManager =
            getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        notificationManager.createNotificationChannel(channel)
    }

    override fun create(initialState: HomeViewState): HomeViewmodel {
        return homeViewModelFactory.create(initialState)
    }

    override fun getBinding(): ActivityMainBinding {
        return ActivityMainBinding.inflate(layoutInflater)
    }

    private fun setupToolbar() {
        toolbar = views.toolbar
        setSupportActionBar(toolbar)
        window.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN)
    }

    @SuppressLint("ResourceType")
    @RequiresApi(Build.VERSION_CODES.N)
    private fun setupDrawer() {
        drawerLayout = views.appBarMain.drawerLayout
        val navView: NavigationView = views.appBarMain.navView
        navController = findNavController(R.id.nav_host_fragment_content_main)

        appBarConfiguration = AppBarConfiguration(
            setOf(
                R.id.nav_HomeFragment,
                R.id.nav_newsFragment,
                R.id.nav_medicalFragment,
                R.id.nav_feedbackFragment,
                R.id.listNewsFragment,
                R.id.detailNewsFragment
            ), drawerLayout
        )

        setupActionBarWithNavController(navController, appBarConfiguration)
        navView.setupWithNavController(navController)

        // settings
        navView.setNavigationItemSelectedListener { menuItem ->

            val handled = NavigationUI.onNavDestinationSelected(menuItem, navController)

            //   if (!handled) {
            when (menuItem.itemId) {
                R.id.nav_change_langue -> {

                }
                else -> {
                    drawerLayout.closeDrawer(GravityCompat.START)
                    handled
                }
            }
            //}
            handled

        }
        val menu: Menu = navView.menu
        val menuItem = menu.findItem(R.id.nav_change_langue)
        val actionView: View = MenuItemCompat.getActionView(menuItem)

        val res: Resources = resources
        val dm: DisplayMetrics = res.displayMetrics
        val conf: Configuration = res.configuration

        var switcher = actionView as Switch
        var local = conf.locale
        print(local.toLanguageTag())

        switcher.isChecked = local.toLanguageTag() == "vi-VN"

        switcher.setOnCheckedChangeListener { _, isChecked ->
            var languageToLoad = "" // your language
            if (isChecked) {
                languageToLoad = "vi"
            } else {
                languageToLoad = "en"
            }
            val myLocale = Locale(languageToLoad)

            conf.setLocale(myLocale)
            res.updateConfiguration(conf, dm)
            startActivity(Intent(baseContext, MainActivity::class.java))
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        val navController = findNavController(R.id.nav_host_fragment_content_main)
        return navController.navigateUp(appBarConfiguration) || super.onSupportNavigateUp()
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.main, menu)
        return true
    }

    fun navigateTo(fragmentId: Int) {
        navController.navigate(fragmentId)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            android.R.id.home -> {
                if (drawerLayout.isOpen) {
                    drawerLayout.closeDrawer(GravityCompat.START)
                } else {
                    drawerLayout.openDrawer(GravityCompat.START)
                }
                return true
            }
            else -> {
                super.onOptionsItemSelected(item)
            }
        }
        return super.onOptionsItemSelected(item)
    }

}

