package com.example.opaynpropertyproject.home_activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import com.example.opaynpropertyproject.R
import com.example.opaynpropertyproject.comman.Utils
import kotlinx.android.synthetic.main.activity_seller_added_ads_property.*
import kotlinx.android.synthetic.main.toolbar.*
import kotlinx.android.synthetic.main.toolbar.*
import kotlinx.android.synthetic.main.toolbar.ads
import kotlinx.android.synthetic.main.toolbar.menu_bar

class SellerAddedAdsProperty : AppCompatActivity() ,View.OnClickListener{
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        supportActionBar!!.hide()

        setContentView(R.layout.activity_seller_added_ads_property)
        seller_all_property_back_btn.setOnClickListener(this)
        overridePendingTransition(0,0)

       sellerAddedAdsHeader()
        Utils.addReplaceFragment(this,SellerAddFragment(),R.id.frameId,false,false ,false)

    }

    private fun sellerAddedAdsHeader(){
        menu_bar.setImageResource(R.drawable.ic_baseline_arrow_back_ios_24)
        ads.setText(getString(R.string.your_ads))
        search_bar_container.visibility = View.INVISIBLE
        header_filer.visibility = View.INVISIBLE
        notification_count.visibility = View.INVISIBLE
    }
    override fun onClick(v: View?) {
      when(v!!.id){
          R.id.seller_all_property_back_btn -> {
              onBackPressed()
          }
      }
    }

}