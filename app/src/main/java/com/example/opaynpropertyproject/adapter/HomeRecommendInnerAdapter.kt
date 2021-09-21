package com.example.opaynpropertyproject.adapter

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.text.LoginFilter
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.opaynpropertyproject.R
import com.example.opaynpropertyproject.addAdsSlides.DealerAddActivity
import kotlinx.android.synthetic.main.seller_home_list_holder.view.*

class HomeRecommendInnerAdapter(val activity: Activity) :RecyclerView.Adapter<HomeRecommendInnerAdapter.HomeRecommendInnerViewHolder>(){


    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): HomeRecommendInnerViewHolder {
        val view = HomeRecommendInnerViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.recommend_inner_home_view_holder, parent, false)
        )
        return view
    }

    override fun onBindViewHolder(holder: HomeRecommendInnerViewHolder, position: Int) {

//        if (holder.property_edit_btn != null){
//            holder.property_edit_btn.setOnClickListener{
//                val intent = Intent(activity,DealerAddActivity::class.java)
//                activity.startActivity(intent)
//            }
//        } else {
//            Log.e("nullcheck","null")
//        }

    }

    override fun getItemCount(): Int {
        return 4
    }

    class HomeRecommendInnerViewHolder(itemView:View):RecyclerView.ViewHolder(itemView){
        val property_image = itemView.your_ads_img
        val sold_status = itemView.is_your_ads_sold
        val make_feature = itemView.make_feauture_container
        val your_ads_cancel = itemView.your_ads_cancel_btn
        val property_edit_btn = itemView.your_ads_edit
        val property_forward_btn =  itemView.your_ads_forward


        val property_header = itemView.yours_ads_head_address
        val property_address = itemView.yours_ads_sub__address
        val property_price = itemView.yours_ads_price
        val rating = itemView.yours_ads_rating_bar
        val customer_rating = itemView.yours_ads_rating_view
        val bed = itemView.yours_ads_bed
        val bathroom = itemView.yours_ads_bathroom
        val sqft = itemView.yours_ads_area
    }
}