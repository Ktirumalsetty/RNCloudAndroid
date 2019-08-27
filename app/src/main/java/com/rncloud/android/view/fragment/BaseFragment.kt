package com.rncloud.android.view.fragment

import android.app.Activity
import android.content.Context
import android.content.DialogInterface
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
import android.widget.Toast
import androidx.annotation.LayoutRes
import androidx.appcompat.app.AlertDialog
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment

abstract class  BaseFragment<DB: ViewDataBinding>:Fragment() {

    @get:LayoutRes
    protected abstract val layoutRes: Int

    protected lateinit var activity: Activity

    protected lateinit var binding:DB

    override fun onAttach(context: Context) {
        super.onAttach(context)
        this.activity = context as Activity
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        bindView(inflater,layoutRes,container)
        return super.onCreateView(inflater, container, savedInstanceState)
    }
    protected fun bindView(inflater: LayoutInflater, layoutId: Int, container: ViewGroup?) {
        binding = DataBindingUtil.inflate<DB>(inflater, layoutId, container, false)

    }

    inline fun <reified T> T.TAG(): String = T::class.java.simpleName

    protected fun showNoNetworkMsg(){
        Toast.makeText(activity, "No Network..", Toast.LENGTH_LONG).show()
    }

    protected fun showErrorRespMsg(){
        Toast.makeText(activity, "Something went wrong..", Toast.LENGTH_LONG).show()
    }

    protected fun showAlrtMsg(message:String){
        val dialogBuilder = AlertDialog.Builder(activity)

        // set message of alert dialog
        dialogBuilder.setMessage(message)
            // if the dialog is cancelable
            .setCancelable(false)
            // positive button text and action
            .setPositiveButton("OK", DialogInterface.OnClickListener {
                    dialog, id -> activity.finish()
            })
            // negative button text and action
            .setNegativeButton("Cancel", DialogInterface.OnClickListener {
                    dialog, id -> dialog.cancel()
            })

        // create dialog box
        val alert = dialogBuilder.create()
        // set title for alert dialog box
//        alert.setTitle("AlertDialogExample")
        // show alert dialog
        alert.show()
    }

//    protected fun showLog(message:String){
//        Log.d(TAG(),message)
//    }

    fun showProgressBar(view: FrameLayout){
        view.visibility = View.VISIBLE
    }

    fun hideProgressBar(view: FrameLayout){
        view.visibility = View.VISIBLE
    }
}