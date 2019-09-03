package com.rncloud.android.adapter
import android.view.View

/**
 * Created by KondalRao Tirumalasetty on 8/29/2019.
 */
interface ClickListener {

     fun onClick(view: View, position: Int)

    fun onLongClick(view: View, position: Int)
}