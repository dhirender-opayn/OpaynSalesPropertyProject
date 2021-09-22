package com.example.opaynpropertyproject.seller_profile

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.opaynpropertyproject.*
import com.example.opaynpropertyproject.addAdsSlides.DealerAddActivity
import com.example.opaynpropertyproject.api.Keys
import com.example.opaynpropertyproject.api_model.LoginSuccessModel
import com.example.opaynpropertyproject.comman.BaseFragment
import com.example.opaynpropertyproject.comman.SharedPreferenceManager
import com.example.opaynpropertyproject.comman.Utils
import com.example.opaynpropertyproject.home_activity.HomeActivity
import com.example.opaynpropertyproject.home_activity.SellerAddedAdsProperty
import com.example.opaynpropertyproject.login_signup_activity.LoginActivity
import kotlinx.android.synthetic.main.fragment_profile.*
import kotlinx.android.synthetic.main.toolbar.*


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
       profileHeader()
        setData()
        setclicks()
//       getProfileDeatils()
    }

    private fun profileHeader(){
        val activity = requireContext() as HomeActivity
        activity.ads.visibility = View.VISIBLE
        activity.ads.setText(getString(R.string.profile))
        activity.menu_bar.visibility = View.INVISIBLE
        activity.search_bar_container.visibility = View.INVISIBLE
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
        account_setting.setOnClickListener(this)
        add_property.setOnClickListener(this)
        my_property.setOnClickListener(this)
        faq.setOnClickListener(this)
        about.setOnClickListener(this)
        contact_us.setOnClickListener(this)

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
            R.id.account_setting -> {
                openA(AccountSettingActivity::class)
            }
            R.id.add_property -> {
                openA(DealerAddActivity::class)

            }
            R.id.my_property -> {
                openA(SellerAddedAdsProperty::class)
            }
            R.id.faq -> {
               openA(FAQActivity::class)
            }
            R.id.about -> {
                openA(AboutUsActivity::class)
            }
            R.id.contact_us -> {
              openA(ContactUsActivity::class)
            }
        }
    }


}