package com.example.opaynpropertyproject.home_activity

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.core.view.GravityCompat
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.navigation.ui.NavigationUI
import com.example.opaynpropertyproject.*
import com.example.opaynpropertyproject.addAdsSlides.DealerAddActivity
import com.example.opaynpropertyproject.api.ApiResponse
import com.example.opaynpropertyproject.api.Keys
import com.example.opaynpropertyproject.api_model.LoginSuccessModel
import com.example.opaynpropertyproject.comman.BaseActivity
import com.example.opaynpropertyproject.comman.SharedPreferenceManager
import com.example.opaynpropertyproject.comman.Utils
import com.google.android.material.bottomnavigation.BottomNavigationView
import kotlinx.android.synthetic.main.activity_home.*
import kotlinx.android.synthetic.main.fragment_profile.*
import kotlinx.android.synthetic.main.fragment_profile.view.*
import kotlinx.android.synthetic.main.toolbar.*
import com.example.opaynpropertyproject.customer.NotificationFragment
import com.example.opaynpropertyproject.login_signup_activity.LoginActivity
import com.example.opaynpropertyproject.seller_chat.SellerChatFragment
import com.example.opaynpropertyproject.seller_profile.ProfileFragment
import kotlinx.android.synthetic.main.fragment_profile.faq
import kotlinx.android.synthetic.main.toolbar.ads
import kotlinx.android.synthetic.main.toolbar.header_filer
import kotlinx.android.synthetic.main.toolbar.menu_bar


class HomeActivity : BaseActivity(), View.OnClickListener, ApiResponse {

    lateinit var navController: NavController
    var model: LoginSuccessModel? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)
        overridePendingTransition(0, 0)

        token = SharedPreferenceManager(this).getString(Keys.TOKEN).toString()
        saved_user_name = SharedPreferenceManager(this).getString(Keys.USER_NAME).toString()
        saved_user_email = SharedPreferenceManager(this).getString(Keys.USER_EMAIL).toString()
        val gUserModel = SharedPreferenceManager(this).getString(Keys.USERDATA).toString()
        model = gson.fromJson(gUserModel, LoginSuccessModel::class.java)

        homeHeader()
        btnOnClicked()

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
    private fun btnOnClicked() {
        menu_bar.setOnClickListener(this)
        nav_view.add_property.setOnClickListener(this)
        my_property.setOnClickListener(this)
        faq.setOnClickListener(this)
        about.setOnClickListener(this)
        contact_us.setOnClickListener(this)
        account_setting.setOnClickListener(this)
        logout.setOnClickListener(this)
    }

    private fun homeHeader() {
        supportActionBar!!.hide()

        add_property.visibility = View.VISIBLE
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
                nav_view.profile_name.setText(model?.data?.user?.name)
                nav_view.seller_phone_number.setText(model?.data?.user?.mobile.toString())
                nav_view.user_email.setText(model?.data?.user?.email)

                drawer_layout.openDrawer(GravityCompat.START)

            }
            R.id.add_property -> {
                drawer_layout.closeDrawers()
                openA(DealerAddActivity::class)
            }
            R.id.my_property -> {
                drawer_layout.closeDrawers()
                openA(SellerAddedAdsProperty::class)
            }

            R.id.customer_message -> {
                drawer_layout.closeDrawers()
                val fragment = SellerChatFragment()
                Utils.addReplaceFragment(this, fragment, R.id.nav_container, false, false, false)
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
            R.id.logout -> {
                Keys.isCustomer = false
                SharedPreferenceManager(this).saveString(Keys.USERID, "")
                SharedPreferenceManager(this).getString(Keys.USERID).let {
                    if (it == null || it.toString().equals("0")) {

                    } else {

                    }
                }
                openA(LoginActivity::class)
            }


        }
    }

    override fun onResponse(requestcode: Int, response: String) {
        when (requestcode) {


        }
    }


}