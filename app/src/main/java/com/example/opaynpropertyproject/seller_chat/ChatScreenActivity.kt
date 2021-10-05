package com.example.opaynpropertyproject.seller_chat

import android.os.Bundle
import android.util.Log
import android.view.View
import com.example.opaynpropertyproject.R
import com.example.opaynpropertyproject.adapter.ChatScreenAdapter
import com.example.opaynpropertyproject.api.Keys
import com.example.opaynpropertyproject.api_model.ChatFirebaseModel
import com.example.opaynpropertyproject.api_model.CityModel
import com.example.opaynpropertyproject.comman.BaseActivity
import com.example.opaynpropertyproject.comman.Utils
import com.google.firebase.Timestamp
import com.google.firebase.firestore.*
import com.google.firebase.firestore.EventListener
import kotlinx.android.synthetic.main.chat_toolbar.view.*
import kotlinx.android.synthetic.main.fragment_chat_screen.*
import java.util.*
import kotlin.collections.ArrayList
import kotlin.collections.HashMap


class ChatScreenActivity : BaseActivity(), View.OnClickListener {

     var createdAt: Timestamp? = Timestamp.now()
    private var mChatMessageEventListener: ListenerRegistration? = null

    val db = FirebaseFirestore.getInstance()
    var chatList = ArrayList<ChatFirebaseModel>()
    var adapter: ChatScreenAdapter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.fragment_chat_screen)
        supportActionBar?.hide()
        setChatAdapter()
        chatHeader()
        readMessage()
        onClicked()

    }

    private fun chatHeader() {

    }

    private fun onClicked() {
        chat_screen_toolbar.chat_back_btn.setOnClickListener(this)
        send.setOnClickListener(this)
        rv_chat_screen.setOnClickListener(this)
    }

    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.chat_back_btn -> {
                onBackPressed()
            }
            R.id.send -> {
                Keys.SENDER = true
                chatList.addAll(chatList)
                setChatAdapter()
                sendmessage("1", msg.text.toString())
            }
            R.id.rv_chat_screen -> {
                Utils.hideKeyboard(this)
            }
        }
    }


    private fun sendmessage(type: String, msg2: String) {

        msg.setText("")
        val chatsModel=ChatFirebaseModel()


         chatsModel.sender_id = "1"
        chatsModel.receiver_id = "2"
        chatsModel.sender_name = "allex"
        chatsModel.receiver_name = "confusion"
        chatsModel.sender_image = ""
        chatsModel.receiver_image = ""
        chatsModel.type = type
        chatsModel.last_message = msg2


        db.collection("chatRooms").document("12").set(chatsModel)

        val instace = db.collection("chatRooms").document("12").collection("Messages").document()
            .set(chatsModel)

        instace.addOnSuccessListener { documentReference ->


        }

            .addOnFailureListener { e ->
                Log.w("TAGERROR", "Error adding document", e)
            }


//        //db listener
//        db.collection("chatRooms")
//            .addSnapshotListener { snapshots, e ->
//                if (e != null) {
//                    Log.w("TAG", "listen:error", e)
//                    return@addSnapshotListener
//                }
//
//                for (dc in snapshots!!.documentChanges) {
//                    when (dc.type) {
//
//                        DocumentChange.Type.ADDED -> Log.d("TAG", "Message Added: ${dc.document.data}")
//                        DocumentChange.Type.MODIFIED -> Log.d("TAG", "Modified city: ${dc.document.data}")
//                        DocumentChange.Type.REMOVED -> Log.d("TAG", "Removed city: ${dc.document.data}")
//                    }
//                }
//            }

    }

    private fun readMessage() {
        val messagesRef = db.collection("chatRooms")
            .document("12")
            .collection("Messages")

        mChatMessageEventListener = messagesRef.orderBy("created_at").addSnapshotListener(
            EventListener { queryDocumentSnapshots, e ->
            if (e != null) {

                return@EventListener
            }
            if (queryDocumentSnapshots != null) {

                for (doc in queryDocumentSnapshots.documentChanges)
                {


                    when (doc.type)
                    {
                        DocumentChange.Type.ADDED ->
                        {
                            Log.e("hehehehehe","ojdaodjapdad")
                            var  model=ChatFirebaseModel()
                            model.sender_id = doc.document.data.get("sender_id").toString()
                            model.receiver_id = doc.document.data.get("receiver_id").toString()
                            model.sender_name =doc.document.data.get("sender_name").toString()
                            model.receiver_name = doc.document.data.get("receiver_name").toString()
                            model.sender_image = ""
                            model.receiver_image = ""
                            model.type =doc.document.data.get("type").toString()
                            model.last_message = doc.document.data.get("last_message").toString()
                            chatList.add(model)
                            rv_chat_screen.smoothScrollToPosition(chatList.size - 1)

                            adapter?.notifyDataSetChanged()

                        }
                        DocumentChange.Type.MODIFIED ->
                        {
                            Log.e("heheheh","123")
                        }
                        DocumentChange.Type.REMOVED ->
                        {
                            Log.e("heheheh","1233")
                        }
                    }




                }}
        })




        //read data
//        db.collection("chatRooms").document("12").collection("Messages").orderBy("created_at").get()
//            .addOnSuccessListener { result ->
//                for (doc in result.documentChanges)
//                {
//                    when(doc.type)
//                    {
//                        DocumentChange.Type.ADDED->{
//                             Log.e("chatScreenResultget", doc.document.data.get("sender_id").toString())
//
//                        }
//                    }
//
//
//                }
//                adapter?.notifyDataSetChanged()
//            }
//            .addOnFailureListener { expection ->
//                Log.e("READERROR", expection.toString())
//            }


    }

    private fun setChatAdapter() {
        adapter = ChatScreenAdapter(chatList, this)
        rv_chat_screen.adapter = adapter
    }


}