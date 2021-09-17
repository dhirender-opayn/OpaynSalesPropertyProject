package com.example.opaynpropertyproject.adapter.property_setup_adapters

import android.content.Context
import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.opaynpropertyproject.R
import com.example.opaynpropertyproject.singleton.SingletonObject
import com.example.opaynpropertyproject.api_model.SellPropertyModel
import kotlinx.android.synthetic.main.furnishing_view_holder.view.*

class FurnishRecyclerAdapter(val furnishList: List<SellPropertyModel.Data.Option>, val context: Context) :
    RecyclerView.Adapter<FurnishRecyclerAdapter.FurnishViewHolder>() {



    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FurnishViewHolder {
        val view = FurnishViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.furnishing_view_holder, parent, false)
        )
        return view
    }

    override fun onBindViewHolder(holder: FurnishViewHolder, position: Int) {
        holder.furnish_name.text = furnishList[position].name
        if (furnishList[position].flag){
            holder.furnish_name.setBackgroundResource(R.drawable.rectangle_border_fill)
            holder.furnish_name.setTextColor(Color.WHITE)
            SingletonObject.propertyFilling.furnish = furnishList[position].id.toString()
        } else {
            holder.furnish_name.setBackgroundResource(R.drawable.rectangel_border)
            holder.furnish_name.setTextColor(Color.BLACK)
        }
        holder.furnish_name.setOnClickListener {
            for (item in furnishList.indices){
                if (item.equals(position)){
                    furnishList[position].flag = true
                } else {
                    furnishList[item].flag = false
                }
            }
            notifyDataSetChanged()
        }
    }

    override fun getItemCount(): Int {
       return furnishList.size
    }
    class FurnishViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val furnish_name = itemView.rv_furnishing_type

    }
}