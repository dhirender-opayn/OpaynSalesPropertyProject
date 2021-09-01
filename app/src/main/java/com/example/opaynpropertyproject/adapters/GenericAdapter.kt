package com.example.opaynpropertyproject.adapters

import android.os.Binder
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView

abstract class GenericAdapter<T>(var listItem: List<T> , ) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return getViewHolder(LayoutInflater.from(parent.context).inflate(viewType,parent,false),viewType)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        (holder as Binder<T>).bind(listItem[position])
    }

    override fun getItemCount(): Int {
        return listItem.size
    }

    override fun getItemViewType(position: Int): Int {
        return getLayoutId(position,listItem[position])

    }
    protected abstract fun getLayoutId(position: Int,obj:T):Int

    abstract fun getViewHolder(view :View,viewType: Int):RecyclerView.ViewHolder

    internal interface Binder<T>{
        fun bind(data:T)
    }
}