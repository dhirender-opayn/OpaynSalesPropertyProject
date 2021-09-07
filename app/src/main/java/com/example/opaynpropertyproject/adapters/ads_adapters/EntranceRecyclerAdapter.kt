package com.example.opaynpropertyproject.adapters.ads_adapters

import android.content.Context
import android.content.res.ColorStateList
import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.opaynpropertyproject.R
import com.example.opaynpropertyproject.adapters.singleton.SingletonObject
import com.example.opaynpropertyproject.api_model.SellPropertyModel
import kotlinx.android.synthetic.main.entrance_view_holder.view.*

class EntranceRecyclerAdapter(val entranceList:List<SellPropertyModel.Data.Option>, val context:Context)
    :RecyclerView.Adapter<EntranceRecyclerAdapter.EntranceViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EntranceViewHolder {
        val view = EntranceViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.entrance_view_holder, parent, false)
        )
        return view
    }

    override fun onBindViewHolder(holder: EntranceViewHolder, position: Int) {
            holder.entrance_name.text = entranceList[position].name
        if (entranceList[position].flag){
            holder.entrance_name.setBackgroundResource(R.drawable.rectangle_border_fill)
            holder.entrance_name.setTextColor(Color.WHITE)
            SingletonObject.propertyFilling.entrance = entranceList[position].id.toString()
        } else {
            holder.entrance_name.setBackgroundResource(R.drawable.rectangel_border)
            holder.entrance_name.setTextColor(Color.BLACK)
        }
        holder.entrance_name.setOnClickListener {
           for (item in entranceList.indices){
               if (item.equals(position)){
                   entranceList[position].flag = true
               } else {
                   entranceList[item].flag = false
               }
           }
            notifyDataSetChanged()
        }
    }

    override fun getItemCount(): Int {
        return  entranceList.size

    }
    class EntranceViewHolder(itemView: View):RecyclerView.ViewHolder(itemView){
        val entrance_name = itemView.rv_entrance_type

    }

}