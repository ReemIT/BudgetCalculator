package com.admin.budgetcalculator

import android.app.Activity
import android.content.Context

class SharedPreferencesServices {

    companion object {
        private const val EMAIL_KEY = "email"
        private const val PASSWORD_KEY = "password"
        private const val CALCULATIONS="calculations"

        fun signup(activity: Activity, email: String, password: String) {
            val preferences = activity.getPreferences(Context.MODE_PRIVATE)
            val editor = preferences.edit()
            editor.putString(EMAIL_KEY, email)
            editor.putString(PASSWORD_KEY, password)
            editor.apply()
        }

        fun login(activity: Activity, email: String, password: String): Pair<Boolean,Boolean> {
            val preferences = activity.getPreferences(Context.MODE_PRIVATE)
            val emailValidation = preferences.getString(EMAIL_KEY, "") == email
            val passwordValidation = preferences.getString(PASSWORD_KEY, "") == password
            return Pair(emailValidation,passwordValidation)
        }


        fun storeCalculation(activity: Activity , result: String){
            val editor = activity.getSharedPreferences(CALCULATIONS,Context.MODE_PRIVATE).edit()
            editor.putString(System.currentTimeMillis().toString(),result)
            editor.apply()
        }

        fun getCalculations(activity:Activity):Map<String,*>{
            val preferences = activity.getSharedPreferences(CALCULATIONS,Context.MODE_PRIVATE)
            return preferences.all
        }
    }



}