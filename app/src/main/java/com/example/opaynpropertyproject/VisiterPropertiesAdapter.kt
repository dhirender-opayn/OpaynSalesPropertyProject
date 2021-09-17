package com.example.opaynpropertyproject

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.visiter_view_holder.view.*

class VisiterPropertiesAdapter () :RecyclerView.Adapter<VisiterPropertiesAdapter.SelectedPropertiesViewHolder>() {


    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): SelectedPropertiesViewHolder {
        val view = SelectedPropertiesViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.visiter_view_holder, parent, false)
        )
        return view
    }

    override fun onBindViewHolder(holder: SelectedPropertiesViewHolder, position: Int) {

    }

    override fun getItemCount(): Int {
        return 4
    }
    class SelectedPropertiesViewHolder(itemView: View):RecyclerView.ViewHolder(itemView){
        val visiter_img = itemView.visiter_img
        val visiter_name = itemView.visiter_name
        val visiter_comment = itemView.visiter_comments
        val visiter_time = itemView.timing
        val visiter_rating = itemView.vistier_rating
    }
}