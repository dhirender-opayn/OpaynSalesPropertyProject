package com.example.opaynpropertyproject.comman

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.example.opaynpropertyproject.R
import com.google.android.material.snackbar.Snackbar
import com.google.gson.Gson
import kotlinx.android.synthetic.main.activity_sign_up.*
import kotlin.reflect.KClass

open class BaseActivity : AppCompatActivity(){
    //snakeBar
    val gson = Gson()
    open fun customSnakebar(view:View,error_name:String){
        Snackbar.make(view,error_name, Snackbar.LENGTH_LONG).show()
    }


    //intent
    fun openA(kClass: KClass<out AppCompatActivity>, bundle: Bundle? = Bundle()) {
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