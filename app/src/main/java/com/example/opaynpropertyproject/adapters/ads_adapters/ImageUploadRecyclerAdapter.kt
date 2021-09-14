package com.example.opaynpropertyproject.adapters.ads_adapters


import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.opaynpropertyproject.R
import com.example.opaynpropertyproject.api.ApiResponse
import com.example.opaynpropertyproject.api.Keys
import com.example.opaynpropertyproject.api_model.DeleteSuccessModel
import com.example.opaynpropertyproject.api_model.ErrorModel
import com.example.opaynpropertyproject.api_model.ImageUploadModelSuccessfully
import com.google.gson.Gson
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.image_upload_view_holder.view.*
import okhttp3.MultipartBody
import ServiceViewModel
import com.example.opaynpropertyproject.`interface`.ImageUploadInterface
import com.example.opaynpropertyproject.adapters.singleton.SingletonObject.propertyFilling
import com.example.opaynpropertyproject.comman.Utils


class ImageUploadRecyclerAdapter(
    val listImageUrl: ArrayList<ImageUploadModelSuccessfully.Data>,
    val imageInterface: ImageUploadInterface,
    val context: Context
) : RecyclerView.Adapter<ImageUploadViewHolder>() {
    val serviceViewModel = ServiceViewModel()
    val fields = ArrayList<MultipartBody.Part>()
    val imgHash_list = HashMap<String, Any>()
    val gson = Gson()
    var delete_position = 0
    val token =
        "eyJ0eXAiOiJKV1QiLCJhbGciOiJSUzI1NiJ9.eyJhdWQiOiIxIiwianRpIjoiMGNlMmI5MDY3NGViNjhiNjY3MWM1NTk1NzhkNmIxZTEyNWFkYjU0OWE1OTQ0ZjE4YjI3ZTEyNmEzZjU3MWM1ZWYzOWJkNjkyOTkyNTU4MDMiLCJpYXQiOjE2MzE1Mjg4MjYuODAwODEzLCJuYmYiOjE2MzE1Mjg4MjYuODAwODE2LCJleHAiOjE2NjMwNjQ4MjYuNzk0ODYsInN1YiI6IjIzIiwic2NvcGVzIjpbXX0.jpy6jl-pIDtYhCAv12W1WrFbbi336jZOiv2VkJFomr5gy0JKOs-dpYREMJhdf1MPWRKVzXPnLh9Sqzhw0rnfQ40zS0lblTOV6vRplVYT_uKuNK7ZKoa4otRp2BIOVF7qPfCwLxDpWdtJa0nCLa6L8Av5rBw1qOzl46toPjNiUS_UdW6IAOtze1DzVrS8jpbZJepHUQfdIfI6NFbaYSmcRHE9v0N8WWtExMIPaH1tP3PbUaM74l9PXsR58UU1jk_PWOpivjhv64gtxxnJRxRzBDtQD2DRmwRco07bQehiu68rlsv7Tu3Q9Hfl12DADyuRfUPYWQLxxs8eVODqNe2nDqkH_VbExCbDkv3ifdre-yp3sjDUbJ0iCIPOX5P9fl0NsicPxa-lKfAZCl8UEsrPfdoHuI4lVvGbor9iANaqMmTAzWw6vIAzz_eD6pAIpuSBECJ6Uyt1LyE7c_rn7rs4s_aXknqTwkDL95Xx1zV7IPchzTxUepyCN0xxYXdtx81CVCORj8qp22DO85DyuFtvtuhsjzDBeZC9dhGa-dcU0NHxLZKNFMDs76opR2arcy8GVaK_SeE73_xu7ygpR3UGxr83UpePWSs45qMefqXBAesxQFFrN38Ia7M7g3RCYVemd1tnkiZ7_Okz8S1Bf5NCd25DeawvTGJCKn862XlEHZ8"


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ImageUploadViewHolder {
        val view = ImageUploadViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.image_upload_view_holder, parent, false)
        )
        return view
    }

    override fun onBindViewHolder(holder: ImageUploadViewHolder, position: Int) {
        if (listImageUrl.isNotEmpty()) {
            Log.e("Check", listImageUrl[position].image.image)
            Picasso.get().load(listImageUrl[position].image.image)
                .placeholder(R.drawable.down_arrow).into(holder.imageView)


            holder.cancel_btn.setOnClickListener {
                delete_position = holder.adapterPosition
                imageInterface.imageUpload(holder.adapterPosition)
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