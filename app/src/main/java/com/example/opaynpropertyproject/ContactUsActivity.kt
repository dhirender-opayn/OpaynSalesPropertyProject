package com.example.opaynpropertyproject

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.util.Log
import android.view.View
import com.example.opaynpropertyproject.api.ApiResponse
import com.example.opaynpropertyproject.api.Keys
import com.example.opaynpropertyproject.api_model.ContactUsFromModelSuccess
import com.example.opaynpropertyproject.comman.BaseActivity
import com.example.opaynpropertyproject.comman.Utils
import com.example.opaynpropertyproject.customer.CustomerHomeActivity.Companion.token
import com.example.opaynpropertyproject.home_activity.HomeActivity
import kotlinx.android.synthetic.main.activity_contact_us.*
import kotlinx.android.synthetic.main.toolbar.*
import kotlinx.android.synthetic.main.toolbar.view.*


class ContactUsActivity : BaseActivity(), View.OnClickListener, ApiResponse {
    val contactUsHashMap = HashMap<String, Any>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_contact_us)
        setClick()
        contactHeader()


    }

    private fun contactUsValidation() {
        if (contact_name.text.toString().trim().isEmpty()) {
            Utils.customSnakebar(contactUs_btn, getString(R.string.name_required))
        } else if (contact_mobile.text.toString().trim().isEmpty()) {
            Utils.customSnakebar(contactUs_btn, getString(R.string.mobile_number_required))
        } else if (contact_email.text.toString().trim().isEmpty()) {
            Utils.customSnakebar(contactUs_btn, getString(R.string.email_required))
        } else if (!Utils.isValidEmailId(contact_email.text.toString().trim())) {
            Utils.customSnakebar(contactUs_btn, getString(R.string.email_pattern))
        } else if (contact_us_query_sub.text.toString().isEmpty()) {
            Utils.customSnakebar(contactUs_btn, getString(R.string.subject_query_required))
        } else if (contact_us_query_des.text.toString().isEmpty()) {
            Utils.customSnakebar(contactUs_btn, getString(R.string.des_query_required))
        } else {
            contactUsHashMap.put(Keys.USER_NAME, contact_name.text.toString().trim())
            contactUsHashMap.put(Keys.USER_MOBILE, contact_mobile.text.toString().trim())
            contactUsHashMap.put(Keys.USER_EMAIL, contact_email.text.toString().trim())
            contactUsHashMap.put(Keys.QUERY_SUB, contact_us_query_sub.text.toString().trim())
            contactUsHashMap.put(Keys.QUERY_DES, contact_us_query_des.text.toString().trim())
            contactUsApi()
        }
    }

    private fun contactUsApi() {
        serviceViewModel.postservice(
            Keys.CONTACT_US_END_POINT,
            this,
            contactUsHashMap,
            Keys.CONTACT_US_REQ_CODE,
            true,
            token,
            true,
            this
        )

    }

    private fun setClick() {


        contactUs_btn.setOnClickListener(this)
        contactus_form_container.setOnClickListener(this)


    }

    private fun contactHeader() {
        supportActionBar!!.hide()


        contact_toolbar.ads.visibility = View.VISIBLE
        contact_toolbar.menu_bar.visibility = View.VISIBLE
        contact_toolbar.ads.setText(getString(R.string.contactus))
        contact_toolbar.menu_bar.setImageResource(R.drawable.ic_baseline_arrow_back_ios_24)
        contact_toolbar.notification_count.visibility = View.INVISIBLE
        contact_toolbar.search_bar_container.visibility = View.INVISIBLE
        header_filer.visibility = View.INVISIBLE
        contact_toolbar.menu_bar.setOnClickListener {
            finish()
            Log.e("teststss","testststss")
        }




    }
    override fun onClick(v: View?) {
        when (v!!.id) {


            R.id.contactUs_btn -> {
                contactUsValidation()
            }
            R.id.contactus_form_container -> {
                Utils.hideKeyboard(this)
            }

        }
    }

    override fun onResponse(requestcode: Int, response: String) {
        when (requestcode) {
            Keys.CONTACT_US_REQ_CODE -> {
                val contactus_model = gson.fromJson(response, ContactUsFromModelSuccess::class.java)
                Utils.customSnakebar(contactUs_btn, contactus_model.message)
                Handler(Looper.getMainLooper()).postDelayed({
                    /* Create an Intent that will start the Menu-Activity. */
                    val mainIntent = Intent(this, HomeActivity::class.java)
                    startActivity(mainIntent)
                    finish()
                }, 2000)
            }
            Keys.BACKENDERROR -> {
                val contactus_model_error =
                    gson.fromJson(response, ContactUsFromModelSuccess::class.java)
                Utils.customSnakebar(contactUs_btn, contactus_model_error.message)
            }
        }
    }
}