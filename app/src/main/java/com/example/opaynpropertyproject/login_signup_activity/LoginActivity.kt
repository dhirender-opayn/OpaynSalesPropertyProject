package com.example.opaynpropertyproject.login_signup_activity


import ServiceViewModel
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import com.example.opaynpropertyproject.R
import com.example.opaynpropertyproject.api.ApiResponse
import com.example.opaynpropertyproject.api.Keys
import com.example.opaynpropertyproject.api.Keys.TOKEN
import com.example.opaynpropertyproject.api_model.ErrorModel
import com.example.opaynpropertyproject.api_model.LoginSuccessModel
import com.example.opaynpropertyproject.comman.BaseActivity
import com.example.opaynpropertyproject.comman.SharedPreferenceManager
import com.example.opaynpropertyproject.comman.Utils
import com.example.opaynpropertyproject.comman.Utils.customSnakebar
import com.example.opaynpropertyproject.home_activity.HomeActivity
import kotlinx.android.synthetic.main.activity_login.*

class LoginActivity : BaseActivity(), View.OnClickListener, ApiResponse {
    lateinit var loginHashMap: HashMap<String, Any>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        supportActionBar!!.hide()
        loginHashMap = HashMap<String, Any>()
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

    override fun onResponse(requestcode: Int, response: String) {
        when (requestcode) {
            Keys.login_log ->
            {
                val model = gson.fromJson(response, LoginSuccessModel::class.java)
                if (model?.data!=null)
                {
                    SharedPreferenceManager(this).saveString(TOKEN,model.data.token)
                    openA(HomeActivity::class)

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