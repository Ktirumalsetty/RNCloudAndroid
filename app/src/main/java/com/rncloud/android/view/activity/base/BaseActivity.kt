package com.rncloud.android.view.activity.base

import android.content.DialogInterface
import android.icu.lang.UCharacter.GraphemeClusterBreak.T
import android.os.Bundle
import android.os.Handler
import android.util.Log
import android.view.MenuItem
import android.view.View
import android.widget.FrameLayout
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProviders
import com.google.android.material.snackbar.Snackbar
import com.rncloud.android.common.ConnectionLiveData


/**
 * Created by KondalRao Tirumalasetty on 8/27/2019.
 */
abstract class BaseActivity<VM:ViewModel,DB: ViewDataBinding> : AppCompatActivity(){

    protected var TAG: String? = null

    open val BASE_TAG ="BaseActivity"

    protected lateinit var binding:DB

    protected lateinit var viewModel:VM

    private var mSnackBar: Snackbar? = null

//    var connectivityReceiver:ConnectivityReceiver = ConnectivityReceiver()

//    private var connectivityManager: ConnectivityManager?=null

    abstract fun getLayoutRes(): Int

    abstract fun getViewModel(): Class<VM>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.d(BASE_TAG,"onCreate")
//        TAG = BaseActivity::class.qualifiedName
        bindView(getLayoutRes())

    }

    override fun onStart() {
        super.onStart()
        val connectionLiveData = ConnectionLiveData(this)
        connectionLiveData.observe(this, Observer {
            Log.d(TAG,"connectionLiveData.observe")
            // do whatever you want with network connectivity change
            showMessage(it)
        })
    }

    inline fun <reified T> T.TAG(): String = T::class.java.simpleName

    protected fun bindView(layoutId: Int) {
        binding = DataBindingUtil.setContentView(this, layoutId)
//        viewModel = ViewModelProviders.of(this, viewModelFactory).get(getViewModel())
        viewModel = ViewModelProviders.of(this).get(getViewModel())
//        this.viewModel = if (mViewModel == null) getViewModel() else mViewModel
//        binding.setVariable(BR.vi, mViewModel)

        binding.lifecycleOwner = this

    }

    override fun onResume() {
        super.onResume()
        Log.d(BASE_TAG,"onResume")

//        registerReceiver(connectivityReceiver,  IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION))

//        ConnectivityReceiver.connectivityReceiverListener = this

    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        when (item?.itemId) {
            android.R.id.home -> {
                finish()
                return true
            }

        }
        return super.onOptionsItemSelected(item)

    }

    protected fun showMessage(isConnected: Boolean) {
        if (!isConnected) {

            val messageToUser = "You are offline now."

            mSnackBar = Snackbar.make(
                findViewById(android.R.id.content),
//                findViewById(com.google.android.material.R.id.snackbar_text),
                messageToUser,
                Snackbar.LENGTH_INDEFINITE
            ) //Assume "rootLayout" as the root layout of every activity.

            mSnackBar?.show()
            Toast.makeText(this, messageToUser, Toast.LENGTH_LONG).show()
        } else {
            val messageToUser = "You are online now."
            if (mSnackBar !=null && mSnackBar!!.isShown){
                val snackBarText = mSnackBar!!.getView().findViewById<TextView>(com.google.android.material.R.id.snackbar_text)
                snackBarText.setText(messageToUser)
                snackBarText.setTextColor(ContextCompat.getColor(this,android.R.color.white))
                snackBarText.setBackgroundColor(ContextCompat.getColor(this,android.R.color.holo_green_dark));

                Handler().postDelayed({

                    mSnackBar?.dismiss()
                },2000)

                Toast.makeText(this, messageToUser, Toast.LENGTH_LONG).show()
            }


        }
    }

    protected fun showNoNetworkMsg(){
        Toast.makeText(this, "No Network..", Toast.LENGTH_LONG).show()
    }

    protected fun showErrorRespMsg(){
        Toast.makeText(this, "Something went wrong..", Toast.LENGTH_LONG).show()
    }


    fun showProgressBar(view: FrameLayout){
        view.visibility = View.VISIBLE
    }

    fun hideProgressBar(view: FrameLayout){
        view.visibility = View.VISIBLE
    }

    override fun onDestroy() {
        super.onDestroy()
//        if (connectivityReceiver!=null)
//            unregisterReceiver(connectivityReceiver)

    }
}