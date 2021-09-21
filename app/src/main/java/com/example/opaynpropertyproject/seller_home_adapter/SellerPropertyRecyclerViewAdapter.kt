package com.example.opaynpropertyproject.seller_home_adapter

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.opaynpropertyproject.R
import com.example.opaynpropertyproject.addAdsSlides.DealerAddActivity
import com.example.opaynpropertyproject.api_model.seller_home_model.SellerPropertyListingModel
import com.example.opaynpropertyproject.singleton.SingletonObject
import com.example.opaynpropertyproject.singleton.SingletonObject.propertyFilling
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.seller_home_list_holder.view.*
import java.io.Serializable

class SellerPropertyRecyclerViewAdapter(val seller_property_list:List<SellerPropertyListingModel.Data>, val context: Context) :RecyclerView.Adapter<SellerPropertyRecyclerViewAdapter.YourAdsViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): YourAdsViewHolder {
        val view = YourAdsViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.seller_home_list_holder,parent,false))
        return view
    }

    override fun onBindViewHolder(holder: YourAdsViewHolder, position: Int) {

        holder.property_price.text = seller_property_list[position].price.toString()
        holder.property_header.text = seller_property_list[position].name
        holder.property_address.text = seller_property_list [position].address

      //  holder.bathroom.text = seller_property_list[position].profile.bath_rooms.toString()

//        if (seller_property_list[position].profile.bed_rooms != null){
//            holder.bed.text = seller_property_list[position].profile.bed_rooms.toString()
//        }
        if (seller_property_list[position].image!= null){
            Picasso.get().load(seller_property_list[position].image.image)
                .placeholder(R.drawable.down_arrow).into(holder.property_image)
        }

        holder.property_edit_btn.setOnClickListener {
            propertyFilling.edit_flag = true
            SingletonObject.propertyFilling.editpost = seller_property_list[position]
            propertyFilling.edit_id = seller_property_list[position].id
            val intent = Intent(context,DealerAddActivity::class.java)
             context.startActivity(intent)
        }
        holder.sold_status.visibility = View.INVISIBLE



    }

    override fun getItemCount(): Int {
        return  seller_property_list.size
    }

    class YourAdsViewHolder(itemView: View):RecyclerView.ViewHolder(itemView){
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