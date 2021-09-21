package com.example.opaynpropertyproject.home_activity

import android.os.Bundle
import android.view.View
import androidx.core.view.GravityCompat
import androidx.navigation.Navigation
import androidx.navigation.ui.NavigationUI
import com.example.opaynpropertyproject.AboutUsFragment
import com.example.opaynpropertyproject.ContactUsFragment
import com.example.opaynpropertyproject.FAQFragment
import com.example.opaynpropertyproject.R
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

class HomeActivity : BaseActivity(), View.OnClickListener, ApiResponse {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)
        overridePendingTransition(0, 0)
        homeHeader()
        setUpNavigation()
        token = SharedPreferenceManager(this).getString(Keys.TOKEN).toString()

        menu_bar.setOnClickListener(this)
        nav_view.add_property.setOnClickListener(this)
        my_property.setOnClickListener(this)
        faq.setOnClickListener(this)
        about.setOnClickListener(this)

    }

   private fun homeHeader() {
        supportActionBar!!.hide()
        search_bar_container.visibility = View.INVISIBLE
        ads.setText(getString(R.string.home))
        header_filer.visibility = View.INVISIBLE
    }

    companion object {
        var token = ""
    }

    private fun setUpNavigation() {
        val navController = Navigation.findNavController(this, R.id.nav_container)
        val bottomNavigationView = findViewById<BottomNavigationView>(R.id.bottomNavigationView)
        NavigationUI.setupWithNavController(bottomNavigationView, navController)
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
                val fragment = FAQFragment()
                Utils.addReplaceFragment(this, fragment, R.id.nav_container, false, false, false)
            }
            R.id.about -> {
                drawer_layout.closeDrawers()
                val fragment = AboutUsFragment()
                Utils.addReplaceFragment(this, fragment, R.id.nav_container, false, false, false)
            }
            R.id.contact_us -> {
                drawer_layout.closeDrawers()
                val fragment = ContactUsFragment()
                Utils.addReplaceFragment(this, fragment, R.id.nav_container, false, false, false)
            }
            R.id.account_setting -> {
//                val fragment =
//                Utils.addReplaceFragment(this,fragment,R.id.nav_container,false,false,false)
            }

        }
    }

    override fun onResponse(requestcode: Int, response: String) {

    }
}