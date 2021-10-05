package com.example.opaynpropertyproject.seller_chat

import android.app.Activity
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.opaynpropertyproject.R
import com.example.opaynpropertyproject.`interface`.GetPositionInterface
import com.example.opaynpropertyproject.adapter.ChatRecyclerAdapter
import com.example.opaynpropertyproject.api.Keys
import com.example.opaynpropertyproject.comman.BaseFragment
import com.example.opaynpropertyproject.comman.SharedPreferenceManager
import com.example.opaynpropertyproject.customer.CustomerHomeActivity
import com.example.opaynpropertyproject.home_activity.HomeActivity
import com.google.firebase.firestore.FirebaseFirestore
import io.grpc.internal.SharedResourceHolder
import kotlinx.android.synthetic.main.fragment_seller_chat.*
import kotlinx.android.synthetic.main.toolbar.*

class SellerChatFragment : BaseFragment(),GetPositionInterface {
    lateinit var activity: Activity



    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_seller_chat, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        ChatHeader()
        rv_seller_chat.adapter = ChatRecyclerAdapter(requireActivity(),this)


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
    }

    override fun getPosition(position: Int) {

    }

}