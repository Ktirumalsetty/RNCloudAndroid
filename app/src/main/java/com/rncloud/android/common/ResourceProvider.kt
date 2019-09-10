package com.rncloud.android.common

import android.content.Context

/**
 * Created by KondalRao Tirumalasetty on 8/23/2019.
 */
class ResourceProvider(private val context: Context) {

    val mContext: Context = context

    fun getString(resId: Int): String {
        return mContext.getString(resId)
    }

    fun getString(resId: Int, value: String): String {
        return mContext.getString(resId, value)
    }
}