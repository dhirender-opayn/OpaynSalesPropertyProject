package com.example.opaynpropertyproject.adapter

import android.app.Activity
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.opaynpropertyproject.R
import com.example.opaynpropertyproject.`interface`.GetPositionInterface
import com.example.opaynpropertyproject.api_model.seller_home_model.SellerPropertyListingModel
import com.squareup.picasso.Picasso

import kotlinx.android.synthetic.main.seller_home_list_holder.view.*

class HomeRecommendInnerAdapter(

    val homeInnerPropertyList: ArrayList<SellerPropertyListingModel.Data>,
    val activity: Activity,
    val home_fav_position: GetPositionInterface
) : RecyclerView.Adapter<HomeRecommendInnerAdapter.HomeRecommendInnerViewHolder>() {


    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): HomeRecommendInnerViewHolder {
        val view = HomeRecommendInnerViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.seller_home_list_holder, parent, false)
        )
        return view
    }

    override fun onBindViewHolder(holder: HomeRecommendInnerViewHolder, position: Int) {

        if (homeInnerPropertyList[position].profile != null) {


            holder.dealer_home_address.setText(homeInnerPropertyList[position].address)
            holder.dealer_home_sub_address.setText(homeInnerPropertyList[position].description)
            holder.dealer_home_price.setText(homeInnerPropertyList[position].price.toString())
            holder.dealer_home_bed.setText(homeInnerPropertyList[position].profile.bed_rooms.toString())
            holder.dealer_home_bathroom.setText(homeInnerPropertyList[position].profile.bath_rooms.toString())
            holder.dealer_home_area.setText(homeInnerPropertyList[position].profile.area.toString())
            holder.dealer_home_sold_status.visibility = View.INVISIBLE


            if (homeInnerPropertyList[position].image != null) {
                Picasso.get().load(homeInnerPropertyList[position].image.image)
                    .placeholder(R.drawable.down_arrow).into(holder.dealer_home_property_img)
            }
            holder.dealer_home_add_fav.setOnClickListener {
                Log.e("click", "Fav click")
                home_fav_position.getPosition(holder.adapterPosition)
                it.setBackgroundResource(R.drawable.ic_red_heart)
            }
            holder.property_des_container.setOnClickListener {

            }

        }

    }

    override fun getItemCount(): Int {
        return homeInnerPropertyList.size
    }

    class HomeRecommendInnerViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        val dealer_home_property_img = itemView.your_ads_img
        val diable_home_cancel = itemView.your_ads_cancel_btn
        val disable_edit = itemView.your_ads_edit
        val customer_forward_btn = itemView.customer_your_ads_forward
        val disable_home_forward = itemView.your_ads_forward
        val dealer_home_add_fav = itemView.c_fav

        val dealer_home_sold_status = itemView.is_your_ads_sold
        val disable_make_it_feature = itemView.make_feauture_container

        //
        val dealer_home_address = itemView.yours_ads_head_address
        val dealer_home_sub_address = itemView.yours_ads_sub__address
        val dealer_home_price = itemView.yours_ads_price
        val dealer_home_rating = itemView.yours_ads_rating_bar
        val dealer_home_sub_rating = itemView.yours_ads_rating_view
        val dealer_home_bed = itemView.yours_ads_bed
        val dealer_home_bathroom = itemView.yours_ads_bathroom
        val dealer_home_area = itemView.yours_ads_area
        val property_des_container = itemView.property_txt_container
    }
}