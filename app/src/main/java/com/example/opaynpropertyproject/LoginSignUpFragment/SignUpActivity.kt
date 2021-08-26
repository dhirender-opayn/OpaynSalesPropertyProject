package com.example.opaynpropertyproject.LoginSignUpFragment

import ServiceViewModel
 import android.content.Intent
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Spannable
import android.text.SpannableString
import android.text.style.ForegroundColorSpan
import android.util.Log
import android.view.View
 import com.example.opaynpropertyproject.Api.Keys
import com.example.opaynpropertyproject.R
import kotlinx.android.synthetic.main.activity_sign_up.*
import java.util.*
 import kotlin.collections.HashMap

class SignUpActivity : AppCompatActivity(),View.OnClickListener,Observer {
    lateinit var hashlist_map:HashMap<String,Any>
    lateinit var serviceViewModel: ServiceViewModel
     override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_up)
        supportActionBar!!.hide()
         hashlist_map = HashMap<String,Any>()

         signup_button.setOnClickListener(this)
        signup_back_btn.setOnClickListener(this)
        login_account_btn_Signup.setOnClickListener(this)
        signup_button.setOnClickListener(this)
        val spannable = SpannableString("Agree to the Term & Conditions")
        spannable.setSpan(
            ForegroundColorSpan(Color.RED),13,30,  Spannable.SPAN_EXCLUSIVE_EXCLUSIVE
        )
        signup_checkbox_terms.text = spannable


    }
    fun userValue():HashMap<String,Any> {
        val user_name = signup_name.text
        val user_email = signup_email.text.toString()
        val user_password = signup_password.text.toString().toInt()
        val is_agree = signup_checkbox_terms.isChecked
        hashlist_map.put("name", user_name)
        hashlist_map.put("email", user_email)
        hashlist_map.put("password", user_password)
        hashlist_map.put("agree", is_agree)
        hashlist_map.put("step",Keys.signup_step)
        Log.e("list",hashlist_map.toString())
        return hashlist_map
    }

    override fun onClick(v: View?) {
        when(v!!.id){
            R.id.signup_back_btn->{
                onBackPressed()
            }
            R.id.login_account_btn_Signup->{
                val intent = Intent(this,LoginActivity::class.java)
                startActivity(intent)
            }
            R.id.signup_button->{
                userValue()
//                val intent = Intent(this,SellerBuyerProfileSetActivity::class.java)
//                startActivity(intent)
                serviceViewModel=ServiceViewModel()
                serviceViewModel.postservice(Keys.signup,this,hashlist_map,Keys.Req_log,true,"",true)
            }

        }
    }
    override fun update(o: Observable?, arg: Any?) {
        when(serviceViewModel.reuestCode)
        {
            Keys.Req_log->{
                Log.e("Login","Login Sucessfully")
            }
            2->{

            }
        }
    }
}