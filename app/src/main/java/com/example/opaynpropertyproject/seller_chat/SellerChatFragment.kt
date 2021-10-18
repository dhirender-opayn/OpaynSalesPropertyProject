package com.example.opaynpropertyproject.seller_chat

import android.app.Activity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.opaynpropertyproject.R
import com.example.opaynpropertyproject.`interface`.GetPositionInterface
import com.example.opaynpropertyproject.adapter.ChatRecyclerAdapter
import com.example.opaynpropertyproject.adapter.ChatScreenAdapter
import com.example.opaynpropertyproject.api.Keys
import com.example.opaynpropertyproject.api_model.ChatFirebaseModel
import com.example.opaynpropertyproject.comman.BaseFragment
import com.example.opaynpropertyproject.comman.SharedPreferenceManager
import com.example.opaynpropertyproject.customer.CustomerHomeActivity
import com.example.opaynpropertyproject.home_activity.HomeActivity
import com.google.firebase.firestore.*
import com.google.firebase.ktx.Firebase
import com.google.firebase.storage.ktx.storage
import io.grpc.internal.SharedResourceHolder
import kotlinx.android.synthetic.main.activity_home.*
import kotlinx.android.synthetic.main.fragment_chat_screen.*
import kotlinx.android.synthetic.main.fragment_seller_chat.*
import kotlinx.android.synthetic.main.search_toolbar.*
import kotlinx.android.synthetic.main.search_toolbar.view.*
import kotlinx.android.synthetic.main.toolbar.*
import okhttp3.MultipartBody
import java.lang.Exception

class SellerChatFragment : BaseFragment(), GetPositionInterface, View.OnClickListener {
    lateinit var activity: Activity
    val sellerchatmodel = ChatFirebaseModel()
    val frontchatlist = ArrayList<ChatFirebaseModel>()
    var templist = ArrayList<ChatFirebaseModel>()
    val fields = ArrayList<MultipartBody.Part>()
    val db = FirebaseFirestore.getInstance()
    private var adapter: ChatRecyclerAdapter? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_seller_chat, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        searchListner()
        ChatHeader()
        setChatAdapterChat(frontchatlist)
    }

    override fun onResume() {
        super.onResume()

        readLastMessage()

    }

    private fun ChatHeader() {
        if (Keys.isCustomer) {
            activity = requireActivity() as CustomerHomeActivity
        } else {
            activity = requireActivity() as HomeActivity
        }
        activity.ads.visibility = View.VISIBLE
        activity.ads.setText(getString(R.string.chat))
        activity.menu_bar.visibility = View.VISIBLE
        activity.search_bar_container.visibility = View.INVISIBLE
        activity.header_filer.visibility = View.INVISIBLE
    }

    private fun readLastMessage() {
        frontchatlist.clear()
        val mMessagesRefchat = db.collection("chatRooms").document("12")
        mMessagesRefchat.get().addOnSuccessListener { documentsnapshot ->
            if (documentsnapshot != null) {
                //if (documentsnapshot.getString("receiver_id").toString().equals() || documentsnapshot.getString("sender_id").toString().equals() ) //condition when
                sellerchatmodel.last_message = documentsnapshot.getString("last_message").toString()
                sellerchatmodel.type = documentsnapshot.getString("type").toString()
                sellerchatmodel.sender_image = documentsnapshot.getString("sender_image").toString()
                sellerchatmodel.sender_id = documentsnapshot.getString("sender_id").toString()
                sellerchatmodel.receiver_id = documentsnapshot.getString("receiver_id").toString()
                sellerchatmodel.sender_name = documentsnapshot.getString("sender_name").toString()
                Log.e("ddeeeeee", sellerchatmodel.sender_name)
                frontchatlist.add(sellerchatmodel)
                try {
                    setChatAdapterChat(frontchatlist)
                } catch (e: Exception) {
                }
            }
        }
    }

    override fun getPosition(position: Int) {

    }

    private fun setChatAdapterChat(list: ArrayList<ChatFirebaseModel>) {
        if (rv_seller_chat != null) {

            adapter = ChatRecyclerAdapter(list, requireActivity(), this)
            rv_seller_chat.adapter = adapter

        }

    }

    override fun onClick(v: View?) {
        when (v?.id) {

        }
    }


    private fun searchListner() {
//    val activity = requireActivity() as HomeActivity
        chat_search_bar?.search_toolbar?.addTextChangedListener(object : TextWatcher {

            override fun afterTextChanged(s: Editable) {
//                Log.e("Check",s.toString() +"check")
            }

            override fun beforeTextChanged(
                s: CharSequence, start: Int,
                count: Int, after: Int
            ) {
//                Log.e("Check",s.toString()+"text")
            }

            override fun onTextChanged(
                s: CharSequence, start: Int,
                before: Int, count: Int
            ) {
                templist.clear()
                if (!chat_search_bar?.search_toolbar?.text.toString().isEmpty()) {
                    templist = frontchatlist.filter {
                        it.sender_name.lowercase()
                            .contains(s.toString()) || it.receiver_name.lowercase()
                            .contains(s.toString())
                    } as ArrayList<ChatFirebaseModel>
                    setChatAdapterChat(templist)
                    Log.e("reslultsssss", templist.size.toString())
                } else {
                    setChatAdapterChat(frontchatlist)
                }
            }
        })
    }
}