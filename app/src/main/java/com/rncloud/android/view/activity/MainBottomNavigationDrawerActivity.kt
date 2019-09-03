package com.rncloud.android.view.activity

import android.os.Bundle
import android.view.MenuItem
import com.google.android.material.bottomnavigation.BottomNavigationView
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.core.view.GravityCompat
import com.rncloud.android.R
import com.google.android.material.navigation.NavigationView
import com.rncloud.android.databinding.ActivityMainBottomNavigationDrawerBinding

import androidx.lifecycle.ViewModelProviders
import com.rncloud.android.view.activity.base.BaseAppCompatActivity
import com.rncloud.android.view.fragment.JobsFragment
import com.rncloud.android.view.fragment.ProfileFragment

import com.rncloud.android.view.fragment.ScheduleFragment
import com.rncloud.android.viewmodel.MainBottomNavigationDrawerViewModel


class MainBottomNavigationDrawerActivity : BaseAppCompatActivity<ActivityMainBottomNavigationDrawerBinding>(),NavigationView.OnNavigationItemSelectedListener {

    override fun getLayoutRes(): Int {
        return R.layout.activity_main_bottom_navigation_drawer
    }

    private lateinit var viewModel:MainBottomNavigationDrawerViewModel

//    val navController by lazy { findNavController(R.id.nav_host_fragment) }
//    private lateinit var textMessage: TextView

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        // Handle navigation view item clicks here.
        val id = item.itemId
        if (id == R.id.nav_my_preferences) {
//            navigateToSettingsActivity()
        } else if (id == R.id.nav_signout) {
//            showLogoutAlert()
        }

        binding.drawerLayout.closeDrawer(GravityCompat.START)
        return true
    }


    private val onNavigationItemSelectedListener = BottomNavigationView.OnNavigationItemSelectedListener { item ->
        loadFragment(item.itemId)
        true
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        setContentView(R.layout.activity_main_bottom_navigation_drawer)
        initialiseView()
        initialiseViewModel()

//        val bottomNavigationView: BottomNavigationView = findViewById(R.id.bottom_nav_view)
        binding.included.bottomNavView.setOnNavigationItemSelectedListener(onNavigationItemSelectedListener)
        binding.included.bottomNavView.selectedItemId=R.id.navigation_home

    }


    /*
    * Initialising the View using Data Binding
    * */
    private fun initialiseView() {
        bindView(getLayoutRes())
        binding.navView.setNavigationItemSelectedListener (this)
        initToolbar()
        val toggle = ActionBarDrawerToggle(
            this, binding.drawerLayout, binding.toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close
        )
        binding.drawerLayout.addDrawerListener(toggle)
        toggle.syncState()
//        moviesListAdapter = MoviesListAdapter(this)
//        binding.moviesList.layoutManager = LinearLayoutManager(applicationContext, LinearLayoutManager.HORIZONTAL, false)
//        binding.moviesList.adapter = moviesListAdapter
//
//        val startSnapHelper = PagerSnapHelper(
//            object : RecyclerSnapItemListener {
//                override fun onItemSnap(position: Int) {
//                    val movie = moviesListAdapter.getItem(position)
//                    binding.overlayLayout.updateCurrentBackground(movie.getFormattedPosterPath())
//                }
//            }
//        )
//        startSnapHelper.attachToRecyclerView(binding.moviesList)
    }

    /*
     * Step 3: Initialising the ViewModel class here.
     * We are adding the ViewModelFactory class here.
     * We are observing the LiveData
     * */
    private fun initialiseViewModel() {
//        loginViewModel = ViewModelProviders.of(this, viewModelFactory).get(MainBottomNavigationDrawerViewModel::class.java!!)
        viewModel = ViewModelProviders.of(this).get(MainBottomNavigationDrawerViewModel::class.java)

    }

    private fun initToolbar() {

        setSupportActionBar(binding.toolbar)
//        getSupportActionBar()?.setDisplayHomeAsUpEnabled(true);
//        getSupportActionBar()?.setHomeButtonEnabled(true);

    }

    private fun loadFragment(itemId: Int) {
        val tag = itemId.toString()
        var fragment = supportFragmentManager.findFragmentByTag(tag) ?: when (itemId) {
            R.id.navigation_home -> {
                binding.toolbar.title = "Schedule"
                ScheduleFragment.newInstance()
            }
            R.id.navigation_jobs -> {
                binding.toolbar.title = "Jobs"
                JobsFragment.newInstance()
            }
            R.id.navigation_profile -> {
                binding.toolbar.title = "Profile"
                ProfileFragment.newInstance()
            }
            else -> {
                null
            }
        }

        if (fragment != null) {
            val transaction = supportFragmentManager.beginTransaction()

            if (viewModel.lastActiveFragmentTag != null) {
                val lastFragment = supportFragmentManager.findFragmentByTag(viewModel.lastActiveFragmentTag)
                if (lastFragment != null)
                    transaction.hide(lastFragment)
            }

            if (!fragment.isAdded) {
                transaction.add(R.id.fragment_container, fragment, tag)
            }
            else {
                transaction.show(fragment)
            }

            transaction.commit()
            viewModel.lastActiveFragmentTag = tag
        }

    }

    override fun onBackPressed() {
        if (binding.drawerLayout.isDrawerOpen(GravityCompat.START)) {
            binding.drawerLayout.closeDrawer(GravityCompat.START)
        } else {
            super.onBackPressed()
        }
    }
}
