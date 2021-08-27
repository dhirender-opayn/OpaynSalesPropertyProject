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
import com.example.opaynpropertyproject.api_model.SuccessSignupModel
import com.example.opaynpropertyproject.comman.BaseActivity
import com.example.opaynpropertyproject.comman.Utils
import com.google.android.material.snackbar.Snackbar
import com.google.gson.Gson
import com.google.gson.JsonObject
import kotlinx.android.synthetic.main.activity_sign_up.*
import org.json.JSONObject
import java.util.*
import kotlin.collections.HashMap
import kotlin.collections.LinkedHashMap
import kotlin.math.log

class SignUpActivity : BaseActivity(), View.OnClickListener, ApiResponse {
    lateinit var hashlist_map: HashMap<String, Any>
   lateinit var bundle:Bundle
    lateinit var serviceViewModel: ServiceViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_up)
        supportActionBar!!.hide()
        hashlist_map = HashMap<String, Any>()
        bundle = Bundle()


        signup_button.setOnClickListener(this)
        signup_back_btn.setOnClickListener(this)
        login_account_btn_Signup.setOnClickListener(this)
        signup_button.setOnClickListener(this)
        val spannable = SpannableString("Agree to the Term & Conditions")
        spannable.setSpan(
            ForegroundColorSpan(Color.RED), 13, 30, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE
        )
        signup_checkbox_terms.text = spannable


    }

    fun userValue(): HashMap<String, Any> {
        val email = signup_email.text.toString().trim()

        if (signup_name.text.trim().isEmpty()) {
            customSnakebar(signup_name, getString(R.string.name_required))
        } else if (email.trim().isEmpty()) {
            customSnakebar(signup_email, getString(R.string.email_required))
        } else if (!Utils.isValidEmailId(email)) {
            customSnakebar(signup_email, getString(R.string.email_pattern))
        } else if (signup_password.text.toString().trim().isEmpty()) {
            customSnakebar(signup_password, getString(R.string.password_required))
        } else if (!signup_checkbox_terms.isChecked) {
            customSnakebar(signup_checkbox_terms, getString(R.string.required_terms_and_condtion))
        } else {
            hashlist_map.put(Keys.signup_name, signup_name.text.trim())
            hashlist_map.put(Keys.signup_email, email)
            hashlist_map.put(Keys.signup_password, signup_password.text.toString().trim())
            hashlist_map.put(Keys.signup_agree, signup_checkbox_terms)
            hashlist_map.put(Keys.signup_Step, Keys.signup_step_One)
            return hashlist_map
        }
        return hashlist_map
    }

    override fun onClick(v: View?) {
        when (v!!.id) {
            R.id.signup_back_btn -> {
                onBackPressed()
            }
            R.id.login_account_btn_Signup -> {
                val intent = Intent(this, LoginActivity::class.java)
                startActivity(intent)
            }
            R.id.signup_button -> {
                val mapUser = userValue()
                if (mapUser.isEmpty()) {
                    Log.e("ddd", "Eddd")
                } else {
                    serviceViewModel = ServiceViewModel()
                    serviceViewModel.postservice(
                        Keys.signupEndPoint,
                        this,
                        hashlist_map,
                        Keys.Req_log,
                        true,
                        "",
                        true,
                        this
                    )


                }
            }

        }
    }

    override fun onResponse(requestcode: Int, response: String) {
        when (requestcode) {

            Keys.Req_log -> {
                var model: SuccessSignupModel? = null
                model = gson.fromJson(response, SuccessSignupModel::class.java)
                val user_id = model.data.user.id.toString()


                bundle.putString(Keys.USER_ID,user_id)

                openA(SellerBuyerProfileSetActivity::class,bundle)

            }

            Keys.BACKENDERROR -> {
                Log.e("eeee", response.toString())
            }
        }
    }

}



