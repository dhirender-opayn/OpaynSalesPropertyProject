package com.example.opaynpropertyproject.adapter.customerAdapter

import android.app.AlertDialog
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.example.opaynpropertyproject.R
import com.example.opaynpropertyproject.`interface`.GetPositionInterface

import com.example.opaynpropertyproject.api_model.GetWishListModel

import kotlinx.android.synthetic.main.saved_property_view_holder.view.*


class SavedPropertyRecyclerAdapter(val get_wish_list:ArrayList<GetWishListModel.Data>, val context: Context, val getwishlistPostion :GetPositionInterface) :RecyclerView.Adapter<SavedPropertyRecyclerAdapter.SavedPropertyViewHolder>(){


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SavedPropertyViewHolder {
        val view = SavedPropertyViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.saved_property_view_holder, parent, false)
        )
        return view
    }

    override fun onBindViewHolder(holder: SavedPropertyViewHolder, position: Int) {
        holder.sold_status.visibility = View.INVISIBLE
        holder.property_fav.visibility = View.INVISIBLE
        holder.saved_property_forward_wishlist.visibility = View.VISIBLE

        holder.property_header.setText(get_wish_list.get(position)?.name)
        holder.property_address.setText(get_wish_list.get(position)?.address)
        holder.property_price.setText(get_wish_list.get(position)?.price.toString())
        holder.saved_property_forward_wishlist.setOnClickListener {
            val sendIntent: Intent = Intent().apply {
                action = Intent.ACTION_SEND
                putExtra(Intent.EXTRA_TEXT, "This is my text to send.")
                type = "text/plain"
            }
            val bundle = Bundle()
            bundle.putString("keys","http://schemas.android.com/apk/res/android")
            val shareIntent = Intent.createChooser(sendIntent, null)
            ContextCompat.startActivity(context, shareIntent, bundle)
        }

        holder.bed.setText(get_wish_list[position].profile.bed_rooms.toString()+" bed")
        holder.bathroom.setText(get_wish_list[position].profile.bath_rooms.toString()+" ba")
        holder.sqft.setText(get_wish_list[position].profile.area.toString()+" sqft")

        holder.your_ads_cancel.setOnClickListener {
            val builder = AlertDialog.Builder(context)
            //set title for alert dialog
            builder.setTitle("Delete Property")
            //set message for alert dialog
            builder.setMessage("Are you sure you want to delete property ? ")
            builder.setIcon(android.R.drawable.ic_dialog_alert)

            //performing positive action
            builder.setPositiveButton("Yes") { dialogInterface, which ->

                getwishlistPostion.getPosition(holder.adapterPosition)

            }
            //performing cancel action
            builder.setNeutralButton("Cancel") { dialogInterface, which ->
                Toast.makeText(context, "clicked cancel\n operation cancel", Toast.LENGTH_LONG)
                    .show()
            }
            //performing negative action
            builder.setNegativeButton("No") { dialogInterface, which ->
                Toast.makeText(context, "clicked No", Toast.LENGTH_LONG).show()
            }
            // Create the AlertDialog
            val alertDialog: AlertDialog = builder.create()
            // Set other dialog properties
            alertDialog.setCancelable(false)
            alertDialog.show()







        }


    }

    override fun getItemCount(): Int {
         return get_wish_list!!.size
    }
    class SavedPropertyViewHolder(itemview:View):RecyclerView.ViewHolder(itemview){
        val property_image = itemView.saved_property_img
        val sold_status = itemView.saved_property_sold
        val your_ads_cancel = itemView.saved_property_cancel_btn
        val property_forward_btn =  itemView.saved_property_forward
        val property_fav = itemview.saved_property_fav

        val property_header = itemView.saved_property_head_address
        val property_address = itemView.saved_property_sub__address
        val property_price = itemView.saved_property_price
        val rating = itemView.saved_property_rating_bar
        val customer_rating = itemView.saved_property_rating_view
        val bed = itemView.saved_property_bed
        val bathroom = itemView.saved_property_bathroom
        val sqft = itemView.saved_property_area
        val saved_property_forward_wishlist = itemview.saved_property_forward_wishlist

    }
}