package com.example.opaynpropertyproject.adapter.home_adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.opaynpropertyproject.R
import com.example.opaynpropertyproject.api_model.FilterModel
import kotlinx.android.synthetic.main.home_widget_view_holder.view.*

class WidgetHomeAdapter(val widegetlist: ArrayList<FilterModel.Data>, context: Context) :
    RecyclerView.Adapter<WidgetHomeAdapter.HomeWidgetViewHolder>() {

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): HomeWidgetViewHolder {
        val view = HomeWidgetViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.home_widget_view_holder, parent, false)
        )
        return view
    }

    override fun onBindViewHolder(holder: HomeWidgetViewHolder, position: Int) {
        holder.widget_title.setText(widegetlist[position].name)

    }

    override fun getItemCount(): Int {
        return widegetlist.size
    }

    class HomeWidgetViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val widget_title = itemView.widget_title

    }

}