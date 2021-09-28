package com.example.opaynpropertyproject

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.core.os.bundleOf
import com.example.opaynpropertyproject.api.ApiResponse
import com.example.opaynpropertyproject.api.Keys
import com.example.opaynpropertyproject.api_model.GetProfileSuccess
import com.example.opaynpropertyproject.api_model.LoginSuccessModel
import com.example.opaynpropertyproject.comman.BaseActivity
import com.example.opaynpropertyproject.comman.SharedPreferenceManager
import com.example.opaynpropertyproject.home_activity.HomeActivity
import com.example.opaynpropertyproject.home_activity.HomeActivity.Companion.token
import com.example.opaynpropertyproject.login_signup_activity.ForgetPasswordActivity
import com.greetupp.extensions.isNotNull
import com.greetupp.extensions.isNull
import kotlinx.android.synthetic.main.activity_account_setting.*
import kotlinx.android.synthetic.main.fragment_profile.*
import kotlinx.android.synthetic.main.toolbar.*
import kotlin.reflect.KClass

class AccountSettingActivity : BaseActivity(), View.OnClickListener, ApiResponse {
    var id = 0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_account_setting)
        supportActionBar!!.hide()
//        if (intent.getParcelableExtra<GetProfileSuccess>("key") != null) {
//            val user = intent.getParcelableExtra<GetProfileSuccess>("key")
//            profile_setting_user_name.setText(user?.data?.user?.name)
//            profile_setting_email.setText(user?.data?.user?.email)
//            profile_setting_phone.setText(user?.data?.user?.mobile)
//        }
        setClick()
        profileHeader()
        setData()
    }

    private fun profileHeader() {
        ads.visibility = View.VISIBLE
        ads.setText(getString(R.string.account_setting))
        menu_bar.visibility = View.VISIBLE
        notification_count.visibility = View.INVISIBLE
        menu_bar.setImageResource(R.drawable.ic_baseline_arrow_back_ios_24)
        search_bar_container.visibility = View.INVISIBLE
        header_filer.visibility = View.INVISIBLE
    }


    private fun setData() {
        val mGon = SharedPreferenceManager(this).getString(Keys.USERDATA)
        val model = gson.fromJson(mGon, LoginSuccessModel::class.java)
        profile_setting_user_name.setText(model.data.user.name)
        profile_setting_email.setText(model.data.user.email)
        profile_setting_phone.setText(model.data.user.mobile.toString())

    }

    private fun setClick() {
        menu_bar.setOnClickListener(this)
        profile_save_btn.setOnClickListener(this)
        change_password.setOnClickListener(this)
    }

    override fun onClick(v: View?) {
        when (v!!.id) {
            R.id.menu_bar -> {
                onBackPressed()
            }
            R.id.profile_save_btn -> {
                profileApi()
            }
            R.id.change_password -> {

                openA(ForgetPasswordActivity::class)
            }
        }

    }

//    private fun setData() {
//        val mGon = SharedPreferenceManager(this).getString(Keys.USERDATA)
//        val model = gson.fromJson(mGon, LoginSuccessModel::class.java)
//        id = model.data.user.id
//        val mUser_name = model.data.user.name
//        val mUser_email = model.data.user.email
//        val mUser_mobile = model.data.user.mobile
//    }

    private fun profileApi() {
        val profile_user_name = profile_setting_user_name.text.toString()
        val profile_setting_email = profile_setting_email.text.toString()
        val profile_setting_phone = profile_setting_phone.text.toString()
        val profileHashMap = HashMap<String, Any>()
        profileHashMap.put(Keys.USERID, id)
        profileHashMap.put(Keys.USER_NAME, profile_user_name)
        profileHashMap.put(Keys.USER_EMAIL, profile_setting_email)
        profileHashMap.put(Keys.USER_MOBILE, profile_setting_phone)

        serviceViewModel.postservice(
            Keys.PROFILE_END_POINT,
            this,
            profileHashMap,
            Keys.PROFILE_RED_CODE,
            true,
            token,
            true,
            this
        )
    }

    override fun onResponse(requestcode: Int, response: String) {
        when (requestcode) {
            Keys.PROFILE_RED_CODE -> {
                SharedPreferenceManager(this).saveString(Keys.USERDATA, response)
            }
            Keys.BACKENDERROR -> {

            }
        }
    }
}