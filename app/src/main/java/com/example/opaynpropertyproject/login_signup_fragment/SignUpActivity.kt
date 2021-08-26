package com.example.opaynpropertyproject.login_signup_fragment

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
 import com.example.opaynpropertyproject.api.Keys
import com.example.opaynpropertyproject.R
import com.example.opaynpropertyproject.api.ApiResponse
import com.example.opaynpropertyproject.api_model.SignupModel
import com.example.opaynpropertyproject.comman.BaseActivity
import com.example.opaynpropertyproject.comman.Utils
import com.google.android.material.snackbar.Snackbar
import com.google.gson.Gson
import com.google.gson.JsonObject
import kotlinx.android.synthetic.main.activity_sign_up.*
import org.json.JSONObject
import java.util.*
 import kotlin.collections.HashMap
import kotlin.math.log

class SignUpActivity : BaseActivity(),View.OnClickListener, ApiResponse {
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
        val email = signup_email.text.toString().trim()

        if(signup_name.text.trim().isEmpty()){
              customSnakebar(getString(R.string.name_required))
        }else if (email.trim().isEmpty()){
            customSnakebar(getString(R.string.email_required))
        }else if (!Utils.isValidEmailId(email)){
            customSnakebar(getString(R.string.email_pattern))
        }
        else if (signup_password.text.toString().trim().isEmpty()){
            customSnakebar(getString(R.string.password_required))
        }else if(!signup_checkbox_terms.isChecked){
            customSnakebar(getString(R.string.required_terms_and_condtion))
        }else{
            hashlist_map.put("name", signup_name.text.trim())
            hashlist_map.put("email", email)
            hashlist_map.put("password", signup_password.text.toString().trim())
            hashlist_map.put("agree", signup_checkbox_terms)
            hashlist_map.put("step",Keys.signup_step)
            Log.e("list",hashlist_map.toString())
            return hashlist_map
        }
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
               val mapUser =  userValue()
                if (mapUser.isEmpty()){
                    Log.e("ddd","Eddd")
                }else{
//                    val intent = Intent(this,SellerBuyerProfileSetActivity::class.java)
//                    startActivity(intent)
                    serviceViewModel=ServiceViewModel()
                    serviceViewModel.postservice(Keys.signup,this,hashlist_map,Keys.Req_log,true,"",true,this)
                }
            }

        }
    }

    override fun onResponse(requestcode: Int, response: String)
    {

        val request = gson.toJson(response)


        when(requestcode){
            Keys.BACKENDERROR->{



            }
        }


    }

}



