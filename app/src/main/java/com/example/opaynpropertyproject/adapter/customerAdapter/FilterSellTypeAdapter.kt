package com.example.opaynpropertyproject.adapter.customerAdapter

import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.opaynpropertyproject.R
import com.example.opaynpropertyproject.api_model.FilterModel
import com.example.opaynpropertyproject.singleton.SingletonObject
import kotlinx.android.synthetic.main.sell_type_view_holder.view.*

class FilterSellTypeAdapter(val list:List<FilterModel.Data.Option>) : RecyclerView.Adapter<FilterSellTypeAdapter.FilterSellViewHolder>(){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FilterSellViewHolder {
        val view = FilterSellViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.sell_type_view_holder, parent, false)
        )
        return view
    }

    override fun onBindViewHolder(holder: FilterSellViewHolder, position: Int) {
        holder.filter_sell_name.setText(list[position].name)
        if (list[position].flag) {
            holder.filter_sell_name.setBackgroundResource(R.drawable.rectangle_border_fill)
            holder.filter_sell_name.setTextColor(Color.WHITE)
            SingletonObject.propertyFilling.sell_type = list[position].id.toString()
            SingletonObject.propertyFilling.sell_type_position = position

        } else {
            holder.filter_sell_name.setBackgroundResource(R.drawable.rectangel_border)
            holder.filter_sell_name.setTextColor(Color.BLACK)
        }
        holder.filter_sell_name.setOnClickListener {
            for (item in list.indices) {
                if (item.equals(position)) {
                    list[position].flag = true
                    SingletonObject.propertyFilling.sell_postion = position

                } else {
                    list[item].flag = false
                }

            }
            notifyDataSetChanged()
        }

    }
    override fun getItemCount(): Int {
      return  list.size

    }

    class FilterSellViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val filter_sell_name = itemView.rv_mSell_type
    }
}


