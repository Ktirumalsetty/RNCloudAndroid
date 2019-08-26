package com.rncloud.android.common

import android.annotation.TargetApi
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Context.CONNECTIVITY_SERVICE
import android.content.Intent
import android.content.IntentFilter
import android.net.ConnectivityManager
import android.net.ConnectivityManager.CONNECTIVITY_ACTION
import android.net.Network
import android.net.NetworkCapabilities.TRANSPORT_CELLULAR
import android.net.NetworkCapabilities.TRANSPORT_WIFI
import android.net.NetworkInfo
import android.net.NetworkRequest
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.lifecycle.LiveData



    class ConnectionLiveData(val context: Context) : LiveData<Boolean>(){

//        var  intentFilter = IntentFilter(CONNECTIVITY_ACTION)
//        private var  connectivityManager = context.getSystemService(CONNECTIVITY_SERVICE) as ConnectivityManager
//        private lateinit var networkCallback : NetworkCallback
//
//        init {
//            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
//                networkCallback = NetworkCallback(this)
//            }
//        }
//
//        override fun onActive() {
//            super.onActive()
//            updateConnection()
//            when {
//                Build.VERSION.SDK_INT >= Build.VERSION_CODES.N -> connectivityManager.registerDefaultNetworkCallback(networkCallback)
//                Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP -> {
//                    val builder = NetworkRequest.Builder().addTransportType(TRANSPORT_CELLULAR).addTransportType(TRANSPORT_WIFI)
//                    connectivityManager.registerNetworkCallback(builder.build(), networkCallback)
//                }
//                else -> {
//                    context.registerReceiver(networkReceiver, intentFilter)
//                }
//            }
//        }
//
//        override fun onInactive() {
//            super.onInactive()
//            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
//                connectivityManager.unregisterNetworkCallback(networkCallback)
//            } else{
//                context.unregisterReceiver(networkReceiver)
//            }
//        }
//
//
//        private val networkReceiver = object : BroadcastReceiver() {
//            override fun onReceive(context: Context, intent: Intent) {
//                updateConnection()
//            }
//        }
//
//        fun updateConnection() {
//            val activeNetwork: NetworkInfo? = connectivityManager.activeNetworkInfo
//            postValue(activeNetwork?.isConnectedOrConnecting == true)
//        }
//
//        @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
//        class NetworkCallback(val liveData : ConnectionLiveData) : ConnectivityManager.NetworkCallback() {
//            override fun onAvailable(network: Network?) {
//                liveData.postValue(true)
//            }
//
//            override fun onLost(network: Network?) {
//                liveData.postValue(false)
//            }
//        }

        private var connectivityManager: ConnectivityManager = context.getSystemService(CONNECTIVITY_SERVICE) as ConnectivityManager

        private lateinit var connectivityManagerCallback: ConnectivityManager.NetworkCallback

        override fun onActive() {
            super.onActive()
            updateConnection()
            when {
                Build.VERSION.SDK_INT >= Build.VERSION_CODES.N -> connectivityManager.registerDefaultNetworkCallback(getConnectivityManagerCallback())
                Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP -> lollipopNetworkAvailableRequest()
                else -> {
                    if (Build.VERSION.SDK_INT < Build.VERSION_CODES.LOLLIPOP) {
                        context.registerReceiver(networkReceiver, IntentFilter("android.net.conn.CONNECTIVITY_CHANGE")) // android.net.ConnectivityManager.CONNECTIVITY_ACTION
                    }
                }
            }
        }

        override fun onInactive() {
            super.onInactive()
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                connectivityManager.unregisterNetworkCallback(connectivityManagerCallback)
            } else {
                context.unregisterReceiver(networkReceiver)
            }
        }

        @TargetApi(Build.VERSION_CODES.LOLLIPOP)
        private fun lollipopNetworkAvailableRequest() {
            val builder = NetworkRequest.Builder()
                .addTransportType(android.net.NetworkCapabilities.TRANSPORT_CELLULAR)
                .addTransportType(android.net.NetworkCapabilities.TRANSPORT_WIFI)
            connectivityManager.registerNetworkCallback(builder.build(), getConnectivityManagerCallback())
        }

        private fun getConnectivityManagerCallback(): ConnectivityManager.NetworkCallback {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {

                connectivityManagerCallback = object : ConnectivityManager.NetworkCallback() {
                    override fun onAvailable(network: Network?) {
                        postValue(true)
                    }

                    override fun onLost(network: Network?) {
                        postValue(false)
                    }
                }
                return connectivityManagerCallback
            } else {
                throw IllegalAccessError("Should not happened")
            }
        }

        private val networkReceiver = object : BroadcastReceiver() {
            override fun onReceive(context: Context, intent: Intent) {
                updateConnection()
            }
        }

        private fun updateConnection() {
            val activeNetwork: NetworkInfo? = connectivityManager.activeNetworkInfo
            postValue(activeNetwork?.isConnected == true)
        }
    }


