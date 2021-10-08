package com.example.opaynpropertyproject.adapter.customerAdapter

import android.content.Context
import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.opaynpropertyproject.R
import com.example.opaynpropertyproject.adapter.property_setup_adapters.PostedByRecyclerAdapter
import com.example.opaynpropertyproject.api_model.FilterModel
import com.example.opaynpropertyproject.api_model.SellPropertyModel
import com.example.opaynpropertyproject.singleton.SingletonObject
import kotlinx.android.synthetic.main.posted_by_view_holder.view.*

class FilterPostedByAdapter (val postedbyList: List<FilterModel.Data.Option>, val context: Context):RecyclerView.Adapter<FilterPostedByAdapter.FilterPostedByViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FilterPostedByViewHolder {
        val view = FilterPostedByViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.posted_by_view_holder, parent, false)
        )
        return view
    }

    override fun onBindViewHolder(holder: FilterPostedByViewHolder, position: Int) {
        holder.postby_name.text = postedbyList[position].name
        if (postedbyList[position].flag){
            holder.postby_name.setBackgroundResource(R.drawable.rectangle_border_fill)
            holder.postby_name.setTextColor(Color.WHITE)
            SingletonObject.propertyFilling.postedby = postedbyList[position].id.toString()
        } else {
            holder.postby_name.setBackgroundResource(R.drawable.rectangel_border)
            holder.postby_name.setTextColor(Color.BLACK)
        }
        holder.postby_name.setOnClickListener {
            for (item in postedbyList.indices){
                if (item.equals(position)){
                    postedbyList[position].flag = true
                } else {
                    postedbyList[item].flag = false
                }
            }
            notifyDataSetChanged()
        }

    }
    override fun getItemCount(): Int {
       return postedbyList.size
    }

    class FilterPostedByViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        val postby_name = itemView.rv_posted_by_type
    }

}