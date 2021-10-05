package com.example.opaynpropertyproject.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.opaynpropertyproject.R
import com.example.opaynpropertyproject.api.Keys
import com.example.opaynpropertyproject.api_model.ChatFirebaseModel
import com.example.opaynpropertyproject.comman.SharedPreferenceManager
import com.greetupp.extensions.isNull
import kotlinx.android.synthetic.main.chat_screen_view_holder.view.*

class ChatScreenAdapter(val chatList:ArrayList<ChatFirebaseModel> , val context: Context ):RecyclerView.Adapter<ChatScreenAdapter.ChatScreenViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ChatScreenViewHolder {
        val view = ChatScreenViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.chat_screen_view_holder, parent, false)
        )
        return view
    }

    override fun onBindViewHolder(holder: ChatScreenViewHolder, position: Int) {
        holder.receive_container.visibility = View.GONE
        holder.sender_person_msg.setText(chatList[position].last_message)
        holder.sender_contaier.visibility = View.VISIBLE

//
//        if (Keys.SENDER){
//
//        }

    }

    override fun getItemCount(): Int {
     return  chatList.size
    }
    class ChatScreenViewHolder(item: View):RecyclerView.ViewHolder(item){
        val receive_container = item.receive_container
        val receive_person_img = item.receive_person_img
        val receive_person_msg = item.receive_person_msg
        val receive_person_timing = item.receive_person_timing

        val sender_contaier = item.sender_contaier
        val sender_person_img = item.sender_person_img
        val sender_person_msg = item.sender_person_msg
        val sender_person_timing = item.sender_person_timing


    }
}