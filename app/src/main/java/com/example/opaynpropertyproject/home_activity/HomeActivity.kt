package com.example.opaynpropertyproject.home_activity

import android.app.PendingIntent.getActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.View
import androidx.annotation.IdRes
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContentProviderCompat.requireContext
import androidx.core.view.GravityCompat
import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.NavigationUI
import com.example.opaynpropertyproject.*
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
import com.example.opaynpropertyproject.`interface`.GetPositionInterface
import com.example.opaynpropertyproject.adapter.customerAdapter.NotificationRecyclerAdapter
import com.example.opaynpropertyproject.api_model.SearchModel
import com.example.opaynpropertyproject.api_model.seller_home_model.SellerPropertyListingModel
import com.example.opaynpropertyproject.customer.NotificationFragment
import com.example.opaynpropertyproject.customer.SavedPropertyActivity
import com.example.opaynpropertyproject.seller_chat.ChatScreenFragment
import com.example.opaynpropertyproject.seller_chat.SellerChatFragment
import com.example.opaynpropertyproject.seller_home_adapter.SellerPropertyRecyclerViewAdapter
import com.example.opaynpropertyproject.seller_profile.ProfileFragment
import com.example.opaynpropertyproject.singleton.SingletonObject
import kotlinx.android.synthetic.main.fragment_profile.faq
import kotlinx.android.synthetic.main.fragment_seller.*
import kotlinx.android.synthetic.main.left_drawer_menu.*
import kotlinx.android.synthetic.main.toolbar.ads
import kotlinx.android.synthetic.main.toolbar.header_filer
import kotlinx.android.synthetic.main.toolbar.menu_bar


class HomeActivity : BaseActivity(), View.OnClickListener, ApiResponse {


    lateinit var navController: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)
        overridePendingTransition(0, 0)
        homeHeader()
//        setUpNavigation()
        token = SharedPreferenceManager(this).getString(Keys.TOKEN).toString()
        saved_user_name = SharedPreferenceManager(this).getString(Keys.USER_NAME).toString()
        saved_user_email = SharedPreferenceManager(this).getString(Keys.USER_EMAIL).toString()

        menu_bar.setOnClickListener(this)
        nav_view.add_property.setOnClickListener(this)
        my_property.setOnClickListener(this)
        faq.setOnClickListener(this)
        about.setOnClickListener(this)
        contact_us.setOnClickListener(this)
//        navController = setNavigationController()
        bottomNavigationView.menu.getItem(2).setChecked(true)
        Utils.addReplaceFragment(this, HomeFragment(), R.id.nav_container, false, false, false)


        //   switchFragment(this,HomeFragment())


//        searchListner()
        bottomNavigationClickListener()
    }

//    fun switchFragment(context: AppCompatActivity, fragment: Fragment) {
//        val fragment = fragment
//        context.supportFragmentManager.let { it1 ->
//            replaceFragment(it1, fragment, R.id.container, fragment::class.java.simpleName)
//        }
//    }

    //    fun replaceFragment(fragmentManager: androidx.fragment.app.FragmentManager, fragment: androidx.fragment.app.Fragment, @IdRes containerId: Int, tag: String) {
//        fragmentManager.beginTransaction().replace(containerId, fragment, tag)
//            .addToBackStack("backstack").commit()
//    }
    private fun homeHeader() {
        supportActionBar!!.hide()

        add_property.visibility = View.INVISIBLE
        notification_count.visibility = View.INVISIBLE
        search_bar_container.visibility = View.INVISIBLE
        ads.visibility = View.VISIBLE
        ads.setText(getText(R.string.home))
        header_filer.visibility = View.INVISIBLE

    }

    private fun setNavigationController(): NavController {
        val navController = Navigation.findNavController(
            this, R.id.nav_container
        )
        return navController
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
                    Utils.addReplaceFragment(
                        this,
                        ProfileFragment(),
                        R.id.nav_container,
                        false,
                        false,
                        false
                    )
                }
                R.id.notificationFragment -> {
                    Utils.addReplaceFragment(
                        this,
                        NotificationFragment(), R.id.nav_container, false, false, false
                    )
                }
                R.id.homeFragment -> {
                    Utils.addReplaceFragment(
                        this,
                        HomeFragment(), R.id.nav_container, false, false, false
                    )

                }
                R.id.sellerChatFragment -> {
                    Utils.addReplaceFragment(
                        this,
                        SellerChatFragment(), R.id.nav_container, false, false, false
                    )

                }
                R.id.searchBarFragment -> {
                    Utils.addReplaceFragment(
                        this,
                        SearchBarFragment(), R.id.nav_container, false, false, false
                    )

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
            R.id.wishlist -> {
                drawer_layout.closeDrawers()
                openA(SavedPropertyActivity::class)
            }
            R.id.customer_message -> {
                drawer_layout.closeDrawers()
                val fragment = SellerChatFragment()
                Utils.addReplaceFragment(this,fragment,R.id.nav_container,false,false,false)
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
                openA(AccountSettingActivity::class)
            }

        }
    }


    override fun onResponse(requestcode: Int, response: String) {
        when (requestcode) {


        }
    }


}