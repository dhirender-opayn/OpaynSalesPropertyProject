package com.example.opaynpropertyproject.home_activity

import android.app.PendingIntent.getActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.View
import androidx.core.content.ContentProviderCompat.requireContext
import androidx.core.view.GravityCompat
import androidx.navigation.Navigation
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.NavigationUI
import com.example.opaynpropertyproject.AboutUsActivity
import com.example.opaynpropertyproject.ContactUsActivity
import com.example.opaynpropertyproject.FAQActivity
import com.example.opaynpropertyproject.addAdsSlides.DealerAddActivity
import com.example.opaynpropertyproject.api.ApiResponse
import com.example.opaynpropertyproject.api.Keys
import com.example.opaynpropertyproject.comman.BaseActivity
import com.example.opaynpropertyproject.comman.SharedPreferenceManager
import com.example.opaynpropertyproject.comman.Utils
import com.google.android.material.bottomnavigation.BottomNavigationView
import kotlinx.android.synthetic.main.activity_home.*
import kotlinx.android.synthetic.main.fragment_profile.*
import kotlinx.android.synthetic.main.fragment_profile.view.*
import kotlinx.android.synthetic.main.toolbar.*
import com.example.opaynpropertyproject.R
import com.example.opaynpropertyproject.`interface`.GetPositionInterface
import com.example.opaynpropertyproject.api_model.SearchModel
import com.example.opaynpropertyproject.api_model.seller_home_model.SellerPropertyListingModel
import com.example.opaynpropertyproject.seller_home_adapter.SellerPropertyRecyclerViewAdapter
import com.example.opaynpropertyproject.singleton.SingletonObject
import kotlinx.android.synthetic.main.fragment_seller.*
import kotlinx.android.synthetic.main.toolbar.ads
import kotlinx.android.synthetic.main.toolbar.header_filer
import kotlinx.android.synthetic.main.toolbar.menu_bar


class HomeActivity : BaseActivity(), View.OnClickListener, ApiResponse  {



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)
        overridePendingTransition(0, 0)
        homeHeader()
        setUpNavigation()
        token = SharedPreferenceManager(this).getString(Keys.TOKEN).toString()
        saved_user_name = SharedPreferenceManager(this).getString(Keys.USER_NAME).toString()
        saved_user_email = SharedPreferenceManager(this).getString(Keys.USER_EMAIL).toString()

        menu_bar.setOnClickListener(this)
        nav_view.add_property.setOnClickListener(this)
        my_property.setOnClickListener(this)
        faq.setOnClickListener(this)
        about.setOnClickListener(this)
//        searchListner()
//        bottomNavigationClickListener()


    }

    private fun homeHeader() {
        supportActionBar!!.hide()
        notification_count.visibility = View.INVISIBLE
        ads.visibility = View.INVISIBLE
        header_filer.visibility = View.INVISIBLE

    }









    companion object {
        var token = ""
        var saved_user_name = ""
        var saved_user_email = ""
    }

    private fun setUpNavigation() {
        val navController = Navigation.findNavController(this, R.id.nav_container)
        val bottomNavigationView = findViewById<BottomNavigationView>(R.id.bottomNavigationView)

        NavigationUI.setupWithNavController(bottomNavigationView, navController)
    }

    private fun bottomNavigationClickListener() {
        bottomNavigationView.setOnItemSelectedListener { item ->
            Log.e("inn", "inner")
            when (item.itemId) {
                R.id.fragment_profile -> {
                    Log.e("profile", "profile")
                    notification_count.visibility = View.INVISIBLE
                    ads.setText("Profile")
                    header_filer.visibility = View.INVISIBLE
                }
            }
            true
        }

    }

    override fun onClick(v: View?) {
        when (v!!.id) {
            R.id.menu_bar -> {
                drawer_layout.openDrawer(GravityCompat.START)
            }
            R.id.add_property -> {
                drawer_layout.closeDrawers()
                openA(DealerAddActivity::class)
            }
            R.id.my_property -> {
                drawer_layout.closeDrawers()
                openA(SellerAddedAdsProperty::class)
//                val fragment = SellerAddFragment()
//                Utils.addReplaceFragment(this,fragment,R.id.nav_container,false,false,false)
            }
            R.id.faq -> {
                drawer_layout.closeDrawers()
                openA(FAQActivity::class)
            }
            R.id.about -> {
                drawer_layout.closeDrawers()
                openA(AboutUsActivity::class)
            }
            R.id.contact_us -> {
                drawer_layout.closeDrawers()
                openA(ContactUsActivity::class)
            }
            R.id.account_setting -> {
//                val fragment =
//                Utils.addReplaceFragment(this,fragment,R.id.nav_container,false,false,false)
            }

        }
    }


    override fun onResponse(requestcode: Int, response: String) {
        when (requestcode) {



        }
    }




}