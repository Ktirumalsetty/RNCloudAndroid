package com.rncloud.android.view.activity

import android.os.Bundle
import android.view.MenuItem
import com.google.android.material.bottomnavigation.BottomNavigationView
import androidx.appcompat.app.AppCompatActivity
import android.widget.TextView
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.core.view.GravityCompat
import com.rncloud.android.R
import androidx.databinding.DataBindingUtil
import com.google.android.material.navigation.NavigationView
import com.rncloud.android.databinding.ActivityMainBottomNavigationDrawerBinding

import androidx.fragment.app.Fragment
import com.rncloud.android.view.fragment.JobsFragment
import com.rncloud.android.view.fragment.ProfileFragment

import com.rncloud.android.view.fragment.ScheduleFragment
import kotlinx.android.synthetic.main.app_bar_main_drawer.*


class MainBottomNavigationDrawerActivity : AppCompatActivity(),NavigationView.OnNavigationItemSelectedListener {

    private lateinit var binding: ActivityMainBottomNavigationDrawerBinding

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

        var fragment: Fragment? = null

        when (item.itemId) {
            R.id.navigation_home -> {
                fragment = ScheduleFragment.newInstance()
                binding.toolbar.title = "Schedule"
            }
            R.id.navigation_jobs -> {
                fragment = JobsFragment.newInstance()
                binding.toolbar.title = "Jobs"
            }
            R.id.navigation_profile -> {
                fragment = ProfileFragment.newInstance()
                binding.toolbar.title = "Profile"

            }
        }
        loadFragment(fragment)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        setContentView(R.layout.activity_main_bottom_navigation_drawer)
        initialiseView()
        initialiseViewModel()
        val bottomNavigationView: BottomNavigationView = findViewById(R.id.bottom_nav_view)

        bottomNavigationView.setOnNavigationItemSelectedListener(onNavigationItemSelectedListener)
        bottomNavigationView.setSelectedItemId(R.id.navigation_home);


    }

    /*
    * Initialising the View using Data Binding
    * */
    private fun initialiseView() {
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main_bottom_navigation_drawer)
        initToolbar()
//        val drawer = findViewById(R.id.drawer_layout) as DrawerLayout
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
//        loginViewModel = ViewModelProviders.of(this, viewModelFactory).get(LoginViewModel::class.java!!)
//        loginViewModel = ViewModelProviders.of(this).get(LoginViewModel::class.java)

//        movieListViewModel.getMoviesLiveData().observe(this, { resource ->
//            if (resource.isLoading()) {
//                displayLoader()
//
//            } else if (!resource.data.isEmpty()) {
//                updateMoviesList(resource.data)
//
//            } else
//                handleErrorResponse()
//        })
//
//        /* Fetch movies list  */
//        movieListViewModel.loadMoreMovies()
    }

    private fun initToolbar() {

        setSupportActionBar(binding.toolbar)
        getSupportActionBar()?.setDisplayHomeAsUpEnabled(true);
//        getSupportActionBar()?.setHomeButtonEnabled(true);

    }

    private fun loadFragment(fragment: Fragment?): Boolean {
        //switching fragment
        if (fragment != null) {
            supportFragmentManager
                .beginTransaction()
                .replace(R.id.fragment_container, fragment!!)
                .commit()
            return true
        }
        return false
    }
}
