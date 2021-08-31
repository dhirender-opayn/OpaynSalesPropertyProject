package com.example.opaynpropertyproject.home_activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.example.opaynpropertyproject.R
import com.example.opaynpropertyproject.comman.BaseActivity
import kotlinx.android.synthetic.main.activity_home.*
import kotlinx.android.synthetic.main.toolbar.*

class HomeActivity : BaseActivity(), View.OnClickListener {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)
        supportActionBar!!.hide()
        search_bar_container.visibility = View.INVISIBLE
        ads.visibility = View.VISIBLE
        header_filer.visibility = View.INVISIBLE


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

    override fun onClick(v: View?) {
        when (v!!.id) {


        }
    }
}