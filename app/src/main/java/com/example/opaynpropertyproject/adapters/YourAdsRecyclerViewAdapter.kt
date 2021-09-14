package com.example.opaynpropertyproject.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.opaynpropertyproject.R
import com.example.opaynpropertyproject.api_model.PropertyListingModel
import kotlinx.android.synthetic.main.your_ads_list_holder.view.*

class YourAdsRecyclerViewAdapter(val your_ads_list:List<PropertyListingModel.Data>, val context: Context) :RecyclerView.Adapter<YourAdsRecyclerViewAdapter.YourAdsViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): YourAdsViewHolder {
        val view = YourAdsViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.your_ads_list_holder,parent,false))
        return view
    }

    override fun onBindViewHolder(holder: YourAdsViewHolder, position: Int) {
        holder.your_ads_price.text = your_ads_list[position].price.toString()
        holder.your_ads_header.text = your_ads_list[position].name.toString()
        holder.your_ads_address.text = your_ads_list[position].address.toString()


    }

    override fun getItemCount(): Int {
        return  your_ads_list.size
    }

    class YourAdsViewHolder(itemView: View):RecyclerView.ViewHolder(itemView){


        val your_ads_image = itemView.your_ads_img
        val sold_status = itemView.is_your_ads_sold
        val make_feature = itemView.make_feauture_container
        val your_ads_cancel = itemView.your_ads_cancel_btn
        val your_ads_edit = itemView.your_ads_edit
        val your_ads_forward =  itemView.your_ads_forward


        val your_ads_header = itemView.yours_ads_head_address
        val your_ads_address = itemView.yours_ads_sub__address
        val your_ads_price = itemView.yours_ads_price
        val rating = itemView.yours_ads_rating_bar
        val customer_rating = itemView.yours_ads_rating_view
        val bed = itemView.yours_ads_bed
        val bathroom = itemView.yours_ads_bathroom
        val sqft = itemView.yours_ads_area

    }
}