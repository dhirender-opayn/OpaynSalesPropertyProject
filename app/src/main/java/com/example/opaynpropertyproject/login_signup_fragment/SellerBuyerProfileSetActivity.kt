package com.example.opaynpropertyproject.login_signup_fragment

import ServiceViewModel
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import com.example.opaynpropertyproject.R
import com.example.opaynpropertyproject.api.ApiResponse
import com.example.opaynpropertyproject.api.Keys
import com.example.opaynpropertyproject.comman.BaseActivity
import kotlinx.android.synthetic.main.activity_seller_buyer_profile_set.*
import kotlinx.coroutines.Job
import okhttp3.internal.userAgent
import java.security.Key

class SellerBuyerProfileSetActivity : BaseActivity(), View.OnClickListener, ApiResponse {
    lateinit var serviceViewModel: ServiceViewModel
    var getUserId:Int= 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_seller_buyer_profile_set)
        supportActionBar!!.hide()
        profile_set_password_back_btn.setOnClickListener(this)
        buyer_btn.setOnClickListener(this)
        seller_btn.setOnClickListener(this)
        getUserId = intent.extras?.get(Keys.USER_ID).toString().toInt()

    }

    override fun onClick(v: View?) {

        when (v!!.id) {
            R.id.profile_set_password_back_btn -> {
                onBackPressed()
            }
            R.id.buyer_btn -> {
                apiHit(Keys.BUYER)

            }
            R.id.seller_btn -> {
                apiHit(Keys.DEALER)

            }
        }

    }

    fun apiHit(user_type: Int) {
        val hashMap = HashMap<String, Any>()
        hashMap.put(Keys.USER_ID, getUserId)
        hashMap.put(Keys.SIGNUP_USER_TYPE, user_type)

        if (user_type > 0 && Keys.USER_ID.isEmpty()) {
            customSnakebar(profile_set_container, "HashMap is Empty")
        } else {
            serviceViewModel = ServiceViewModel()
            serviceViewModel.postservice(
                Keys.signupEndPoint,
                this,
                hashMap,
                Keys.PROFILE_Log,
                true,
                "",
                true,
                this
            )
            val intent = Intent(this, OtpVerifyActivity::class.java)
            startActivity(intent)
        }


    }

    override fun onResponse(requestcode: Int, response: String) {

    }
}