package com.example.opaynpropertyproject.login_signup_fragment

import android.content.Intent
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.example.opaynpropertyproject.R
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.activity_otp_verify.*

class OtpVerifyActivity : AppCompatActivity(),View.OnClickListener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_otp_verify)
        supportActionBar!!.hide()
        otp_verify_password_back_btn.setOnClickListener(this)
        otp_continue_btn.setOnClickListener(this)
        resend_otp_again_btn.setOnClickListener(this)


    }

    override fun onClick(v: View?) {
        when(v!!.id){
            R.id.otp_verify_password_back_btn->{
                onBackPressed()
            }
            R.id.otp_continue_btn->{
                val intent = Intent(this,ResetPasswordActivity::class.java)
                startActivity(intent)
            }
            R.id.resend_otp_again_btn->{
               val snackbar = Snackbar.make(this,resend_otp_again_btn, "OTP Send Again..!!",
                    Snackbar.LENGTH_LONG)
                snackbar.view.setBackgroundColor(Color.parseColor("#E11616"))
                snackbar.setActionTextColor(Color.parseColor("#FFFFFF"))
                snackbar.show()

            }
        }

    }
}