package com.example.opaynpropertyproject.adapter.property_setup_adapters

import android.app.Activity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.opaynpropertyproject.R
import com.example.opaynpropertyproject.`interface`.GetPositionInterface
import com.example.opaynpropertyproject.api.Keys
import com.example.opaynpropertyproject.api_model.CustomerHomeModel
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.seller_home_list_holder.view.*

class CustomerHomeInnerAdapter(var customerInnerHomeList: ArrayList<CustomerHomeModel.Data>, val activity: Activity, val home_fav_position: GetPositionInterface) :
    RecyclerView.Adapter<CustomerHomeInnerAdapter.CustomerHomeInnerViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CustomerHomeInnerViewHolder {
        val view = CustomerHomeInnerViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.seller_home_list_holder, parent, false)
        )
        return view
    }

    override fun onBindViewHolder(holder: CustomerHomeInnerViewHolder, position: Int) {
        if (customerInnerHomeList[position].data.isNotEmpty()) {
            holder.customer_home_address.setText(customerInnerHomeList[position].data[position].address)
//            holder.customer_home_sub_address.setText(customerInnerHomeList[position].data[position].description.toString())
            holder.customer_home_price.setText(customerInnerHomeList[position].data[position].price.toString())
            holder.disable_home_cancel.visibility = View.INVISIBLE
            holder.disable_edit.visibility = View.INVISIBLE
            holder.disable_make_it_feature.visibility = View.INVISIBLE
            holder.disable_home_forward.visibility = View.INVISIBLE

            holder.customer_forward_btn.visibility = View.VISIBLE
            holder.customer_home_add_fav.visibility = View.VISIBLE
            holder.customer_home_sold_status.visibility = View.INVISIBLE

            if (customerInnerHomeList[position].data[position].image != null) {
              //  Picasso.get().load((customerInnerHomeList[position].data[position].image.image).placeholder(R.drawable.down_arrow).into(holder.customer_home_property_img)
                Picasso.get().load(customerInnerHomeList[position].data[position].image.image).placeholder(R.drawable.down_arrow).into(holder.customer_home_property_img)
            }

        }

    }

    override fun getItemCount(): Int {
       return  customerInnerHomeList.size
    }

    class CustomerHomeInnerViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val customer_home_property_img = itemView.your_ads_img
        val disable_home_cancel = itemView.your_ads_cancel_btn
        val disable_edit = itemView.your_ads_edit
        val customer_forward_btn = itemView.customer_your_ads_forward
        val disable_home_forward = itemView.your_ads_forward
        val customer_home_add_fav = itemView.c_fav

        val customer_home_sold_status = itemView.is_your_ads_sold
        val disable_make_it_feature = itemView.make_feauture_container

        //
        val customer_home_address = itemView.yours_ads_head_address
        val customer_home_sub_address = itemView.yours_ads_sub__address
        val customer_home_price = itemView.yours_ads_price
        val customer_home_rating = itemView.yours_ads_rating_bar
        val customer_home_sub_rating = itemView.yours_ads_rating_view
        val customer_home_bed = itemView.yours_ads_bed
        val customer_home_bathroom = itemView.yours_ads_bathroom
        val customer_home_area = itemView.yours_ads_area

    }
}