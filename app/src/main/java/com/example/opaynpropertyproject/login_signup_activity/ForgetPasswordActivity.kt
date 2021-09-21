package com.example.opaynpropertyproject.login_signup_activity

import ServiceViewModel
import android.os.Bundle
import android.util.Log
import android.view.View
import com.example.opaynpropertyproject.R
import com.example.opaynpropertyproject.api.ApiResponse
import com.example.opaynpropertyproject.api.Keys
import com.example.opaynpropertyproject.api_model.ErrorModel
import com.example.opaynpropertyproject.comman.BaseActivity
import com.example.opaynpropertyproject.comman.Utils
import kotlinx.android.synthetic.main.activity_forget_password.*

class ForgetPasswordActivity : BaseActivity(), View.OnClickListener, ApiResponse {
    lateinit var hashlist_map: HashMap<String, Any>

    lateinit var bundle: Bundle
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_forget_password)

        overridePendingTransition(0,0)
        supportActionBar!!.hide()
        hashlist_map = HashMap<String,Any>()
        bundle = Bundle()
        forget_password_back_btn.setOnClickListener(this)
        forget_password_sendOtp_btn.setOnClickListener(this)
        forget_container.setOnClickListener(this)
    }

    fun userValue(): HashMap<String, Any> {
        val email = forget_email_enter.text.toString().trim()
        if (email.trim().isEmpty()) {
            Utils.customSnakebar(forget_email_enter, getString(R.string.email_required))
        } else if (!Utils.isValidEmailId(email)) {
            Utils.customSnakebar(forget_email_enter, getString(R.string.email_pattern))
        } else {
            hashlist_map.put(Keys.RESET_EMAIL, email)
            return hashlist_map
        }
        return hashlist_map

    }

    override fun onClick(v: View?) {
        when (v!!.id) {
            R.id.forget_password_back_btn -> {
                onBackPressed()
            }
            R.id.forget_container->{
                Utils.hideKeyboard(this)
            }
            R.id.forget_password_sendOtp_btn -> {

                val reset_values = userValue()
                if (reset_values.isNotEmpty()) {
                    serviceViewModel.postservice(
                        Keys.RESET_EMAIL_END_POINT,
                        this,
                        hashlist_map,
                        Keys.RESET_REQ_CODE,
                        true,
                        "",
                        true,
                        this
                    )

                }else{
                    Log.e("reset", "hashmap is empty")
                }

            }
        }
    }

    override fun onResponse(requestcode: Int, response: String) {
        when(requestcode){
            Keys.RESET_REQ_CODE->{

                openA(ResetPasswordActivity::class)
            }
            Keys.BACKENDERROR->{
                val errorModel = gson.fromJson(response, ErrorModel::class.java)
                Utils.customSnakebar(forget_password_sendOtp_btn,errorModel.message.toString())
                Log.e("eeee", response.toString())
            }
        }

    }
}