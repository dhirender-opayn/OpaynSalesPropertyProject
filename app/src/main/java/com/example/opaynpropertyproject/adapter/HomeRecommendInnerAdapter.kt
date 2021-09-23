package com.example.opaynpropertyproject.adapter

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.text.LoginFilter
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.opaynpropertyproject.R
import com.example.opaynpropertyproject.`interface`.GetPositionInterface
import com.example.opaynpropertyproject.addAdsSlides.DealerAddActivity
import com.example.opaynpropertyproject.api_model.seller_home_model.SellerPropertyListingModel
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.recommend_inner_home_view_holder.view.*
import kotlinx.android.synthetic.main.seller_home_list_holder.view.*

class HomeRecommendInnerAdapter(
    val homeInnerPropertyList: ArrayList<SellerPropertyListingModel.Data>,
    val activity: Activity, val home_fav_position: GetPositionInterface
) : RecyclerView.Adapter<HomeRecommendInnerAdapter.HomeRecommendInnerViewHolder>() {


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

        if (homeInnerPropertyList[position].profile != null) {
            holder.home_head_address.setText(homeInnerPropertyList[position].address)
            holder.home_sub__address.setText(homeInnerPropertyList[position].description)
            holder.home_price.setText(homeInnerPropertyList[position].price.toString())
            holder.home_bed.setText(homeInnerPropertyList[position].profile.bed_rooms.toString())
            holder.home_bathroom.setText(homeInnerPropertyList[position].profile.bath_rooms.toString())
            holder.home_area.setText(homeInnerPropertyList[position].profile.area.toString())
            holder.home_fav.setOnClickListener {
                holder.home_fav.setBackgroundColor(Color.RED)
                home_fav_position.getPosition(holder.adapterPosition)
            }


            if (homeInnerPropertyList[position].image != null) {
                Picasso.get().load(homeInnerPropertyList[position].image.image)
                    .placeholder(R.drawable.down_arrow).into(holder.home_property_img)
            }

        }


    }

    override fun getItemCount(): Int {
        return homeInnerPropertyList.size
    }

    class HomeRecommendInnerViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val home_property_img = itemView.c_home_img
        val home_forward = itemView.c_home_forward
        val home_fav = itemView.c_fav
        val home_is_sold = itemView.c_home_is_sold
        val home_head_address = itemView.c_home_head_address
        val home_sub__address = itemView.c_home_sub__address
        val home_price = itemView.c_home_price
        val home_rating_bar = itemView.c_home_rating_bar
        val yours_ads_rating_view = itemView.c_yours_ads_rating_view
        val home_bed = itemView.c_home_bed
        val home_bathroom = itemView.c_home_bathroom
        val home_area = itemView.c_home_area

    }
}