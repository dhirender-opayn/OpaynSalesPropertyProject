package com.example.opaynpropertyproject.adapter

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.opaynpropertyproject.R
import com.example.opaynpropertyproject.api.Keys
import com.example.opaynpropertyproject.api_model.ChatFirebaseModel
import com.example.opaynpropertyproject.comman.SharedPreferenceManager
import com.greetupp.extensions.isNull
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.chat_screen_view_holder.view.*

class ChatScreenAdapter(val chatList: ArrayList<ChatFirebaseModel>, val context: Context) :
    RecyclerView.Adapter<ChatScreenAdapter.ChatScreenViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ChatScreenViewHolder {
        val view = ChatScreenViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.chat_screen_view_holder, parent, false)
        )
        return view
    }

    override fun onBindViewHolder(holder: ChatScreenViewHolder, position: Int) {

//        if (chatList[position].sender_id.equals(Keys.SENDER_ID)) {

            if (chatList[position].type.equals("2")) {
                holder.senderImage.visibility = View.VISIBLE
                Log.e("lastmsg", chatList[position].last_message)
                Picasso.get().load(chatList[position].last_message)
                    .placeholder(R.drawable.down_arrow)
                    .into(holder.senderImage)
                holder.receive_container.visibility = View.GONE
                holder.sender_contaier.visibility = View.VISIBLE
            } else {
                holder.senderImage.visibility = View.GONE
                Log.e("check", "ddddd")
                holder.receive_container.visibility = View.GONE
                holder.sender_person_msg.visibility = View.VISIBLE
                holder.sender_person_msg.setText(chatList[position].last_message)
                holder.sender_contaier.visibility = View.VISIBLE
            }
//        }
//        else if (chatList[position].receiver_id.equals(Keys.RECEIVER_ID)) {
//            if (chatList[position].type.equals("2")) {
//                holder.receiverImage.visibility = View.VISIBLE
//                Log.e("lastmsg", chatList[position].last_message)
//                Picasso.get().load(chatList[position].last_message)
//                    .placeholder(R.drawable.down_arrow)
//                    .into(holder.receiverImage)
//                holder.receive_container.visibility = View.VISIBLE
//                holder.sender_contaier.visibility = View.GONE
//            } else {
//                holder.receiverImage.visibility = View.VISIBLE
//                Log.e("check", "ddddd")
//                holder.sender_contaier.visibility = View.GONE
//                holder.receive_person_img.visibility = View.VISIBLE
//                holder.receive_person_msg.setText(chatList[position].last_message)
//                holder.receive_container.visibility = View.VISIBLE
//            }
//        } else {
//            Log.e("errrrrrrrrrrr", "checkckckckckckckccfkc")
//        }
    }

    override fun getItemCount(): Int {
        return chatList.size
    }

    class ChatScreenViewHolder(item: View) : RecyclerView.ViewHolder(item) {
        val receive_container = item.receive_container
        val receive_person_img = item.receive_person_img
        val receive_person_msg = item.receive_person_msg
        val receive_person_timing = item.receive_person_timing

        val sender_contaier = item.sender_contaier
        val sender_person_img = item.sender_person_img
        val sender_person_msg = item.sender_person_msg
        val sender_person_timing = item.sender_person_timing
        val receiverImage = item.receiverImage
        val senderImage = item.senderImage
    }
}