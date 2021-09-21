package com.example.opaynpropertyproject.adapter.customerAdapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.opaynpropertyproject.R
import kotlinx.android.synthetic.main.notification_view_holder.view.*
import kotlinx.android.synthetic.main.toolbar.view.*

class NotificationRecyclerAdapter :RecyclerView.Adapter<NotificationRecyclerAdapter.NotificationViewHolder>(){


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NotificationViewHolder {
        val view = NotificationViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.notification_view_holder, parent, false)
        )
        return view
    }

    override fun onBindViewHolder(holder: NotificationViewHolder, position: Int) {

    }

    override fun getItemCount(): Int {
        return 4
    }

    class  NotificationViewHolder(itemView: View):RecyclerView.ViewHolder(itemView){
        val notification_count = itemView.notification_count
        val notification_msg = itemView.notification_msg

    }
}