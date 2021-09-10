package com.example.opaynpropertyproject.adapters.ads_adapters

import android.content.Context
import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.opaynpropertyproject.R
import kotlinx.android.synthetic.main.fragment_add_ads3.view.*
import kotlinx.android.synthetic.main.image_upload_view_holder.view.*

class ImageUploadRecyclerAdapter(val imageList: Uri, val context:Context) : RecyclerView.Adapter<ImageUploadViewHolder>(){
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ImageUploadViewHolder {
        val view = ImageUploadViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.image_upload_view_holder, parent, false)
        )
        return view
    }

    override fun onBindViewHolder(holder: ImageUploadViewHolder, position: Int) {

    }

    override fun getItemCount(): Int {
       return 4
    }

}
class ImageUploadViewHolder(itemView:View):RecyclerView.ViewHolder(itemView){
    val imageView = itemView.rv_img_uploader_item

}