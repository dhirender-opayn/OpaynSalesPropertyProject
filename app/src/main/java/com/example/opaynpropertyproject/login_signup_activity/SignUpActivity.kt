package com.example.opaynpropertyproject.login_signup_activity

import android.graphics.Color
import android.os.Bundle
import android.text.Spannable
import android.text.SpannableString
import android.text.style.ForegroundColorSpan
import android.util.Log
import android.view.View
import com.example.opaynpropertyproject.api.Keys
import com.example.opaynpropertyproject.R
import com.example.opaynpropertyproject.singleton.SingletonObject.mUserID
import com.example.opaynpropertyproject.api.ApiResponse
import com.example.opaynpropertyproject.api_model.ErrorModel
import com.example.opaynpropertyproject.api_model.SuccessSignupModel
import com.example.opaynpropertyproject.comman.BaseActivity
import com.example.opaynpropertyproject.comman.Utils
import kotlinx.android.synthetic.main.activity_sign_up.*
import kotlinx.android.synthetic.main.activity_sign_up.signup_email
import kotlin.collections.HashMap

class SignUpActivity : BaseActivity(), View.OnClickListener, ApiResponse {
    lateinit var hashlist_map: HashMap<String, Any>
    lateinit var bundle: Bundle

    var model: SuccessSignupModel? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_up)
        overridePendingTransition(0,0)
        supportActionBar!!.hide()
        hashlist_map = HashMap<String, Any>()
        bundle = Bundle()


        signup_button.setOnClickListener(this)
        signup_back_btn.setOnClickListener(this)
        login_account_btn_Signup.setOnClickListener(this)
        signup_button.setOnClickListener(this)
        signup_container.setOnClickListener(this)
        val spannable = SpannableString("Agree to the Term & Conditions")
        spannable.setSpan(
            ForegroundColorSpan(Color.RED), 13, 30, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE
        )
        signup_checkbox_terms.text = spannable


    }

    fun userValue(): HashMap<String, Any> {
        val email = signup_email.text.toString().trim()
        if (signup_name.text.trim().isEmpty()) {
            Utils.customSnakebar(signup_name, getString(R.string.name_required))
        } else if (email.trim().isEmpty()) {
            Utils.customSnakebar(signup_email, getString(R.string.email_required))
        } else if (!Utils.isValidEmailId(email)) {
            Utils.customSnakebar(signup_email, getString(R.string.email_pattern))
        } else if (signup_password.text.toString().trim().isEmpty()) {
            Utils.customSnakebar(signup_password, getString(R.string.password_required))
        } else if (!signup_checkbox_terms.isChecked) {
            Utils.customSnakebar(signup_checkbox_terms, getString(R.string.required_terms_and_condtion))
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
            R.id.signup_container->{
                Utils.hideKeyboard(this)
            }
            R.id.login_account_btn_Signup -> {
                openA(LoginActivity::class)
            }
            R.id.signup_button -> {
                val mapUser = userValue()
                if (mapUser.isEmpty()) {
                    Log.e("ddd", "hashmap is empty")
                } else {

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
                model = gson.fromJson(response, SuccessSignupModel::class.java)
                bundle.putParcelable(Keys.PRACELABLE_KEY, model)
                mUserID = (model  as SuccessSignupModel)
                openA(SellerBuyerProfileSetActivity::class, bundle)
            }

            Keys.BACKENDERROR -> {
                val errorModel = gson.fromJson(response,ErrorModel::class.java)
                Utils.customSnakebar(signup_button,errorModel.message.toString())
                Log.e("eeee", response.toString())
            }
        }
    }

}



