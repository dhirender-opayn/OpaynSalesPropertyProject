package com.example.opaynpropertyproject.adapter.property_setup_adapters


import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.opaynpropertyproject.R
import com.example.opaynpropertyproject.api_model.ImageUploadModelSuccessfully
import com.google.gson.Gson
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.image_upload_view_holder.view.*
import okhttp3.MultipartBody
import ServiceViewModel
import com.example.opaynpropertyproject.`interface`.GetPositionInterface
import com.example.opaynpropertyproject.api_model.ImageModel
import com.example.opaynpropertyproject.comman.Utils


class ImageUploadRecyclerAdapter(
    val listImageUrl: ArrayList<ImageModel>,
    val imageInterface: GetPositionInterface,
    val context: Context
) : RecyclerView.Adapter<ImageUploadViewHolder>() {
    val serviceViewModel = ServiceViewModel()
    val fields = ArrayList<MultipartBody.Part>()
    val imgHash_list = HashMap<String, Any>()
    val gson = Gson()
    var delete_position = 0

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ImageUploadViewHolder {
        val view = ImageUploadViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.image_upload_view_holder, parent, false)
        )
        return view
    }

    override fun onBindViewHolder(holder: ImageUploadViewHolder, position: Int)
    {
        if (listImageUrl.isNotEmpty()) {
             Picasso.get().load(listImageUrl[position].imageurl)
                .placeholder(R.drawable.down_arrow).into(holder.imageView)

            holder.cancel_btn.setOnClickListener {
                delete_position = holder.adapterPosition
                imageInterface.getPosition(holder.adapterPosition)
            }
        } else {
            Utils.customSnakebar(holder.cancel_btn, "No images ")
        }

    }

    override fun getItemCount(): Int {
        return listImageUrl.size
    }

}

class ImageUploadViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    val imageView = itemView.rv_img_uploader_item
    val cancel_btn = itemView.your_ads_cancel_btn

}