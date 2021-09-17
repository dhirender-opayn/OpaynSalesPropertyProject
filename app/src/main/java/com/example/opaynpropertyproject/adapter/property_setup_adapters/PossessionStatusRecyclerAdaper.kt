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
import kotlinx.android.synthetic.main.possession_view_holder.view.*

class PossessionStatusRecyclerAdaper(val possessionList: List<SellPropertyModel.Data.Option>, val context: Context) :
    RecyclerView.Adapter<PossessionStatusRecyclerAdaper.PossessiionViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PossessiionViewHolder {
        val view = PossessiionViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.possession_view_holder, parent, false)
        )
        return view
    }

    override fun onBindViewHolder(holder: PossessiionViewHolder, position: Int) {
        holder.possession_name.text = possessionList[position].name
        if (possessionList[position].flag){
            holder.possession_name.setBackgroundResource(R.drawable.rectangle_border_fill)
            holder.possession_name.setTextColor(Color.WHITE)
            propertyFilling.poessionType = possessionList[position].id.toString()
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
       return  possessionList.size
    }


    class PossessiionViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val possession_name = itemView.rv_mPossession_type

    }

}