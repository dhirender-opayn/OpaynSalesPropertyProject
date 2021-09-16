package com.example.opaynpropertyproject.property_setup_adapters

import android.content.Context
import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.opaynpropertyproject.R
import com.example.opaynpropertyproject.singleton.SingletonObject
import com.example.opaynpropertyproject.api_model.SellPropertyModel
import kotlinx.android.synthetic.main.age_of_property_view_holder.view.*

class AgeOfPropertyAdapter(val age_of_property_list: List<SellPropertyModel.Data.Option>, val context: Context) :
    RecyclerView.Adapter<AgeOfPropertyAdapter.AgeOfPropertyViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AgeOfPropertyViewHolder {
        val view = AgeOfPropertyViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.age_of_property_view_holder, parent, false)
        )
        return view
    }

    override fun onBindViewHolder(holder: AgeOfPropertyViewHolder, position: Int) {
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

    class AgeOfPropertyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val age_of_property_name = itemView.rv_age_of_property_type
    }

}