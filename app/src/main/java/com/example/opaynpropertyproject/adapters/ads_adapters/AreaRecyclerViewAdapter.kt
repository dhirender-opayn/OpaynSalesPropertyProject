package com.example.opaynpropertyproject.adapters.ads_adapters

import android.content.Context
import android.content.res.ColorStateList
import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.view.menu.ActionMenuItemView
import androidx.recyclerview.widget.RecyclerView
import com.example.opaynpropertyproject.R
import com.example.opaynpropertyproject.adapters.singleton.SingletonObject
import com.example.opaynpropertyproject.api_model.SellPropertyModel
import kotlinx.android.synthetic.main.area_view_holder.view.*

class AreaRecyclerViewAdapter(val areaListHolder:  List<SellPropertyModel.Data.Option>, val context:Context):RecyclerView.Adapter<AreaRecyclerViewAdapter.AreaViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AreaViewHolder {
        val view = AreaViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.area_view_holder, parent, false)
        )
        return view
    }

    override fun onBindViewHolder(holder: AreaViewHolder, position: Int) {
        holder.area_name.text = areaListHolder[position].name
        if (areaListHolder[position].flag){
            holder.area_name.setBackgroundResource(R.drawable.rectangle_border_fill)
            holder.area_name.setTextColor(Color.WHITE)
            SingletonObject.propertyFilling.area = areaListHolder[position].id.toString()
        } else {
            holder.area_name.setBackgroundResource(R.drawable.rectangel_border)
            holder.area_name.setTextColor(Color.BLACK)
        }
        holder.area_name.setOnClickListener {
            for (item in areaListHolder.indices){
                if (item.equals(position)){
                    areaListHolder[position].flag = true
                } else {
                    areaListHolder[item].flag = false
                }
            }
            notifyDataSetChanged()
        }


    }

    override fun getItemCount(): Int {
        return areaListHolder.size

    }
    class AreaViewHolder(itemView: View):RecyclerView.ViewHolder(itemView){
        val area_name = itemView.rv_area_type

    }
}