package com.example.opaynpropertyproject.comman

import ServiceViewModel
import android.app.Activity
import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.view.View
import android.view.inputmethod.InputMethodManager
import androidx.appcompat.app.AppCompatActivity
import com.example.opaynpropertyproject.R
import com.google.android.material.snackbar.Snackbar
import com.google.gson.Gson
import kotlinx.android.synthetic.main.activity_sign_up.*
import kotlin.reflect.KClass

open class BaseActivity : AppCompatActivity(){
    //snakeBar
    val gson = Gson()
    val serviceViewModel = ServiceViewModel()


    //intent
   open fun openA(kClass: KClass<out AppCompatActivity>, bundle: Bundle? = Bundle()) {
        val intent = Intent(this, kClass.java)
        intent.putExtras(bundle ?: Bundle())
        startActivity(intent)





/*
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            activity.startActivity(intent, ActivityOptions.makeSceneTransitionAnimation(activity).toBundle())
        } else {
            activity.startActivity(intent)
        }
*/
    }

}