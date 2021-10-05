package com.example.opaynpropertyproject.adapter

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.opaynpropertyproject.R
import com.example.opaynpropertyproject.`interface`.GetPositionInterface
import com.example.opaynpropertyproject.seller_chat.ChatScreenActivity
import kotlinx.android.synthetic.main.seller_chat_view_holder.view.*

class ChatRecyclerAdapter(val context :Context, val getPositionInterface: GetPositionInterface ) : RecyclerView.Adapter<ChatRecyclerAdapter.ChatViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ChatViewHolder {
        val view =ChatViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.seller_chat_view_holder, parent, false)
        )
        return view
    }

    override fun onBindViewHolder(holder: ChatViewHolder, position: Int) {
        holder.chat_container.setOnClickListener{
            getPositionInterface.getPosition(holder.adapterPosition)


            //tempary
            val intent = Intent(context,ChatScreenActivity::class.java )
            context.startActivity(intent)
        }

    }

    override fun getItemCount(): Int {
        return 3
    }

    class ChatViewHolder(item: View) : RecyclerView.ViewHolder(item) {
        val visiter_name = item.visiter_name
        val visiter_comments = item.visiter_comments
        val timing = item.timing
        val chat_count = item.chat_count
        val chat_container = item.chat_container


    }
}