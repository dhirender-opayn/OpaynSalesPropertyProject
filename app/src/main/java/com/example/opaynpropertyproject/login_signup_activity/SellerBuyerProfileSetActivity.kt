package com.example.opaynpropertyproject.login_signup_activity


import android.os.Bundle
import android.view.View
import com.example.opaynpropertyproject.R
import com.example.opaynpropertyproject.api.ApiResponse
import com.example.opaynpropertyproject.api.Keys
import com.example.opaynpropertyproject.api_model.SuccessSignupModel
import com.example.opaynpropertyproject.comman.BaseActivity
import kotlinx.android.synthetic.main.activity_seller_buyer_profile_set.*
import ServiceViewModel
import com.example.opaynpropertyproject.comman.Utils

class SellerBuyerProfileSetActivity : BaseActivity(), View.OnClickListener, ApiResponse {
    lateinit var serviceViewModel: ServiceViewModel
    var getUserId: Int = 0
    var successSignupModel: SuccessSignupModel? = null
    lateinit var bundle: Bundle

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_seller_buyer_profile_set)
        supportActionBar!!.hide()
        profile_set_password_back_btn.setOnClickListener(this)
        buyer_btn.setOnClickListener(this)
        seller_btn.setOnClickListener(this)
        bundle = Bundle()
        val bundle = this.intent.extras
        if (bundle != null) {
            successSignupModel = bundle.getParcelable(Keys.PRACELABLE_KEY)
            getUserId = successSignupModel!!.data.user.id
        }




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
        hashMap.put(Keys.signup_Step, Keys.signup_step_two)

        if (user_type > 0 && Keys.USER_ID.isEmpty())
        {
            Utils.customSnakebar(profile_set_container, "HashMap is Empty")
        } else
        {
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


        }

    }

    override fun onResponse(requestcode: Int, response: String)
    {
        when (requestcode) {

            Keys.PROFILE_Log -> {

                var model: SuccessSignupModel? = null
                model = gson.fromJson(response, SuccessSignupModel::class.java)
                bundle.putParcelable(Keys.PRACELABLE_KEY, model)
                openA(OtpVerifyActivity::class, bundle)
            }
        }

    }
}