package com.example.opaynpropertyproject.adapter

import android.app.Activity
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.opaynpropertyproject.R
import kotlinx.android.synthetic.main.home_outer_view_holder.view.*
import kotlinx.android.synthetic.main.seller_home_list_holder.view.*

class HomeRecommendRecyclerAdapter(val activity: Activity) :RecyclerView.Adapter<HomeRecommendRecyclerAdapter.HomeViewHolder>(){



    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HomeViewHolder {
        val view = HomeViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.home_outer_view_holder, parent, false)
        )
        return view
    }

    override fun onBindViewHolder(holder: HomeViewHolder, position: Int) {
        holder.innner_adapter.adapter = HomeRecommendInnerAdapter(activity)

    }

    override fun getItemCount(): Int {
         return 4
    }
    class HomeViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
        val recommend_header = itemView.recommended_property_head
        val recommend_view_all_ = itemView.recommended_view_all
        val innner_adapter= itemView.rv_recommended_property_inner





    }
}