package com.example.opaynpropertyproject.LoginSignUpFragment

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.example.opaynpropertyproject.R
import kotlinx.android.synthetic.main.activity_login.*

class LoginActivity : AppCompatActivity(), View.OnClickListener {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        supportActionBar!!.hide()
        create_account_btn_login.setOnClickListener(this)
        forget_password.setOnClickListener(this)

    }

    override fun onClick(v: View?) {
        when(v!!.id){
            R.id.create_account_btn_login->{
                val intent = Intent(this,SignUpActivity::class.java)
                startActivity(intent)
            }
            R.id.forget_password->{
                val intent = Intent(this,ForgetPasswordActivity::class.java)
                startActivity(intent)
            }
        }
    }
}