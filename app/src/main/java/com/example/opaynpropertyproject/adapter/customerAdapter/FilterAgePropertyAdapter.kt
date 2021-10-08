package com.example.opaynpropertyproject.adapter.customerAdapter

import android.content.Context
import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.opaynpropertyproject.R
import com.example.opaynpropertyproject.api_model.FilterModel
import com.example.opaynpropertyproject.singleton.SingletonObject
import kotlinx.android.synthetic.main.age_of_property_view_holder.view.*

class FilterAgePropertyAdapter(val age_of_property_list: List<FilterModel.Data.Option>, val context: Context) :RecyclerView.Adapter<FilterAgePropertyAdapter.FilterAgePropertyViewHolder>(){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FilterAgePropertyViewHolder {
        val view =FilterAgePropertyViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.age_of_property_view_holder, parent, false)
        )
        return view
     }

    override fun onBindViewHolder(holder: FilterAgePropertyViewHolder, position: Int) {
        holder.age_of_property_name.text = age_of_property_list[position].name
        if (age_of_property_list[position].flag){
            holder.age_of_property_name.setBackgroundResource(R.drawable.rectangle_border_fill)
            holder.age_of_property_name.setTextColor(Color.WHITE)
            SingletonObject.propertyFilling.age_of_property = age_of_property_list[position].id.toString()
        } else {
            holder.age_of_property_name.setBackgroundResource(R.drawable.rectangel_border)
            holder.age_of_property_name.setTextColor(Color.BLACK)
        }
        holder.age_of_property_name.setOnClickListener {
            for (item in age_of_property_list.indices){
                if (item.equals(position)){
                    age_of_property_list[position].flag = true
                } else {
                    age_of_property_list[item].flag = false
                }
            }
            notifyDataSetChanged()
        }
     }

    override fun getItemCount(): Int {
        return age_of_property_list.size
     }

    class FilterAgePropertyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val age_of_property_name = itemView.rv_age_of_property_type
    }

}