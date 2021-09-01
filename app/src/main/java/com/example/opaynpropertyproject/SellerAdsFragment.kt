package com.example.opaynpropertyproject

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.opaynpropertyproject.adapters.YourAdsRecyclerViewAdapter
import kotlinx.android.synthetic.main.fragment_seller_ads.*


class SellerAdsFragment : Fragment() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_seller_ads, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val list = ArrayList<String>()
        list.add("abd")
        list.add("ccc")
        rv_your_ads.adapter =YourAdsRecyclerViewAdapter(list,requireActivity())

    }


}