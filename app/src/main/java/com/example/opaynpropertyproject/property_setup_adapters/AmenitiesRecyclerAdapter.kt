package com.example.opaynpropertyproject.property_setup_adapters

import android.content.Context
import android.graphics.Color
import android.os.Build
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import androidx.recyclerview.widget.RecyclerView
import com.example.opaynpropertyproject.R
import com.example.opaynpropertyproject.singleton.SingletonObject.propertyFilling
import com.example.opaynpropertyproject.api_model.SellPropertyModel
import kotlinx.android.synthetic.main.amenities_view_holder.view.*

class AmenitiesRecyclerAdapter(
    val amenitiesList: List<SellPropertyModel.Data.Option>,
    val context: Context
) :
    RecyclerView.Adapter<AmenitiesRecyclerAdapter.AmenitiesViewHolder>() {
    var selected_amenities_list = ArrayList<Int>()



    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AmenitiesViewHolder {
        val view = AmenitiesViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.amenities_view_holder, parent, false)
        )
        return view
    }

    @RequiresApi(Build.VERSION_CODES.M)
    override fun onBindViewHolder(holder: AmenitiesViewHolder, position: Int) {
        holder.amenities_name.text = amenitiesList[position].name

        holder.amenities_name.setOnClickListener {

            amenitiesList[position].flag = !amenitiesList[position].flag // if(amenities[position].flag) amenities[position].flag = false else amenities[position].true )

            notifyDataSetChanged()
        }
        if (amenitiesList[position].flag) {
            selected_amenities_list.add(amenitiesList[position].id)
            propertyFilling.amenties!!.add(amenitiesList[position].id)
            holder.amenities_name.setBackgroundResource(R.drawable.rectangle_border_fill)
            holder.amenities_name.setTextColor(Color.WHITE)


        } else {
            holder.amenities_name.setBackgroundResource(R.drawable.rectangel_border)
            holder.amenities_name.setTextColor(Color.BLACK)
            propertyFilling.amenties!!.remove(amenitiesList[position].id)


        }
        Log.e("check",amenitiesList[position].toString())
     //   propertyFilling.amenties = selected_amenities_list



    }

    override fun getItemCount(): Int {
        return amenitiesList.size
    }

    class AmenitiesViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val amenities_name = itemView.rv_amenities_type
    }
}