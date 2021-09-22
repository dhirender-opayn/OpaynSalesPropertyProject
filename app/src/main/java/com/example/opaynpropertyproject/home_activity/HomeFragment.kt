package com.example.opaynpropertyproject.home_activity

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import com.example.opaynpropertyproject.R
import com.example.opaynpropertyproject.adapter.HomeRecommendRecyclerAdapter
import com.example.opaynpropertyproject.adapter.home_adapter.WidgetHomeAdapter
import com.example.opaynpropertyproject.comman.BaseFragment
import kotlinx.android.synthetic.main.activity_account_setting.*
import kotlinx.android.synthetic.main.fragment_home.*
import kotlinx.android.synthetic.main.toolbar.*


class HomeFragment : BaseFragment() {



    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_home, container, false)

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        rv_home_widget.adapter = WidgetHomeAdapter()

        rv_recommended_property_home.adapter  = HomeRecommendRecyclerAdapter(requireActivity())   ///CustomerHomeWidgetAdapter()

      //  rv_nearby_property.adapter = HomeRecommendRecyclerAdapter()



    }




}