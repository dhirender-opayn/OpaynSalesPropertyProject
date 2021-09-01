package com.example.opaynpropertyproject

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.opaynpropertyproject.adapters.GenericAdapter
import com.example.opaynpropertyproject.adapters.JavaViewHolderFactory
import com.example.opaynpropertyproject.genericClass.AddAds
import kotlinx.android.synthetic.main.fragment_add_ads1.*

class AddAdsFragment1 : Fragment() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_add_ads1, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val list = listOf<Any>(AddAds.SellType("PG"), AddAds.PropertyType("DepartmentHIHI"))
        val adapter = object : GenericAdapter<Any>(list) {
            override fun getLayoutId(position: Int, obj: Any): Int {
                return when (obj) {
                    is AddAds.SellType -> R.layout.sell_type_view_holder
                    is AddAds.PropertyType -> R.layout.property_type_view_holder
                    else -> R.layout.sell_type_view_holder
                }
            }

            override fun getViewHolder(view: View, viewType: Int): RecyclerView.ViewHolder {
                return JavaViewHolderFactory.create(view, viewType)

            }

        }
        recyclerView.layoutManager = LinearLayoutManager(requireContext())
        recyclerView.setHasFixedSize(true)
        recyclerView.adapter = adapter
    }
}