package com.example.opaynpropertyproject.adapter.customerAdapter

import android.content.Context
import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.opaynpropertyproject.R
import com.example.opaynpropertyproject.adapter.property_setup_adapters.FurnishRecyclerAdapter
import com.example.opaynpropertyproject.api_model.FilterModel
import com.example.opaynpropertyproject.api_model.SellPropertyModel
import com.example.opaynpropertyproject.singleton.SingletonObject
import kotlinx.android.synthetic.main.furnishing_view_holder.view.*

class FilterFurnishAdapter(
    val filter_furnishList: List<FilterModel.Data.Option>,
    val context: Context
) : RecyclerView.Adapter<FilterFurnishAdapter.FilterFurnishViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FilterFurnishViewHolder {
        val view = FilterFurnishViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.furnishing_view_holder, parent, false)
        )
        return view
    }

    override fun onBindViewHolder(holder: FilterFurnishViewHolder, position: Int) {
        holder.furnish_name.text = filter_furnishList[position].name
        if (filter_furnishList[position].flag) {
            holder.furnish_name.setBackgroundResource(R.drawable.rectangle_border_fill)
            holder.furnish_name.setTextColor(Color.WHITE)
            SingletonObject.propertyFilling.furnish = filter_furnishList[position].id.toString()
        } else {
            holder.furnish_name.setBackgroundResource(R.drawable.rectangel_border)
            holder.furnish_name.setTextColor(Color.BLACK)
        }
        holder.furnish_name.setOnClickListener {
            for (item in filter_furnishList.indices) {
                if (item.equals(position)) {
                    filter_furnishList[position].flag = true
                } else {
                    filter_furnishList[item].flag = false
                }
            }
            notifyDataSetChanged()
        }
    }

    override fun getItemCount(): Int {
        return filter_furnishList.size

    }

    class FilterFurnishViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val furnish_name = itemView.rv_furnishing_type
    }
}