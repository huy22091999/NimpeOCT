package com.globits.nimpe.ui

import android.annotation.SuppressLint
import android.content.Intent
import android.content.res.Configuration
import android.content.res.Resources
import android.os.Build
import android.os.Bundle
import android.util.DisplayMetrics
import android.view.Menu
import android.view.MenuItem
import android.view.WindowManager
import androidx.annotation.RequiresApi
import androidx.appcompat.app.ActionBar.DISPLAY_HOME_AS_UP
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.widget.Toolbar
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import androidx.fragment.app.add
import androidx.fragment.app.commit
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.ui.*
import com.airbnb.mvrx.viewModel
import com.globits.nimpe.NimpeApplication
import com.globits.nimpe.R
import com.globits.nimpe.core.NimpeBaseActivity
import com.globits.nimpe.databinding.ActivityMainBinding
import com.globits.nimpe.ui.home.HomeFragment
import com.globits.nimpe.ui.home.HomeViewState
import com.globits.nimpe.ui.home.HomeViewmodel
import com.globits.nimpe.ui.news.NewsFragment
import com.google.android.material.navigation.NavigationView
import java.util.*
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
        /*  supportFragmentManager.commit {
              add<NewsFragment>(R.id.nav_host_fragment_content_main)
          }*/
//        print(homeViewModel.getUser().size.toString())
//        print(homeViewModel.getString())
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
        supportActionBar?.setHomeAsUpIndicator(R.drawable.ic_menu_togglr_drawer)
        supportActionBar?.setHomeButtonEnabled(true)


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
                R.id.nav_feedbackFragment

            ), drawerLayout
        )
//        var mDrawerToggle = ActionBarDrawerToggle(
//            this, drawerLayout,
//            R.drawable.ic_menu_togglr_drawer, //nav menu toggle icon
//            R.drawable.ic_menu_togglr_drawer
//        )
//        drawerLayout.setDrawerListener(mDrawerToggle)

        setupActionBarWithNavController(navController,appBarConfiguration)
        navView.setupWithNavController(navController)

        // settings
        navView.setNavigationItemSelectedListener { menuItem ->

            val handled = NavigationUI.onNavDestinationSelected(menuItem, navController)

            // if (!handled) {
            when (menuItem.itemId) {
                R.id.nav_change_langue -> {
                    val myLocale = Locale("en")
                    val res: Resources = resources
                    val dm: DisplayMetrics = res.displayMetrics
                    val conf: Configuration = res.configuration
                    conf.setLocale(myLocale)
                    res.updateConfiguration(conf, dm)
                    val refresh = Intent(this, MainActivity::class.java)
                    finish()
                    startActivity(refresh)
                }
            }
            // }

            drawerLayout.closeDrawer(GravityCompat.START)
            handled
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
                if(drawerLayout.isOpen)
                {
                    drawerLayout.closeDrawer(GravityCompat.START)
                }
                else{
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

