package com.example.opaynpropertyproject.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.view.menu.ActionMenuItemView
import androidx.recyclerview.widget.RecyclerView
import com.example.opaynpropertyproject.R
import kotlinx.android.synthetic.main.your_ads_list_holder.view.*

class YourAdsRecyclerViewAdapter(val your_ads_list:List<String>,val context: Context) :RecyclerView.Adapter<YourAdsRecyclerViewAdapter.YourAdsViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): YourAdsViewHolder {
        val view = YourAdsViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.your_ads_list_holder,parent,false))
        return view
    }

    override fun onBindViewHolder(holder: YourAdsViewHolder, position: Int) {



    }

    override fun getItemCount(): Int {
        return  your_ads_list.size
    }

    class YourAdsViewHolder(itemView: View):RecyclerView.ViewHolder(itemView){
        val your_ads_image = itemView.your_ads_img
        val your_ads_cancel = itemView.your_ads_cancel_btn
        val your_ads_edit = itemView.your_ads_edit
        val your_ads_forward =  itemView.your_ads_forward

    }
}