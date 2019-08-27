package com.rncloud.android.view.activity

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.rncloud.android.databinding.ActivityLoginBinding
import com.rncloud.android.viewmodel.LoginViewModel
import androidx.lifecycle.ViewModelProviders
import androidx.databinding.DataBindingUtil
import androidx.databinding.Observable
import androidx.lifecycle.Observer
import com.rncloud.android.R
import com.rncloud.android.api.APIService
import com.rncloud.android.common.AppPreferences
import com.rncloud.android.model.LoginDataModel
import com.rncloud.android.model.LoginResponse
import com.rncloud.android.model.XLoginController
import com.rncloud.android.view.activity.base.BaseAppCompatActivity

class LoginActivity: BaseAppCompatActivity<ActivityLoginBinding>() {

    override fun getLayoutRes(): Int {
        return R.layout.activity_login
    }

//    override val layoutRes: Int
//        get() = R.layout.activity_login

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
//    private lateinit var binding: ActivityLoginBinding

    override fun onCreate(savedInstanceState: Bundle?) {

//        AndroidInjection.inject(this)
        super.onCreate(savedInstanceState)

        initialiseView()
        initialiseViewModel()
        binding.login.setOnClickListener(View.OnClickListener {

            if (isLoginValid()){
                showProgress()
//
                loginViewModel.login(LoginDataModel(binding.email.text.toString()))
            }

        })

        binding.signup.setOnClickListener(View.OnClickListener {
            startActivity(Intent(this@LoginActivity,SignUpFormActivity::class.java))
        })
    }

    /*
    * Initialising the View using Data Binding
    * */
    private fun initialiseView() {
        bindView(getLayoutRes())
//        binding = DataBindingUtil.setContentView(this, R.layout.activity_login)

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

        loginViewModel.getLoginRespLiveData().observe(this, Observer { resource ->
            hideProgress()
            Log.d(TAG(),"getLoginRespLiveData"+resource)
            if (resource!=null) {
                if(!resource.hasError){
                    saveLoginRespToPrefs(resource.Result.xLoginController)
                }else{
                    showAlrtMsg("response failed ...")
                }
                startActivity(Intent(this@LoginActivity,MainBottomNavigationDrawerActivity::class.java))
            } else {
                showErrorRespMsg()
                startActivity(Intent(this@LoginActivity,MainBottomNavigationDrawerActivity::class.java))

            }
        })



//        loginViewModel.getLoginRespLiveData().observe(this,{
//            hideProgress()
//            if(it.isSuccessful){
//                val loginResponse: LoginResponse? = it.body
//                Log.d(TAG(),loginResponse.toString())
//            }else{
//                startActivity(Intent(this@LoginActivity,MainBottomNavigationDrawerActivity::class.java))
//            }
//        })

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

    private fun saveLoginRespToPrefs(loginController:XLoginController){
        AppPreferences.actorCode = loginController.ActorCode
        AppPreferences.authGenKey = loginController.AuthGenKEY
        AppPreferences.userName = loginController.UserName
        AppPreferences.userType = loginController.UserType

    }

    @SuppressLint("NewApi")
    fun isLoginValid():Boolean{
        if(binding.email.text!!.isEmpty()){
            binding.email.error = "Email is Required"
//            binding.emailTextInputLayout.focusable = View.FOCUSABLE
            binding.emailTextInputLayout.requestFocus()
            return false
        }else if (binding.password.text!!.isEmpty()){
            binding.password.error = "Password is Required"
//            binding.password.focusable = View.FOCUSABLE
            binding.password.requestFocus()

            return false
        }else{
            return true
        }

    }

    private fun showProgress() {
        binding.included.progress.visibility = View.VISIBLE
//        binding.progressBarHolder.visibility = View.VISIBLE
    }

    private fun hideProgress() {
        binding.included.progress.visibility = View.GONE
//        binding.progressBarHolder.visibility = View.GONE

    }


}