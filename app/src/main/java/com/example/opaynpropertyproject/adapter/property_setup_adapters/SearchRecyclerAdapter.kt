package com.example.opaynpropertyproject.adapter.property_setup_adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.opaynpropertyproject.R
import com.example.opaynpropertyproject.api_model.SearchModel
import com.example.opaynpropertyproject.api_model.SearchModelSuccess
import com.greetupp.extensions.isNull
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.seller_home_list_holder.view.*

class SearchRecyclerAdapter(val searchList : ArrayList<SearchModelSuccess.Data.Data>): RecyclerView.Adapter<SearchRecyclerAdapter.SearchViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SearchViewHolder {
        val view = SearchViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.seller_home_list_holder, parent, false)
        )
        return view
    }

    override fun onBindViewHolder(holder: SearchViewHolder, position: Int) {


            holder.search_address.setText(searchList[position].address)
//            holder.customer_home_sub_address.setText(customerInnerHomeList[position].data[position].description.toString())
            holder.search_price.setText(searchList[position].price.toString())
            holder.disable_search_cancel.visibility = View.INVISIBLE
            holder.disable_edit.visibility = View.INVISIBLE
            holder.disable_make_it_feature.visibility = View.INVISIBLE
            holder.disable_search_forward.visibility = View.INVISIBLE

            holder.search_forward_btn.visibility = View.VISIBLE
            holder.search_add_fav.visibility = View.VISIBLE
            holder.search_sold_status.visibility = View.INVISIBLE

            //  Picasso.get().load((customerInnerHomeList[position].data[position].image.image).placeholder(R.drawable.down_arrow).into(holder.customer_home_property_img)
        if (!searchList[position].image.isNull()){
                Picasso.get().load(searchList[position].image.image).placeholder(R.drawable.down_arrow).into(holder.search_property_img)
            }



    }

    override fun getItemCount(): Int {
       return searchList.size
    }

    class SearchViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
        val search_property_img = itemView.your_ads_img
        val disable_search_cancel = itemView.your_ads_cancel_btn
        val disable_edit = itemView.your_ads_edit
        val search_forward_btn = itemView.customer_your_ads_forward
        val disable_search_forward = itemView.your_ads_forward
        val search_add_fav = itemView.c_fav

        val search_sold_status = itemView.is_your_ads_sold
        val disable_make_it_feature = itemView.make_feauture_container

        val search_address = itemView.yours_ads_head_address
        val search_sub_address = itemView.yours_ads_sub__address
        val search_price = itemView.yours_ads_price
        val search_rating = itemView.yours_ads_rating_bar
        val search_sub_rating = itemView.yours_ads_rating_view
        val search_bed = itemView.yours_ads_bed
        val search_bathroom = itemView.yours_ads_bathroom
        val search_area = itemView.yours_ads_area

    }
}