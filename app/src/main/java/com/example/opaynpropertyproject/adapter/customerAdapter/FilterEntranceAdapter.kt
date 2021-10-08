package com.example.opaynpropertyproject.adapter.customerAdapter

import android.content.Context
import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.opaynpropertyproject.R
import com.example.opaynpropertyproject.adapter.property_setup_adapters.EntranceRecyclerAdapter
import com.example.opaynpropertyproject.api_model.FilterModel
import com.example.opaynpropertyproject.api_model.SellPropertyModel
import com.example.opaynpropertyproject.singleton.SingletonObject
import kotlinx.android.synthetic.main.entrance_view_holder.view.*

class FilterEntranceAdapter(val filter_entranceList:List<FilterModel.Data.Option>, val context: Context) :RecyclerView.Adapter<FilterEntranceAdapter.FilterEntranceViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FilterEntranceViewHolder {
        val view =FilterEntranceViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.entrance_view_holder, parent, false)
        )
        return view

    }

    override fun onBindViewHolder(holder: FilterEntranceViewHolder, position: Int) {
        holder.entrance_name.text = filter_entranceList[position].name
        if (filter_entranceList[position].flag){
            holder.entrance_name.setBackgroundResource(R.drawable.rectangle_border_fill)
            holder.entrance_name.setTextColor(Color.WHITE)
            SingletonObject.propertyFilling.entrance = filter_entranceList[position].id.toString()
        } else {
            holder.entrance_name.setBackgroundResource(R.drawable.rectangel_border)
            holder.entrance_name.setTextColor(Color.BLACK)
        }
        holder.entrance_name.setOnClickListener {
            for (item in filter_entranceList.indices){
                if (item.equals(position)){
                    filter_entranceList[position].flag = true
                } else {
                    filter_entranceList[item].flag = false
                }
            }
            notifyDataSetChanged()
        }

    }

    override fun getItemCount(): Int {
        return filter_entranceList.size
    }
    class FilterEntranceViewHolder(itemView: View):RecyclerView.ViewHolder(itemView){
        val entrance_name = itemView.rv_entrance_type
    }
}