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
import com.example.opaynpropertyproject.`interface`.ImageUploadInterface
import com.example.opaynpropertyproject.api_model.ImageModel
import com.example.opaynpropertyproject.comman.Utils


class ImageUploadRecyclerAdapter(
    val listImageUrl: ArrayList<ImageModel>,
    val imageInterface: ImageUploadInterface,
    val context: Context
) : RecyclerView.Adapter<ImageUploadViewHolder>() {
    val serviceViewModel = ServiceViewModel()
    val fields = ArrayList<MultipartBody.Part>()
    val imgHash_list = HashMap<String, Any>()
    val gson = Gson()
    var delete_position = 0
    val token =
        "eyJ0eXAiOiJKV1QiLCJhbGciOiJSUzI1NiJ9.eyJhdWQiOiIxIiwianRpIjoiNzdhNTMyNmNjYjlmN2EyM2EwMWZmYjU5MzU0MGI0ZTNlM2E3ZjEwZDA3ZTUzZWE3MGU0MTQ1YTYwNGZjMzM1MDBlODY4MjZmNGExYzBiNmUiLCJpYXQiOjE2MzE3MDc3MDcuMTAyNTA4LCJuYmYiOjE2MzE3MDc3MDcuMTAyNTA5LCJleHAiOjE2NjMyNDM3MDcuMDk3MzE1LCJzdWIiOiIyMyIsInNjb3BlcyI6W119.n44bxrJdwEd_MkhflYsFWrw1KXc7bZv9hmHteKS9n6Ce1pL66DziAeHgpIGLTswkRf2297PkO6vc1spkrEMl_6CQMcYla5oMJdxuIoU4AmZAJ8N7_NKOpYkAeBc4QwTsOIof1V_QEEVjz5MUu0bWcFnkMRTAAQCiv8-rSSv1hi1UHsqIPbVHw3iPBJps92J5srrt13suEA5mtn6oPOBrKIm1eKIzPFoCRq7bSgChg5vUAsqLiTN4bfaB9pJyUEABKLG2H76zk2n2CkO0RzCtOU2Z9EI08Hpqn6j4RQGWv1Yl-d6M1PGLEvs_olmcMnGxm9llSiC7AJa5fYEH8fRyCcBDGvK88Y2k1cT1njSOjzbCiLvPXoiKq_4vtOppgWZcWr9aj7rnWlnYVopkxNxgre0i8szlWjGn3MNU5T1P7TKLE4TmVlYBEaYTjX8R33uWVz8jNMZT2xefa3ujIIifYF0jpUZDLMaC9hlwMiOTZY2iLkqnsfX8Gn3_qqko3Vq6aO5w2qcmmL53Ssuj9IVR-idcHBEj8oksfNLYeJaDVuRWkTlNNJWCOFF5LP1lFLO22SuNUDkdyW1mRl4SS1qM-d_lTjmsSzGN1ji5hjd5aXxRVXlQdNm5J3McsLokMWl_BX74S87neGp72MwuMjBojc_2k523tVFX5VRMGVJoX2s"


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