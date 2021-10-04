package com.example.opaynpropertyproject.home_activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import com.example.opaynpropertyproject.R
import com.example.opaynpropertyproject.VisiterPropertiesAdapter
import com.example.opaynpropertyproject.api.Keys
import com.example.opaynpropertyproject.api_model.CustomerHomeModel
import com.example.opaynpropertyproject.api_model.SellPropertyModel
import com.example.opaynpropertyproject.seller_home_adapter.FacilitiesRecyclerViewAdapter
import com.greetupp.extensions.isNull
import com.opaynkart.ui.adapters.ImageSliderAdapter
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_account_setting.*
import kotlinx.android.synthetic.main.activity_selected_property.*

class SelectedPropertyActivity : AppCompatActivity() {
    var selectedPropertyPositionID = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_selected_property)
        overridePendingTransition(0, 0)
        supportActionBar?.hide()

        selectedPropertyPositionID = intent.getIntExtra(Keys.POSITION, -1)

        propertyDetails()
        //visiter
        rv_visiter_review.adapter = VisiterPropertiesAdapter()

        //Facilities
        rv_selected_property_facilities.adapter = FacilitiesRecyclerViewAdapter()
        selected_ads_back_btn.setOnClickListener {
            onBackPressed()
        }
    }

    private fun propertyDetails() {
        val property_list = Keys.customerList

        if (property_list != null) {
            for (item in property_list.indices) {
                if (property_list[item].id == selectedPropertyPositionID) {
                    selected_ads_sold.visibility = View.INVISIBLE
                    selected_ads_edit.visibility = View.INVISIBLE

                    yours_ads_head_address.setText(property_list?.get(item)?.name.toString())
                    yours_ads_sub__address.setText(property_list?.get(item)?.address.toString())
                    yours_ads_price.setText("$ "+property_list?.get(item)?.price.toString())
                    yours_ads_bed.setText(property_list?.get(item)?.profile.bed_rooms.toString() + " Bed")
                    yours_ads_bathroom.setText(property_list?.get(item).profile.bath_rooms.toString()+" Ba")
                    yours_ads_area.setText(property_list?.get(item).profile.area.toString()+" sqft")
                    selected_ads_img.adapter = ImageSliderAdapter(this)



                }
            }
        }
    }
}