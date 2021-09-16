package com.example.opaynpropertyproject

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.faq_list_view_holder.view.*

class FaqRecyclerViewAdapter :
    RecyclerView.Adapter<FaqRecyclerViewAdapter.FaqViewHolder>() {


    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): FaqViewHolder {
        return FaqViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.faq_list_view_holder, parent, false)
        )
    }
    override fun onBindViewHolder(holder: FaqViewHolder, position: Int) {

    }

    override fun getItemCount(): Int {
          return  4
    }


    class FaqViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val faq_title =itemView.faq_head
        val faq_des = itemView.faq_des
    }




}