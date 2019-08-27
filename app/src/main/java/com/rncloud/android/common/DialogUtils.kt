package com.rncloud.android.common

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.provider.Settings
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import com.rncloud.android.R

/**
 * Created by KondalRao Tirumalasetty on 8/27/2019.
 */

/**
 * Purpose of this Class is to display different dialog in application.
 */
object DialogUtils {

    /**
     * Displays alert dialog to show common messages.
     *
     * @param message Message to be shown in the Dialog displayed
     * @param context Context of the Application, where the Dialog needs to be displayed
     */
    fun displayDialog(
        context: Context,
        message: String
    ) {

        val alertDialog = AlertDialog.Builder(context)
            .create()
        alertDialog.setTitle(context.getString(R.string.app_name))
        alertDialog.setCancelable(false)

        alertDialog.setMessage(message)
        alertDialog.setButton(
            AlertDialog.BUTTON_POSITIVE, context.getString(android.R.string.ok)
        ) { dialog, _ -> dialog.dismiss() }

        if (!(context as Activity).isFinishing) {
            alertDialog.show()
        }
    }


    /**
     * Displays toast message
     *
     * @param mContext requires to create Toast
     */
    fun showToast(
        mContext: Context,
        message: String
    ) {
        Toast.makeText(mContext, message, Toast.LENGTH_SHORT)
            .show()
    }
}