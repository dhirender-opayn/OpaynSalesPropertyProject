package com.example.opaynpropertyproject.adapters.ads_adapters

import android.content.Context
import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.opaynpropertyproject.R
import com.example.opaynpropertyproject.adapters.singleton.SingletonObject
import com.example.opaynpropertyproject.api_model.SellPropertyModel
import kotlinx.android.synthetic.main.amenities_view_holder.view.*

class AmenitiesRecyclerAdapter(
    val amenitiesList: List<SellPropertyModel.Data.Option>,
    val context: Context
) :
    RecyclerView.Adapter<AmenitiesRecyclerAdapter.AmenitiesViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AmenitiesViewHolder {
        val view = AmenitiesViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.amenities_view_holder, parent, false)
        )
        return view
    }

    override fun onBindViewHolder(holder: AmenitiesViewHolder, position: Int) {
        holder.amenities_name.text = amenitiesList[position].name
        if (amenitiesList[position].flag) {
            holder.amenities_name.setBackgroundResource(R.drawable.rectangle_border_fill)
            holder.amenities_name.setTextColor(Color.WHITE)
            SingletonObject.propertyFilling.amenties = amenitiesList[position].id.toString()
        } else {
            holder.amenities_name.setBackgroundResource(R.drawable.rectangel_border)
            holder.amenities_name.setTextColor(Color.BLACK)
        }
        holder.amenities_name.setOnClickListener {
            for (item in amenitiesList.indices) {
                if (item.equals(position)) {
                    amenitiesList[position].flag = true
                } else {
                    amenitiesList[item].flag = false
                }
            }
            notifyDataSetChanged()
        }

    }

    override fun getItemCount(): Int {
        return amenitiesList.size
    }

    class AmenitiesViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val amenities_name = itemView.rv_amenities_type
    }
}