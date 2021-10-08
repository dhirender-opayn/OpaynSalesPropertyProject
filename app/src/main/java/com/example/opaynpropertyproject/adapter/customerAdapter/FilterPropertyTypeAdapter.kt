package com.example.opaynpropertyproject.adapter.customerAdapter

import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.opaynpropertyproject.R
import com.example.opaynpropertyproject.api_model.FilterModel
import com.example.opaynpropertyproject.singleton.SingletonObject
import kotlinx.android.synthetic.main.property_type_view_holder.view.*
import kotlinx.android.synthetic.main.sell_type_view_holder.view.*

class FilterPropertyTypeAdapter (val list:List<FilterModel.Data.Option>) : RecyclerView.Adapter<FilterPropertyTypeAdapter.FilterPropertyTypeViewHolder>(){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FilterPropertyTypeViewHolder {
        val view = FilterPropertyTypeViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.property_type_view_holder, parent, false)
        )
        return view
    }

    override fun onBindViewHolder(holder: FilterPropertyTypeViewHolder, position: Int) {
        holder.property_name.text = list[position].name

        if (list[position].flag) {

            holder.property_name.setBackgroundResource(R.drawable.rectangle_border_fill)
            holder.property_name.setTextColor(Color.WHITE)
            SingletonObject.propertyFilling.property_type =  list[position].id
        } else
        {
            holder.property_name.setBackgroundResource(R.drawable.rectangel_border)
            holder.property_name.setTextColor(Color.BLACK)
        }

        holder.property_name.setOnClickListener {
            for (item in list.indices)
            {
                if (item.equals(position)) {
                    list[position].flag = true
                    SingletonObject.propertyFilling.property_type_position = position


                }
                else
                {
                    list[item].flag = false
                }
            }



            notifyDataSetChanged()
        }


    }
    override fun getItemCount(): Int {
        return  list.size

    }

    class FilterPropertyTypeViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val property_name = itemView.rv_mProperty_type
    }
}


