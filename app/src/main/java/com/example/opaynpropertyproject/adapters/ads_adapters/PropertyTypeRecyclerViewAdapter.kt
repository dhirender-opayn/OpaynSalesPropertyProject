package com.example.opaynpropertyproject.adapters.ads_adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.opaynpropertyproject.R
import kotlinx.android.synthetic.main.property_type_view_holder.view.*

class PropertyTypeRecyclerViewAdapter(val list: ArrayList<String>):RecyclerView.Adapter<PropertyTypeRecyclerViewAdapter.PropertyViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PropertyViewHolder {
        val view = PropertyViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.property_type_view_holder, parent, false)
        )
        return view
    }

    override fun onBindViewHolder(holder: PropertyViewHolder, position: Int) {

    }

    override fun getItemCount(): Int {
        return 8
    }
    class PropertyViewHolder(itemView: View):RecyclerView.ViewHolder(itemView){
        val property_name = itemView.rv_mProperty_type
    }
}