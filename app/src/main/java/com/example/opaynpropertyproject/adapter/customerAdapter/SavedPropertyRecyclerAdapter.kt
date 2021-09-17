package com.example.opaynpropertyproject.adapter.customerAdapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.opaynpropertyproject.R
import com.example.opaynpropertyproject.adapter.property_setup_adapters.AgeOfPropertyAdapter
import kotlinx.android.synthetic.main.saved_property_view_holder.view.*
import kotlinx.android.synthetic.main.seller_home_list_holder.view.*
import kotlinx.android.synthetic.main.seller_home_list_holder.view.your_ads_edit

class SavedPropertyRecyclerAdapter :RecyclerView.Adapter<SavedPropertyRecyclerAdapter.SavedPropertyViewHolder>(){


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SavedPropertyViewHolder {
        val view = SavedPropertyViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.saved_property_view_holder, parent, false)
        )
        return view
    }

    override fun onBindViewHolder(holder: SavedPropertyViewHolder, position: Int) {

    }

    override fun getItemCount(): Int {
       return 4
    }
    class SavedPropertyViewHolder(itemview:View):RecyclerView.ViewHolder(itemview){
        val property_image = itemView.saved_property_img
        val sold_status = itemView.saved_property_sold
        val your_ads_cancel = itemView.saved_property_cancel_btn
        val property_forward_btn =  itemView.saved_property_forward
        val property_fav = itemview.saved_property_fav

        val property_header = itemView.saved_property_head_address
        val property_address = itemView.saved_property_sub__address
        val property_price = itemView.saved_property_price
        val rating = itemView.saved_property_rating_bar
        val customer_rating = itemView.saved_property_rating_view
        val bed = itemView.saved_property_bed
        val bathroom = itemView.saved_property_bathroom
        val sqft = itemView.saved_property_area

    }
}