package com.example.opaynpropertyproject.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.opaynpropertyproject.R

class HomeRecyclerAdapter :RecyclerView.Adapter<HomeRecyclerAdapter.HomeViewHolder>(){



    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HomeViewHolder {
        val view = HomeViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.home_outer_view_holder, parent, false)
        )
        return view
    }

    override fun onBindViewHolder(holder: HomeViewHolder, position: Int) {

    }

    override fun getItemCount(): Int {
         return 4
    }
    class HomeViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){

    }
}