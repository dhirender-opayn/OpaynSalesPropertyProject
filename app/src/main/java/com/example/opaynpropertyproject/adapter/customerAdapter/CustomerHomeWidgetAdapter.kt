package com.example.opaynpropertyproject.adapter.customerAdapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.opaynpropertyproject.R

class CustomerHomeWidgetAdapter :RecyclerView.Adapter<CustomerHomeWidgetAdapter.CustomerHomeWidgetViewHolder>(){

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): CustomerHomeWidgetViewHolder {
        val view =CustomerHomeWidgetViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.customer_home_widget_view_holder, parent, false)
        )
        return view
    }

    override fun onBindViewHolder(holder: CustomerHomeWidgetViewHolder, position: Int) {

    }

    override fun getItemCount(): Int {
         return 4
    }
    class CustomerHomeWidgetViewHolder(itemView:View):RecyclerView.ViewHolder(itemView){

    }

}