package com.rncloud.android.common

import android.content.Context
import android.content.SharedPreferences

object AppPreferences {
    private const val NAME = "RNCloud"
    private const val MODE = Context.MODE_PRIVATE
    private lateinit var preferences: SharedPreferences

    // list of app specific preferences
    private val IS_FIRST_RUN_PREF = Pair("is_first_run", false)

    private val USER_TYPE = Pair("user_type", -1)
    private val ACTOR_CODE = Pair("actor_code", -1)
    private val AUTH_GEN_KEY = Pair("auth_gen_key", "")  // Session Token
    private val USER_NAME = Pair("user_name", "")  // Session Token


    fun init(context: Context) {
        preferences = context.getSharedPreferences(NAME, MODE)
    }

    /**
     * SharedPreferences extension function, so we won't need to call edit() and apply()
     * ourselves on every SharedPreferences operation.
     */
    private inline fun SharedPreferences.edit(operation: (SharedPreferences.Editor) -> Unit) {
        val editor = edit()
        operation(editor)
        editor.apply()
    }

    var firstRun: Boolean
        // custom getter to get a preference of a desired type, with a predefined default value
        get() = preferences.getBoolean(IS_FIRST_RUN_PREF.first, IS_FIRST_RUN_PREF.second)

        // custom setter to save a preference back to preferences file
        set(value) = preferences.edit {
            it.putBoolean(IS_FIRST_RUN_PREF.first, value)
        }

    var userType:Int
    get() = preferences.getInt(USER_TYPE.first, USER_TYPE.second)
    set(value) = preferences.edit(){
        it.putInt(USER_TYPE.first,value)
    }

    var actorCode:Int
    get() = preferences.getInt(ACTOR_CODE.first, ACTOR_CODE.second)
    set(value) = preferences.edit(){
        it.putInt(ACTOR_CODE.first,value)
    }

    var authGenKey:String
    get() = preferences.getString(AUTH_GEN_KEY.first, AUTH_GEN_KEY.second)
    set(value) = preferences.edit(){
        it.putString(AUTH_GEN_KEY.first,value)
    }

    var userName:String
    get() = preferences.getString(USER_NAME.first, USER_NAME.second)
    set(value) = preferences.edit(){
        it.putString(USER_NAME.first,value)
    }
}