package com.mlbench.fitmeapp.Utils

import android.annotation.SuppressLint
import android.content.Context
import android.content.SharedPreferences
import com.google.gson.Gson
import com.mlbench.fitmeapp.Utils.Constants.PREFS_TOKEN_FILE
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject

class SharePref @Inject constructor(@ApplicationContext ctx: Context) {
    val gson = Gson()
    var prefs: SharedPreferences? = null

    companion object {
        @SuppressLint("StaticFieldLeak")
        private var mContext: Context? = null

        @SuppressLint("StaticFieldLeak")
        var mInstence: SharePref? = null

        fun init(context: Context?) {
            mContext = context
        }

        fun getInstance(): SharePref? {
            if (mInstence != null) {
                return mInstence
            } else {
                mInstence = SharePref(mContext!!.applicationContext)
            }
            return mInstence
        }
    }

    var isThisSessionFromLink = false

    init {
        prefs = ctx.getSharedPreferences(PREFS_TOKEN_FILE, Context.MODE_PRIVATE)
        isThisSessionFromLink = false
    }

    fun writeBoolean(key: String?, value: Boolean) {
        prefs!!.edit().putBoolean(key, value).apply()
    }

    fun readBoolean(
        key: String?,
        defValue: Boolean
    ): Boolean {
        return prefs!!.getBoolean(key, defValue)
    }

    fun writeInteger(key: String?, value: Int) {
        prefs!!.edit().putInt(key, value).apply()
    }

    fun readInteger(key: String?, defValue: Int): Int {
        return prefs!!.getInt(key, defValue)
    }

    fun writeString(key: String?, value: String?) {
        prefs!!.edit().putString(key, value).apply()
    }

    fun readString(key: String?, defValue: String): String? {
        return prefs!!.getString(key, defValue)
    }

    fun readString(key: String?): String? {
        return prefs!!.getString(key, null)
    }

    fun writeCheck(key: String?, value: Boolean?) {
        if (value != null) {
            prefs!!.edit().putBoolean(key, value).apply()
        }
    }

    fun readCheck(key: String?): Boolean? {
        return prefs!!.getBoolean(key, false)
    }

    fun deleteAllSharedPrefs() {
        prefs!!.edit().clear().apply()
    }

    fun deleteItemSharePref(value: String) {
        prefs!!.edit().remove(value).apply()
    }

   /* fun readUserData(key: String?): UserData? {
        val gson = Gson()
        val jsonOutput = prefs?.getString(key, null)
        val dataType: Type =
            object : TypeToken<UserData?>() {}.type
        return gson.fromJson(jsonOutput, dataType)
    }

    fun writeUserData(user: UserData) {
        val data = gson.toJson(user)
        prefs!!.edit().putString(Constants.USER_DATA, data).apply()
    }

    fun readActivePlan(key: String?): ActiveData? {
        val gson = Gson()
        val jsonOutput = prefs?.getString(key, null)
        val dataType: Type =
            object : TypeToken<ActiveData?>() {}.type
        return gson.fromJson(jsonOutput, dataType)
    }

    fun writeActivePlan(activeData: ActiveData) {
        val data = gson.toJson(activeData)
        prefs!!.edit().putString(Constants.ACTIVE_PLAN, data).apply()
    }*/



}