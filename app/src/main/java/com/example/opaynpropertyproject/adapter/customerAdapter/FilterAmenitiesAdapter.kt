package com.example.opaynpropertyproject.adapter.customerAdapter

import android.content.Context
import android.graphics.Color
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.opaynpropertyproject.R
 import com.example.opaynpropertyproject.api_model.FilterModel
import com.example.opaynpropertyproject.singleton.SingletonObject
import kotlinx.android.synthetic.main.amenities_view_holder.view.*

class FilterAmenitiesAdapter(val amenitiesList:  List<FilterModel.Data.Option>, val context: Context) :RecyclerView.Adapter<FilterAmenitiesAdapter.FilterAmenitiesViewHolder>() {

    var selected_filter_amenities_list = ArrayList<Int>()


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FilterAmenitiesViewHolder {
        val view =  FilterAmenitiesViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.amenities_view_holder, parent, false)
        )
        return view
     }

    override fun onBindViewHolder(holder: FilterAmenitiesViewHolder, position: Int) {
        holder.amenities_name.text = amenitiesList[position].name

        holder.amenities_name.setOnClickListener {

            amenitiesList[position].flag = !amenitiesList[position].flag // if(amenities[position].flag) amenities[position].flag = false else amenities[position].true )

            notifyDataSetChanged()
        }
        if (amenitiesList[position].flag) {
            selected_filter_amenities_list.add(amenitiesList[position].id)
            SingletonObject.propertyFilling.amenties!!.add(amenitiesList[position].id)
            holder.amenities_name.setBackgroundResource(R.drawable.rectangle_border_fill)
            holder.amenities_name.setTextColor(Color.WHITE)


        } else {
            holder.amenities_name.setBackgroundResource(R.drawable.rectangel_border)
            holder.amenities_name.setTextColor(Color.BLACK)
            SingletonObject.propertyFilling.amenties!!.remove(amenitiesList[position].id)


        }
        Log.e("check",amenitiesList[position].toString())
        //   propertyFilling.amenties = selected_amenities_list

    }

    override fun getItemCount(): Int {
        return amenitiesList.size
     }

    class FilterAmenitiesViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        val amenities_name = itemView.rv_amenities_type
    }


}