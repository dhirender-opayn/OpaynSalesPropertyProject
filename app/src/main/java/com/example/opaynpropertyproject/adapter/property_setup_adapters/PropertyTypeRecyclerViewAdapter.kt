package com.example.opaynpropertyproject.adapter.property_setup_adapters

import android.content.Context
import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.opaynpropertyproject.R
import com.example.opaynpropertyproject.singleton.SingletonObject.propertyFilling
import com.example.opaynpropertyproject.api_model.SellPropertyModel
import kotlinx.android.synthetic.main.property_type_view_holder.view.*

class PropertyTypeRecyclerViewAdapter(
    val list: List<SellPropertyModel.Data.Option>
) : RecyclerView.Adapter<PropertyTypeRecyclerViewAdapter.PropertyViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PropertyViewHolder {
        val view = PropertyViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.property_type_view_holder, parent, false)
        )
        return view
    }

    override fun onBindViewHolder(holder: PropertyViewHolder, position: Int) {
        holder.property_name.text = list[position].name

            if (list[position].flag) {

            holder.property_name.setBackgroundResource(R.drawable.rectangle_border_fill)
            holder.property_name.setTextColor(Color.WHITE)
            propertyFilling.property_type =  list[position].id
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
                    propertyFilling.property_type_position = position


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

        return list.size
    }

    class PropertyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val property_name = itemView.rv_mProperty_type
    }
}