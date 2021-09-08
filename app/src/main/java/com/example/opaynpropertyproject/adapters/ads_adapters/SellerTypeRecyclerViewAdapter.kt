package com.example.opaynpropertyproject.adapters.ads_adapters

import android.content.res.ColorStateList
import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.opaynpropertyproject.R
import com.example.opaynpropertyproject.adapters.singleton.SingletonObject.propertyFilling
import com.example.opaynpropertyproject.api_model.SellPropertyModel
  import kotlinx.android.synthetic.main.sell_type_view_holder.view.*

class SellerTypeRecyclerViewAdapter(
    val list:
    List<SellPropertyModel.Data.Option>
) :
    RecyclerView.Adapter<SellerTypeRecyclerViewAdapter.SellViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SellViewHolder {
        val view = SellViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.sell_type_view_holder, parent, false)
        )
        return view

    }

    override fun onBindViewHolder(holder: SellViewHolder, position: Int) {
        holder.sell_name.text = list[position].name
        if (list[position].flag) {
            holder.sell_name.setBackgroundResource(R.drawable.rectangle_border_fill)
            holder.sell_name.setTextColor(Color.WHITE)
            propertyFilling.sell_type = list[position].id.toString()

        } else {
            holder.sell_name.setBackgroundResource(R.drawable.rectangel_border)
            holder.sell_name.setTextColor(Color.BLACK)
        }
        holder.sell_name.setOnClickListener {
            for (item in list.indices)
            {
                if (item.equals(position))
                {
                    list[position].flag = true
                    propertyFilling.sell_postion = position

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

    class SellViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val sell_name = itemView.rv_mSell_type

    }
}