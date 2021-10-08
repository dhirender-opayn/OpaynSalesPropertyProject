package com.example.opaynpropertyproject.adapter.customerAdapter

import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.opaynpropertyproject.R
import com.example.opaynpropertyproject.api_model.FilterModel
import com.example.opaynpropertyproject.singleton.SingletonObject
import kotlinx.android.synthetic.main.possession_view_holder.view.*

class FilterPossessionAdapter (val possessionList:List<FilterModel.Data.Option>):RecyclerView.Adapter<FilterPossessionAdapter.FilterPossessionViewHolder>(){




    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FilterPossessionViewHolder {
        val view = FilterPossessionViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.possession_view_holder, parent, false)
        )
        return view
    }

    override fun onBindViewHolder(holder: FilterPossessionViewHolder, position: Int) {
        holder.possession_name.text = possessionList[position].name
        if (possessionList[position].flag){
            holder.possession_name.setBackgroundResource(R.drawable.rectangle_border_fill)
            holder.possession_name.setTextColor(Color.WHITE)
            SingletonObject.propertyFilling.poessionType = possessionList[position].id.toString()
        } else {
            holder.possession_name.setBackgroundResource(R.drawable.rectangel_border)
            holder.possession_name.setTextColor(Color.BLACK)
        }
        holder.possession_name.setOnClickListener {
            for (item in possessionList.indices){
                if (item.equals(position)){
                    possessionList[position].flag = true
                } else {
                    possessionList[item].flag = false
                }
            }
            notifyDataSetChanged()
        }

    }
    override fun getItemCount(): Int {
       return possessionList.size
    }
    class FilterPossessionViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        val possession_name = itemView.rv_mPossession_type
    }

}
