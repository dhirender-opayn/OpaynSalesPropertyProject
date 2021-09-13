package com.example.opaynpropertyproject.login_signup_activity

import ServiceViewModel
import android.os.Bundle
import android.util.Log
import android.view.View
import com.example.opaynpropertyproject.R
import com.example.opaynpropertyproject.api.ApiResponse
import com.example.opaynpropertyproject.api.Keys
import com.example.opaynpropertyproject.api_model.ErrorModel
import com.example.opaynpropertyproject.api_model.ResetCodeSendSuccessful
import com.example.opaynpropertyproject.comman.BaseActivity
import com.example.opaynpropertyproject.comman.Utils
import kotlinx.android.synthetic.main.activity_reset_password.*
import kotlin.collections.HashMap

class ResetPasswordActivity : BaseActivity(), View.OnClickListener, ApiResponse {
    lateinit var hashlist_map: HashMap<String, Any>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_reset_password)
        supportActionBar!!.hide()
        hashlist_map = HashMap<String,Any>()
        reset_password_back_btn.setOnClickListener(this)
        reset_password_btn.setOnClickListener(this)
        reset_container.setOnClickListener(this)


    }

    fun resetPassUserValue(): HashMap<String, Any> {
        val reset_password = reset_password_enter.text.toString()
        val reset_password_again = reset_password_r_enter.text.toString()
        val reset_code = reset_password_r_code.text.toString()
        hashlist_map.put(Keys.RESET_PASSWORD, reset_password)
        hashlist_map.put(Keys.RESET_COMFIRM_PASSWORD, reset_password_again)
        hashlist_map.put(Keys.RESET_CODE, reset_code)
        return hashlist_map
    }

    override fun onClick(v: View?) {
        when (v!!.id) {
            R.id.reset_password_back_btn -> {
                onBackPressed()
            }
            R.id.reset_container->{
                Utils.hideKeyboard(this)
            }
            R.id.reset_password_btn -> {

                val otpReset_User_value = resetPassUserValue()
                serviceViewModel.postservice(
                    Keys.RESET_MATCH_END_POINT,
                    this,
                    otpReset_User_value,
                    Keys.RESET_REQ_CODE,
                    true,
                    "",
                    true,
                    this
                )

            }
        }


    }

    override fun onResponse(requestcode: Int, response: String) {
        when (requestcode) {
            Keys.RESET_REQ_CODE-> {
                    val model = gson.fromJson(response, ResetCodeSendSuccessful::class.java)
                    Utils.customSnakebar(reset_password_btn, model.message.toString())
                    openA(LoginActivity::class)



            }
            Keys.BACKENDERROR -> {
                val errorModel = gson.fromJson(response, ErrorModel::class.java)
                Utils.customSnakebar(reset_password_btn, errorModel.message.toString())
                Log.e("eeee", response.toString())
            }
        }
    }
}