package com.example.opaynpropertyproject.seller_home_adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.opaynpropertyproject.R
import com.example.opaynpropertyproject.api_model.CustomerHomeModel
import kotlinx.android.synthetic.main.facility_view_holder.view.*

class FacilitiesRecyclerViewAdapter():RecyclerView.Adapter<FacilitiesRecyclerViewAdapter.FacilitiesViewHolder>(){


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FacilitiesViewHolder {
        val view = FacilitiesViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.facility_view_holder, parent, false)
        )
        return view
    }

    override fun onBindViewHolder(holder: FacilitiesViewHolder, position: Int) {


    }

    override fun getItemCount(): Int {
       return   10
    }
    class  FacilitiesViewHolder(itemView:View):RecyclerView.ViewHolder(itemView){
        val facilities_img = itemView.facility_img
    }
}