package com.example.opaynpropertyproject.adapter.property_setup_adapters

import android.app.Activity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.opaynpropertyproject.R
import com.example.opaynpropertyproject.`interface`.GetPositionInterface
import com.example.opaynpropertyproject.adapter.HomeRecommendInnerAdapter
import com.example.opaynpropertyproject.adapter.HomeRecommendRecyclerAdapter
import com.example.opaynpropertyproject.api_model.CustomerHomeModel
import kotlinx.android.synthetic.main.home_outer_view_holder.view.*

class CustomerHomeOuterAdapter(val customerHomeProperty:ArrayList<CustomerHomeModel.Data>, val activity: Activity, val getfavPosition: GetPositionInterface) : RecyclerView.Adapter<CustomerHomeOuterAdapter.CustomerHomeOuterViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CustomerHomeOuterViewHolder {
        val view =  CustomerHomeOuterViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.home_outer_view_holder, parent, false)


        )
        return view
    }

    override fun onBindViewHolder(holder: CustomerHomeOuterViewHolder, position: Int) {
        holder.innner_adapter.adapter = CustomerHomeInnerAdapter(customerHomeProperty,activity,getfavPosition)
    }

    override fun getItemCount(): Int {
       return customerHomeProperty.size
    }
    class CustomerHomeOuterViewHolder(itemView : View) : RecyclerView.ViewHolder(itemView){
        val recommend_header = itemView.recommended_property_head
        val recommend_view_all_ = itemView.recommended_view_all
        val innner_adapter= itemView.rv_recommended_property_inner
    }
}