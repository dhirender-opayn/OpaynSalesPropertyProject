package com.example.opaynpropertyproject.home_activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.opaynpropertyproject.R
import com.example.opaynpropertyproject.VisiterPropertiesAdapter
import com.example.opaynpropertyproject.seller_home_adapter.FacilitiesRecyclerViewAdapter
import kotlinx.android.synthetic.main.activity_selected_property.*

class SelectedPropertyActivity : AppCompatActivity() {
    val list = ArrayList<String>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_selected_property)
        overridePendingTransition(0,0)


        //visiter
        rv_visiter_review.adapter = VisiterPropertiesAdapter()

        //Facilities
        rv_selected_property_facilities.adapter = FacilitiesRecyclerViewAdapter()
    }
}