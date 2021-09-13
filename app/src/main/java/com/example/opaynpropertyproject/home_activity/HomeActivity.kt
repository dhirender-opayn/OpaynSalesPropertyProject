package com.example.opaynpropertyproject.home_activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.navigation.Navigation
import androidx.navigation.ui.NavigationUI
import com.example.opaynpropertyproject.R
import com.example.opaynpropertyproject.api.ApiResponse
import com.example.opaynpropertyproject.api.Keys
import com.example.opaynpropertyproject.comman.BaseActivity
import com.google.android.material.bottomnavigation.BottomNavigationView
import kotlinx.android.synthetic.main.activity_home.*
import kotlinx.android.synthetic.main.toolbar.*

class HomeActivity : BaseActivity(), View.OnClickListener,ApiResponse {
    val token =
        "eyJ0eXAiOiJKV1QiLCJhbGciOiJSUzI1NiJ9.eyJhdWQiOiIxIiwianRpIjoiZTc0M2QxNTQwNGRhMWQ4NjU1MGI1Njk1MjBlMDJhN2U2OTdiNGFiMDY2MjgyZTgwMjc1ODJhMmZjNmEzMDQ4YTI0M2MyNTljZTUyYzAwYTkiLCJpYXQiOjE2MzA5MDY1OTQuNzI0NTY0LCJuYmYiOjE2MzA5MDY1OTQuNzI0NTY1LCJleHAiOjE2NjI0NDI1OTQuNzE5NTc1LCJzdWIiOiIyMyIsInNjb3BlcyI6W119.miTFb8YyDL186NlROqnHHjOVnCdRXEs2lhDOk_V6LLaiImsfhtQ3KBrBiPYC7hrOOXY1xLfzu1pPNCS-y21SSiB_iQ403f1i3lh_LRUSzTe5EGyNc4Ejz3Ixuekw1iuWJq_yFmvqi4j14UL_BI55y4jgtUwhV2Z3MVMgxcHmRoYKem2Tr6UhW6OiVLjzt2HExJc5JsT6SCjbSc4UXSW4IV6V-_46Q3Vv5s8iylm0vmZBF4IJso2xJPwydVd8s7jIOq1y3CPCVj6gWkHyi_SlKTQfAqKMcBDjhg1sFIGGsJP-foORlvtsR33mp2AG7Rp46R2saqH5baMEnj-W6-KFhuYQs83-Fa-nGZC8cuTH7laWD_t-7jOU_whtbHS_Ydf46gN5TSQFAOLEsTBI310qhpgTpEAf1qkinUFDEAPdJ-XuRS_iaVBFLGmwcmrsphpGpAK5_tdqTY52OSUdq63CRqJfY6UkSBdD5dWQpb9wUy-iAvb010qea1duZWqXTnVuZ0bjXRuGx_Zo4_lB8FZCLFcs0D09mlJSx_Iu_72o8vp6lH9pbCYIFMQY9s7bHeZuyyOhP1W_W4qHljT6AXO16joUrArEY3FTGvj9Sxkfzy_KVGRSxb-VerbB54bx2eHM7Zaacx3iHOWZivucINzr96BtessxXZNw1jtdkkCUBf8"


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)
        supportActionBar!!.hide()
        search_bar_container.visibility = View.INVISIBLE
        ads.visibility = View.VISIBLE
        header_filer.visibility = View.INVISIBLE
        setUpNavigation()




//        val firstFragment=FirstFragment()
//
//        setCurrentFragment(firstFragment)
//
//        bottomNavigationView.setOnNavigationItemSelectedListener {
//            when(it.itemId){
//                R.id.home->setCurrentFragment(firstFragment)
//                R.id.person->setCurrentFragment(secondFragment)
//                R.id.settings->setCurrentFragment(thirdFragment)
//
//            }
//            true
//        }

    }

    private fun setUpNavigation(){
        val navController = Navigation.findNavController(this,R.id.nav_container)
        val bottomNavigationView = findViewById<BottomNavigationView>(R.id.bottomNavigationView)
        NavigationUI.setupWithNavController(bottomNavigationView,navController)

    }

    override fun onClick(v: View?) {
        when (v!!.id) {


        }
    }

    override fun onResponse(requestcode: Int, response: String) {

    }
}