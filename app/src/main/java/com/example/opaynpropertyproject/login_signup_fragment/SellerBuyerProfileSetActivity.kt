package com.example.opaynpropertyproject.login_signup_fragment

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.example.opaynpropertyproject.R
import kotlinx.android.synthetic.main.activity_seller_buyer_profile_set.*

class SellerBuyerProfileSetActivity : AppCompatActivity(),View.OnClickListener {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_seller_buyer_profile_set)
        supportActionBar!!.hide()
        profile_set_password_back_btn.setOnClickListener(this)
    }

    override fun onClick(v: View?) {
       when(v!!.id){
           R.id.profile_set_password_back_btn->{
               onBackPressed()
           }
       }
    }
}