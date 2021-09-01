package com.example.opaynpropertyproject.adapters

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.opaynpropertyproject.AddAdsFragment1
import com.example.opaynpropertyproject.R
import com.example.opaynpropertyproject.genericClass.AddAds

object JavaViewHolderFactory {
    fun create(view: View, viewType: Int): RecyclerView.ViewHolder {
        return when (viewType) {
            R.layout.sell_type_view_holder -> SellTypeViewHolder(view)
            R.layout.property_type_view_holder-> PropertyType(view)

            else -> {
                SellTypeViewHolder(view)
            }
        }
    }

    class SellTypeViewHolder(itemview: View) : RecyclerView.ViewHolder(itemview),
        GenericAdapter.Binder<AddAds.SellType> {
        var sell_type_name:TextView
        init {
            sell_type_name = itemview.findViewById(R.id.rv_mSell_type)
        }
        override fun bind(data: AddAds.SellType) {
            sell_type_name.text =data.name
        }
    }
    class PropertyType(itemview: View):RecyclerView.ViewHolder(itemview),GenericAdapter.Binder<AddAds.PropertyType> {
        var sell_type_name:TextView
        init {
            sell_type_name = itemview.findViewById(R.id.rv_mProperty_type)
        }
        override fun bind(data: AddAds.PropertyType) {
            sell_type_name.text = data.name
        }
    }
}