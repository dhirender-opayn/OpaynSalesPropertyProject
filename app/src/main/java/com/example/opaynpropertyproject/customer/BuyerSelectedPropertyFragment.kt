package com.example.opaynpropertyproject.customer

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.opaynpropertyproject.R
import com.example.opaynpropertyproject.VisiterPropertiesAdapter
import kotlinx.android.synthetic.main.fragment_buyer_selected_property.*


class BuyerSelectedPropertyFragment : Fragment() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_buyer_selected_property, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        rv_buyer_visiter_review.adapter = VisiterPropertiesAdapter()
    }

}