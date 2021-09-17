package com.example.opaynpropertyproject.customer

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AbsListView
import com.example.opaynpropertyproject.R
import com.example.opaynpropertyproject.adapter.customerAdapter.SavedPropertyRecyclerAdapter
import kotlinx.android.synthetic.main.fragment_saved_property.*


class SavedPropertyFragment : Fragment() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_saved_property, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        rv_saved_property.adapter = SavedPropertyRecyclerAdapter()
    }


}