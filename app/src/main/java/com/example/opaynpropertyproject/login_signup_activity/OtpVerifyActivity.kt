package com.example.opaynpropertyproject.login_signup_activity

import ServiceViewModel
import `in`.aabhasjindal.otptextview.OTPListener
import android.os.Bundle
import android.util.Log
import android.view.View
import com.example.opaynpropertyproject.R
import com.example.opaynpropertyproject.api.ApiResponse
import com.example.opaynpropertyproject.api.Keys
import com.example.opaynpropertyproject.api_model.EmailVerifySuccessfully
import com.example.opaynpropertyproject.api_model.SuccessSignupModel
import com.example.opaynpropertyproject.comman.BaseActivity
import com.example.opaynpropertyproject.comman.Utils
import com.example.opaynpropertyproject.comman.Utils.customSnakebar
import com.example.opaynpropertyproject.home_activity.HomeActivity
import kotlinx.android.synthetic.main.activity_otp_verify.*

class OtpVerifyActivity : BaseActivity(), View.OnClickListener, ApiResponse {
    var getUserId: Int = 0

    var successSignupModel: SuccessSignupModel? = null
    lateinit var hashMap: HashMap<String, Any>
    var mOtp = ""
    lateinit var bundle: Bundle


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_otp_verify)
        supportActionBar!!.hide()
        otp_verify_password_back_btn.setOnClickListener(this)
        otp_continue_btn.setOnClickListener(this)
        resend_otp_again_btn.setOnClickListener(this)
        otp_main_container.setOnClickListener(this)
        hashMap = HashMap<String, Any>()

        bundle = Bundle()
        val bundle = this.intent.extras
            successSignupModel = bundle!!.getParcelable(Keys.PRACELABLE_KEY)
            if (successSignupModel != null){
                getUserId = successSignupModel!!.data.user.id
                Log.e("Bundle","Bulde222222")

            }

        customSnakebar(otp_main_container, "OTP Send On Your Register Email Address")

        otp_view.otpListener = object : OTPListener {
            override fun onInteractionListener() {

            }

            override fun onOTPComplete(otp: String) {
                mOtp = otp
                Log.e("otp", otp.toString())
            }

        }


    }

    override fun onClick(v: View?) {
        when (v!!.id) {
            R.id.otp_verify_password_back_btn -> {
                onBackPressed()
            }
            R.id.otp_main_container->{
                Utils.hideKeyboard(this)
            }
            R.id.otp_continue_btn -> {

                    if (mOtp.isNotEmpty()) {
                        hashMap.put(Keys.USER_ID, getUserId)
                        hashMap.put(Keys.USER_INPUT_OTP, mOtp)
                        serviceViewModel.postservice(
                            Keys.EMAIL_VERIFY_END_POINT,
                            this,
                            hashMap,
                            Keys.MAIL_REQ_CODE,
                            true,
                            "",
                            true,
                            this
                        )
                    } else {
                        customSnakebar(otp_continue_btn, "You Must fill OTP")
                    }


            }
            R.id.resend_otp_again_btn -> {
                if (hashMap.isNotEmpty()){

                    customSnakebar(resend_otp_again_btn, getString(R.string.otp_send_again))
                    hashMap.put(Keys.USER_ID, getUserId)
                    serviceViewModel.postservice(
                        Keys.USER_OTP_RESEND_END_POINT,
                        this,
                        hashMap,
                        Keys.MAIL_RESEND_REQ_CODE,
                        true,
                        "",
                        false,
                        this
                    )
                }else{
                    customSnakebar(resend_otp_again_btn,getString(R.string.resend_otp_again_error))
                }

            }
        }

    }

    override fun onResponse(requestcode: Int, response: String) {

        when (requestcode) {
            Keys.MAIL_REQ_CODE -> {
                var model: EmailVerifySuccessfully? = null
                model = gson.fromJson(response, EmailVerifySuccessfully::class.java)
                customSnakebar(otp_continue_btn, model.message)
                bundle.putString(Keys.USER_ID, successSignupModel!!.data.user.id.toString())
                openA(HomeActivity::class, bundle)
            }
            Keys.RESET_REQ_CODE->{
                bundle.putString("code",mOtp)
            }
            Keys.BACKENDERROR -> {
                Log.e("testtest", "test1111")
            }
        }

    }
}