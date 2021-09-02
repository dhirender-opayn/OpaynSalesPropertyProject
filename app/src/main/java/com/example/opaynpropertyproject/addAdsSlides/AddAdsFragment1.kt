package com.example.opaynpropertyproject.addAdsSlides

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.example.opaynpropertyproject.R
import com.example.opaynpropertyproject.adapters.ads_adapters.PropertyTypeRecyclerViewAdapter
import com.example.opaynpropertyproject.adapters.ads_adapters.SellerTypeRecyclerViewAdapter
import kotlinx.android.synthetic.main.fragment_add_ads1.*


class AddAdsFragment1 : Fragment() {

    lateinit var list: ArrayList<String>
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
        list = ArrayList()
        list.add("Ddd")
        list.add("ddd")
        stateAdapter()


        recyclerView_sell_type.layoutManager =
            StaggeredGridLayoutManager(3, LinearLayoutManager.VERTICAL)
        recyclerView_sell_type.adapter = SellerTypeRecyclerViewAdapter(list)

        recyclerView_property_type.layoutManager = GridLayoutManager(requireContext(), 3)
        recyclerView_property_type.adapter = PropertyTypeRecyclerViewAdapter(list)


    }

    fun stateAdapter() {
        val adapter = ArrayAdapter(
            requireActivity(),
            android.R.layout.simple_spinner_item,  list
        )
        state_list.adapter = adapter
        state_list.onItemSelectedListener = object :
            AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                parent: AdapterView<*>,
                view: View, position: Int, id: Long
            ) {
//                subcatid = subcategorylist[position].id
            }

            override fun onNothingSelected(parent: AdapterView<*>) {
                // write code to perform some action
            }
        }

    }




}