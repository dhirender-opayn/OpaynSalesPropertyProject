package com.example.opaynpropertyproject.seller_chat

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.opaynpropertyproject.R
import com.example.opaynpropertyproject.comman.BaseFragment

class SellerChatFragment : BaseFragment() {


        override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
        ): View? {
            // Inflate the layout for this fragment
            return inflater.inflate(R.layout.fragment_seller_chat, container, false)
        }


}