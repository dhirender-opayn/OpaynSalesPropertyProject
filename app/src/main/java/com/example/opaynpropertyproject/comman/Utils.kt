package com.example.opaynpropertyproject.comman

import ServiceViewModel
import android.view.View
import com.example.opaynpropertyproject.api_model.SignupModel
import com.google.android.material.snackbar.Snackbar
import com.google.gson.Gson
import java.util.regex.Pattern
import android.app.Activity
import android.app.ActivityManager
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.inputmethod.InputMethodManager
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat.startActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import androidx.lifecycle.Lifecycle
import com.example.opaynpropertyproject.R
import kotlin.reflect.KClass


object Utils {

    fun isValidEmailId(email: String): Boolean {
        return Pattern.compile(
            "^(([\\w-]+\\.)+[\\w-]+|([a-zA-Z]{1}|[\\w-]{2,}))@"
                    + "((([0-1]?[0-9]{1,2}|25[0-5]|2[0-4][0-9])\\.([0-1]?"
                    + "[0-9]{1,2}|25[0-5]|2[0-4][0-9])\\."
                    + "([0-1]?[0-9]{1,2}|25[0-5]|2[0-4][0-9])\\.([0-1]?"
                    + "[0-9]{1,2}|25[0-5]|2[0-4][0-9])){1}|"
                    + "([a-zA-Z]+[\\w-]+\\.)+[a-zA-Z]{2,4})$"
        ).matcher(email).matches()
    }

    open fun customSnakebar(view: View, error_name:String){
        Snackbar.make(view,error_name, Snackbar.LENGTH_LONG).show()
    }





    //Hide Keyboard
    fun hideKeyboard(activity: Activity) {
        val imm: InputMethodManager =
            activity.getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager
        //Find the currently focused view, so we can grab the correct window token from it.
        var view = activity.currentFocus
        //If no view currently has focus, create a new one, just so we can grab a window token from it
        if (view == null) {
            view = View(activity)
        }
        imm.hideSoftInputFromWindow(view.windowToken, 0)
    }



    fun addReplaceFragment(
        mContext: Context,
        fragment: Fragment,
        container: Int,
        addToBackStack: Boolean,
        isAdd: Boolean,
        isSlide: Boolean,
    ) {
        val transaction = (mContext as FragmentActivity).supportFragmentManager.beginTransaction()

//        if (isSlide)
//            transaction.setCustomAnimations(R.anim.lefttoright, R.anim.righttoleft)
        if (isAdd)
            transaction.add(container, fragment)
        else
            transaction.replace(container, fragment)
        if (addToBackStack)
            transaction.addToBackStack(null)
        transaction.commitAllowingStateLoss()
    }



    fun OnBackPressFragement(fragmentActivity: FragmentActivity){

        val fm: FragmentManager = fragmentActivity.supportFragmentManager
        fm.popBackStack()
    }


    //convert string to json
//        var  model: SignupModel?=null
//        var gson = Gson()
//        jsondata=preferenceManager.getString(Keys.USERDATA).toString()
//        model=gson.fromJson(jsondata, UserJson::class.java)


}
