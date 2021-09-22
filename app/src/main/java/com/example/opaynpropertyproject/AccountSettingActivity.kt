package com.example.opaynpropertyproject

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.example.opaynpropertyproject.comman.BaseActivity
import com.example.opaynpropertyproject.home_activity.HomeActivity
import kotlinx.android.synthetic.main.fragment_profile.*
import kotlinx.android.synthetic.main.toolbar.*

class AccountSettingActivity : BaseActivity(), View.OnClickListener {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_account_setting)
        supportActionBar!!.hide()
        setClick()
        profileHeader()
    }

    private fun profileHeader() {

        ads.visibility = View.VISIBLE
        ads.setText(getString(R.string.account_setting))
        menu_bar.visibility = View.VISIBLE
        notification_count.visibility = View.INVISIBLE
        menu_bar.setImageResource(R.drawable.ic_baseline_arrow_back_ios_24)
        search_bar_container.visibility = View.INVISIBLE
        header_filer.visibility = View.INVISIBLE
    }

    private fun setClick() {
       menu_bar.setOnClickListener(this)

    }

    override fun onClick(v: View?) {
        when(v!!.id){
            R.id.menu_bar -> {
                onBackPressed()
            }
        }

    }
}