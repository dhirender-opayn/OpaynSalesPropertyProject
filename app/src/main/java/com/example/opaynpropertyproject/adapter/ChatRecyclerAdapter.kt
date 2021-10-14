package com.example.opaynpropertyproject.adapter

import android.content.Context
import android.content.Intent
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.opaynpropertyproject.R
import com.example.opaynpropertyproject.`interface`.GetPositionInterface
import com.example.opaynpropertyproject.api_model.ChatFirebaseModel
import com.example.opaynpropertyproject.seller_chat.ChatScreenActivity
import com.greetupp.extensions.isNull
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.seller_chat_view_holder.view.*

class ChatRecyclerAdapter(
    val chatlist: ArrayList<ChatFirebaseModel>,
    val context: Context,
    val getPositionInterface: GetPositionInterface
) : RecyclerView.Adapter<ChatRecyclerAdapter.ChatViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ChatViewHolder {
        val view = ChatViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.seller_chat_view_holder, parent, false)
        )
        return view
    }

    override fun onBindViewHolder(holder: ChatViewHolder, position: Int) {

        if (!chatlist.isEmpty()) {
            holder.visiter_name.setText(chatlist[position].sender_name)
            holder.timing.setText(chatlist[position].created_at.toString())
            holder.chat_count.visibility = View.INVISIBLE
            if (!chatlist[position].sender_image.isEmpty()){
                Picasso.get().load(chatlist[position].sender_image).placeholder(R.drawable.chat_person).into(holder.visiter_img)
            }

            if (chatlist[position].type.equals("1")) {
                Log.e("lastmsg", chatlist[position].last_message)
                holder.visiter_comments.setText(chatlist[position].last_message)

                holder.chat_container.setOnClickListener {
                    getPositionInterface.getPosition(holder.adapterPosition)
                    //tempary
                    val intent = Intent(context, ChatScreenActivity::class.java)
                    context.startActivity(intent)
                }
            } else {

//            holder.visiter_comment_img.visibility = View.VISIBLE
//            Picasso.get().load(chatlist[position].last_message)
//                .placeholder(R.drawable.down_arrow).into(holder.visiter_comment_img)
                holder.visiter_comments.setText("Image")

                holder.chat_container.setOnClickListener {
                    getPositionInterface.getPosition(holder.adapterPosition)
                    //tempary
                    val intent = Intent(context, ChatScreenActivity::class.java)
                    context.startActivity(intent)
                }

            }
        }
    }

    override fun getItemCount(): Int {
        return chatlist.size
    }

    class ChatViewHolder(item: View) : RecyclerView.ViewHolder(item) {
        val visiter_name = item.visiter_name
        val visiter_comments = item.visiter_comments
        val timing = item.timing
        val chat_count = item.chat_count
        val chat_container = item.chat_container
        val visiter_img = item.visiter_img



    }
}