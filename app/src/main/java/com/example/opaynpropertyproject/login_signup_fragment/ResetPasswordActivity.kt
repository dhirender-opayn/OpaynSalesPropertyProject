package com.example.opaynpropertyproject.login_signup_fragment

import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.example.opaynpropertyproject.R
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.activity_otp_verify.*
import kotlinx.android.synthetic.main.activity_reset_password.*

class ResetPasswordActivity : AppCompatActivity(),View.OnClickListener {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_reset_password)
        supportActionBar!!.hide()
        reset_password_back_btn.setOnClickListener(this)
        reset_password_btn.setOnClickListener(this)

    }

    override fun onClick(v: View?) {
        when(v!!.id){
            R.id.reset_password_back_btn->{
                onBackPressed()
            }
            R.id.reset_password_btn->{
                val snackbar = Snackbar.make(this,resend_otp_again_btn, "Reset Password Button is clicked..!!",
                    Snackbar.LENGTH_LONG)
                snackbar.view.setBackgroundColor(Color.parseColor("#E11616"))
                snackbar.setActionTextColor(Color.parseColor("#FFFFFF"))
                snackbar.show()

            }
        }


    }
}