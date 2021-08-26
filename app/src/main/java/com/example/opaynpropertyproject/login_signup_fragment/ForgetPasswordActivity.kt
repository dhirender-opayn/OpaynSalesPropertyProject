package com.example.opaynpropertyproject.login_signup_fragment

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.example.opaynpropertyproject.R
import kotlinx.android.synthetic.main.activity_forget_password.*

class ForgetPasswordActivity : AppCompatActivity(),View.OnClickListener{
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_forget_password)
        supportActionBar!!.hide()
        forget_password_back_btn.setOnClickListener(this)
        forget_password_sendOtp_btn.setOnClickListener(this)
    }

    override fun onClick(v: View?) {
        when(v!!.id){
            R.id.forget_password_back_btn->{
                onBackPressed()
            }
            R.id.forget_password_sendOtp_btn->{
                val intent = Intent(this,OtpVerifyActivity::class.java)
                startActivity(intent)
            }
        }
    }
}