package com.example.opaynpropertyproject.comman

import androidx.appcompat.app.AppCompatActivity
import com.example.opaynpropertyproject.R
import com.google.android.material.snackbar.Snackbar
import com.google.gson.Gson
import kotlinx.android.synthetic.main.activity_sign_up.*

open class BaseActivity : AppCompatActivity(){
    //snakeBar
    val gson = Gson()
    open fun customSnakebar(error_name:String){
        Snackbar.make(signup_name,error_name, Snackbar.LENGTH_LONG).show()
    }
}