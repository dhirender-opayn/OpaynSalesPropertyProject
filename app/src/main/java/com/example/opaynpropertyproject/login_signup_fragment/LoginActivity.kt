package com.example.opaynpropertyproject.login_signup_fragment


import ServiceViewModel
import android.content.Intent
 import android.os.Bundle
import android.util.Log
import android.view.View
import com.example.opaynpropertyproject.R
import com.example.opaynpropertyproject.api.ApiResponse
import com.example.opaynpropertyproject.api.Keys
import com.example.opaynpropertyproject.comman.BaseActivity
import com.example.opaynpropertyproject.home_activity.HomeActivity
import kotlinx.android.synthetic.main.activity_login.*
 class LoginActivity : BaseActivity(), View.OnClickListener,ApiResponse {
    lateinit var loginHashMap: HashMap<String,Any>
    lateinit var serviceViewModel: ServiceViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        supportActionBar!!.hide()
        loginHashMap = HashMap<String,Any>()
        create_account_btn_login.setOnClickListener(this)
        forget_password.setOnClickListener(this)
        login_button.setOnClickListener(this)

    }

    private fun loginUserValue():HashMap<String,Any>{
        if (login_email.text.toString().trim().isEmpty()){
            customSnakebar(login_email,getString(R.string.email_required))
        }else if (login_password.text.toString().trim().isEmpty()){
            customSnakebar(login_password,getString(R.string.password_required))
        }else{
            loginHashMap.put(Keys.login_email,login_email.text.toString().trim())
            loginHashMap.put(Keys.login_password,login_password.text.toString().trim())


           return loginHashMap
        }
        return loginHashMap
    }

    override fun onClick(v: View?) {
        when(v!!.id){
            R.id.login_button->{
                Log.e("button","click")
                     loginUserValue()
                    if (loginHashMap.isEmpty()){
//                        customSnakebar(login_container,"Something Wrong May be fields are empty")
                    }else{
                        serviceViewModel = ServiceViewModel()
                        serviceViewModel.postservice(Keys.loginEndPoint,this,loginHashMap,Keys.login_log,false,"",true,this)
                        val intent = Intent(this,HomeActivity::class.java)
                        startActivity(intent)
                    }
            }

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

    override fun onResponse(requestcode: Int, response: String) {

    }
}