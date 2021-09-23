package com.example.opaynpropertyproject.login_signup_activity


import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.util.Log
import android.view.View
import com.example.opaynpropertyproject.customer.CustomerHomeActivity
import com.example.opaynpropertyproject.R
import com.example.opaynpropertyproject.api.ApiResponse
import com.example.opaynpropertyproject.api.Keys
import com.example.opaynpropertyproject.api.Keys.TOKEN
import com.example.opaynpropertyproject.api.Keys.USERDATA
import com.example.opaynpropertyproject.api.Keys.USERID
import com.example.opaynpropertyproject.api_model.ErrorModel
import com.example.opaynpropertyproject.api_model.LoginSuccessModel
import com.example.opaynpropertyproject.comman.BaseActivity
import com.example.opaynpropertyproject.comman.SharedPreferenceManager
import com.example.opaynpropertyproject.comman.Utils
import com.example.opaynpropertyproject.comman.Utils.customSnakebar
import com.example.opaynpropertyproject.home_activity.HomeActivity
import com.example.opaynpropertyproject.home_activity.HomeActivity.Companion.token
import kotlinx.android.synthetic.main.activity_login.*
import java.security.Key

class LoginActivity : BaseActivity(), View.OnClickListener, ApiResponse {
    lateinit var loginHashMap: HashMap<String, Any>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        overridePendingTransition(0,0)
        setContentView(R.layout.activity_login)
        supportActionBar!!.hide()
        loginHashMap = HashMap<String, Any>()
//        getuserdata()
        login_container.setOnClickListener(this)
        create_account_btn_login.setOnClickListener(this)
        forget_password.setOnClickListener(this)
        login_button.setOnClickListener(this)

    }

    private fun loginUserValue(): Boolean {
        val mLogin_email = login_email.text.toString().trim()
        if (mLogin_email.isEmpty()) {
            Utils.customSnakebar(login_email, getString(R.string.email_required))
            return false
        }
        if (!Utils.isValidEmailId(mLogin_email)) {
            Utils.customSnakebar(login_email, getString(R.string.email_pattern))
            return false
        }
        if (login_password.text.toString().trim().isEmpty()) {
            Utils.customSnakebar(login_password, getString(R.string.password_required))
            return false
        }
        return true
    }

    override fun onClick(v: View?)
    {
        when (v!!.id) {
            R.id.login_button ->
            {
                Log.e("button", "click")
                if (loginUserValue())
                {
                    loginHashMap.put(Keys.login_email, login_email.text.toString().trim())
                    loginHashMap.put(Keys.login_password, login_password.text.toString().trim())

                    serviceViewModel.postservice(Keys.loginEndPoint, this, loginHashMap, Keys.login_log, false, "", true, this)
                }
            }

            R.id.create_account_btn_login -> {
                val intent = Intent(this, SignUpActivity::class.java)
                startActivity(intent)
            }
            R.id.forget_password -> {
                val intent = Intent(this, ForgetPasswordActivity::class.java)
                startActivity(intent)
            }
             R.id.login_container->{
                 Utils.hideKeyboard(this)
             }
        }
    }
    private  fun getuserdata()
    {
        Handler(getMainLooper()).postDelayed({
            SharedPreferenceManager(this).getString(Keys.USERID).let {
                if (it==null||it.toString().equals(""))
                {

                }
                else{
                    openA(HomeActivity::class)
                    finishAffinity()
                }
            }

        }, 1000)

    }
    override fun onResponse(requestcode: Int, response: String) {
        when (requestcode) {
            Keys.login_log ->
            {
                val model = gson.fromJson(response, LoginSuccessModel::class.java)
              val user_type = model.data.user.roles[0].name
                if (user_type.equals(Keys.CUSTOMER)){
                    if (model?.data!=null)
                    {
                        SharedPreferenceManager(this).saveString(TOKEN,model.data.token)
                        SharedPreferenceManager(this).saveString(USERDATA,response)
                        SharedPreferenceManager(this).saveString(USERID,model.data.user.id.toString())

                        token = model.data.token

                        openA(CustomerHomeActivity::class)
                        finishAffinity()
                        Keys.isCustomer = true

                    }
                } else {
                    if (model?.data!=null)
                    {
                        SharedPreferenceManager(this).saveString(TOKEN,model.data.token)
                        SharedPreferenceManager(this).saveString(USERDATA,response)
                        SharedPreferenceManager(this).saveString(USERID,model.data.user.id.toString())

                        token = model.data.token

                        openA(HomeActivity::class)
                        finishAffinity()

                    }
                }

            }

            Keys.BACKENDERROR -> {
                val errorModel = gson.fromJson(response, ErrorModel::class.java)
                customSnakebar(login_button, errorModel.message.toString())
                Log.e("eeee", response.toString())
            }
        }

    }

}