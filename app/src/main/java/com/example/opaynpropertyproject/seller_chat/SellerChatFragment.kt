package com.example.opaynpropertyproject.seller_chat

import android.app.Activity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.opaynpropertyproject.R
import com.example.opaynpropertyproject.api.Keys
import com.example.opaynpropertyproject.comman.BaseFragment
import com.example.opaynpropertyproject.customer.CustomerHomeActivity
import com.example.opaynpropertyproject.home_activity.HomeActivity
import kotlinx.android.synthetic.main.toolbar.*

class SellerChatFragment : BaseFragment() {
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
    }

    private fun ChatHeader() {
        if (Keys.isCustomer) {
            activity = requireContext() as CustomerHomeActivity
        } else {
            activity = requireContext() as HomeActivity
        }

        activity.ads.visibility = View.VISIBLE
        activity.ads.setText(getString(R.string.chat))
        activity.menu_bar.visibility = View.VISIBLE
        activity.search_bar_container.visibility = View.INVISIBLE
    }

}