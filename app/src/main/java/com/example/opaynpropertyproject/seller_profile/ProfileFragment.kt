package com.example.opaynpropertyproject.seller_profile

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.opaynpropertyproject.R
import com.example.opaynpropertyproject.api.ApiResponse
import com.example.opaynpropertyproject.api.Keys
import com.example.opaynpropertyproject.api_model.LoginSuccessModel
import com.example.opaynpropertyproject.comman.BaseFragment
import com.example.opaynpropertyproject.comman.SharedPreferenceManager
import com.example.opaynpropertyproject.home_activity.HomeActivity.Companion.saved_user_email
import com.example.opaynpropertyproject.home_activity.HomeActivity.Companion.saved_user_name
import com.example.opaynpropertyproject.home_activity.HomeActivity.Companion.token
import com.example.opaynpropertyproject.login_signup_activity.LoginActivity
import kotlinx.android.synthetic.main.fragment_profile.*


class ProfileFragment : BaseFragment(), View.OnClickListener {


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_profile, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setData()
        setclicks()
//       getProfileDeatils()
    }


    private fun setData() {
        val mGon = SharedPreferenceManager(requireContext()).getString(Keys.USERDATA)
        val model = gson.fromJson(mGon, LoginSuccessModel::class.java)
        val mUser_name = model.data.user.name
        val mUser_email = model.data.user.email
        val mUser_mobile = model.data.user.mobile

        profile_name.setText(mUser_name)
        user_email.setText(mUser_email)
        if (!mUser_mobile.equals(null)) {
            seller_phone_number.setText("+91-0000000000")
        } else {
            seller_phone_number.setText(mUser_mobile.toString())
        }



    }

    private fun setclicks() {
        logout.setOnClickListener(this)
    }

    override fun onClick(v: View?) {
        when (v!!.id) {
            R.id.logout -> {
                SharedPreferenceManager(requireContext()).saveString(Keys.USERID, "")
                SharedPreferenceManager(requireContext()).getString(Keys.USERID).let {
                    if (it == null || it.toString().equals("0")) {

                    } else {

                    }
                }
                openA(LoginActivity::class)
            }
        }
    }


}