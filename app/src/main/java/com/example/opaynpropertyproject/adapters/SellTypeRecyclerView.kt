package com.example.opaynpropertyproject.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.opaynpropertyproject.R
import kotlinx.android.synthetic.main.sell_type_view_holder.view.*

class SellTypeRecyclerView(sellList: List<String>,val context: Context) :RecyclerView.Adapter<SellTypeRecyclerView.SellTypeViewHolder>(){


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SellTypeViewHolder {
      return SellTypeViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.sell_type_view_holder,parent,false))

    }

    override fun onBindViewHolder(holder: SellTypeViewHolder, position: Int) {

    }

    override fun getItemCount(): Int {
       return 4
    }
    class SellTypeViewHolder(itemview: View): RecyclerView.ViewHolder(itemview){
        val sell_type_name = itemview.rv_mSell_type

    }
}