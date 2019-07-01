package com.rncloud.android.view.activity

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.rncloud.android.databinding.ActivityLoginBinding
import com.rncloud.android.viewmodel.LoginViewModel
import androidx.lifecycle.ViewModelProviders
import androidx.databinding.DataBindingUtil
import com.rncloud.android.R

class LoginActivity: AppCompatActivity() {

//    override val layoutRes: Int
//        get() = R.layout.activity_login
//
////    override val bindingVariable: Int
////        get() = BR.viewModel
//
//    override fun getViewModel(): Class<LoginViewModel> {
//
//        return LoginViewModel::class.java
//    }

    /*
     * Step 1: Here as mentioned in Step 5, we need to
     * inject the ViewModelFactory. The ViewModelFactory class
     * has a list of ViewModels and will provide
     * the corresponding ViewModel in this activity
     * */
//    @Inject
//    internal lateinit var viewModelFactory: ViewModelFactory

    /*
    * This is our ViewModel class
    * */
    lateinit var loginViewModel: LoginViewModel

    /*
     * I am using DataBinding
     * */
    private lateinit var binding: ActivityLoginBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        /*
         * Step 2: Remember in our ActivityModule, we
         * defined MainActivity injection? So we need
         * to call this method in order to inject the
         * ViewModelFactory into our Activity
         * */
//        AndroidInjection.inject(this)
        super.onCreate(savedInstanceState)

        initialiseView()
        initialiseViewModel()
        binding.login.setOnClickListener(View.OnClickListener {

            if (isLoginValid())
            startActivity(Intent(this@LoginActivity,MainBottomNavigationDrawerActivity::class.java))

//            viewModel.login(LoginDataModel(dataBinding.email.text.toString(),dataBinding.password.text.toString()))
        })

        binding.signup.setOnClickListener(View.OnClickListener {
            startActivity(Intent(this@LoginActivity,SignUpFormActivity::class.java))
        })
    }

    /*
    * Initialising the View using Data Binding
    * */
    private fun initialiseView() {
        binding = DataBindingUtil.setContentView(this, R.layout.activity_login)

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
        loginViewModel = ViewModelProviders.of(this).get(LoginViewModel::class.java)

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

    @SuppressLint("NewApi")
    fun isLoginValid():Boolean{
        if(binding.email.text!!.isEmpty()){
            binding.email.error = "Email is Required"
            binding.email.focusable = View.FOCUSABLE
            return false
        }else if (binding.password.text!!.isEmpty()){
            binding.password.error = "Password is Required"
            binding.password.focusable = View.FOCUSABLE

            return false
        }else{
            return true
        }

    }
}