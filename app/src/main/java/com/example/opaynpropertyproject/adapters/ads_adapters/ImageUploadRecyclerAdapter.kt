package com.example.opaynpropertyproject.adapters.ads_adapters

import ServiceViewModel
import android.content.Context
import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContentProviderCompat.requireContext
import androidx.recyclerview.widget.RecyclerView
import com.example.opaynpropertyproject.R
import com.example.opaynpropertyproject.api.Keys
import kotlinx.android.synthetic.main.fragment_add_ads3.view.*
import kotlinx.android.synthetic.main.image_upload_view_holder.view.*
import okhttp3.MultipartBody
import java.io.File
import java.net.URL

class ImageUploadRecyclerAdapter(val listImageUrl: List<Uri>, val imgFilePath:String, val context:Context) : RecyclerView.Adapter<ImageUploadViewHolder>(){
    val serviceViewModel = ServiceViewModel()
    val fields = ArrayList<MultipartBody.Part>()

    val token =
        "eyJ0eXAiOiJKV1QiLCJhbGciOiJSUzI1NiJ9.eyJhdWQiOiIxIiwianRpIjoiZTc0M2QxNTQwNGRhMWQ4NjU1MGI1Njk1MjBlMDJhN2U2OTdiNGFiMDY2MjgyZTgwMjc1ODJhMmZjNmEzMDQ4YTI0M2MyNTljZTUyYzAwYTkiLCJpYXQiOjE2MzA5MDY1OTQuNzI0NTY0LCJuYmYiOjE2MzA5MDY1OTQuNzI0NTY1LCJleHAiOjE2NjI0NDI1OTQuNzE5NTc1LCJzdWIiOiIyMyIsInNjb3BlcyI6W119.miTFb8YyDL186NlROqnHHjOVnCdRXEs2lhDOk_V6LLaiImsfhtQ3KBrBiPYC7hrOOXY1xLfzu1pPNCS-y21SSiB_iQ403f1i3lh_LRUSzTe5EGyNc4Ejz3Ixuekw1iuWJq_yFmvqi4j14UL_BI55y4jgtUwhV2Z3MVMgxcHmRoYKem2Tr6UhW6OiVLjzt2HExJc5JsT6SCjbSc4UXSW4IV6V-_46Q3Vv5s8iylm0vmZBF4IJso2xJPwydVd8s7jIOq1y3CPCVj6gWkHyi_SlKTQfAqKMcBDjhg1sFIGGsJP-foORlvtsR33mp2AG7Rp46R2saqH5baMEnj-W6-KFhuYQs83-Fa-nGZC8cuTH7laWD_t-7jOU_whtbHS_Ydf46gN5TSQFAOLEsTBI310qhpgTpEAf1qkinUFDEAPdJ-XuRS_iaVBFLGmwcmrsphpGpAK5_tdqTY52OSUdq63CRqJfY6UkSBdD5dWQpb9wUy-iAvb010qea1duZWqXTnVuZ0bjXRuGx_Zo4_lB8FZCLFcs0D09mlJSx_Iu_72o8vp6lH9pbCYIFMQY9s7bHeZuyyOhP1W_W4qHljT6AXO16joUrArEY3FTGvj9Sxkfzy_KVGRSxb-VerbB54bx2eHM7Zaacx3iHOWZivucINzr96BtessxXZNw1jtdkkCUBf8"


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ImageUploadViewHolder {
        val view = ImageUploadViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.image_upload_view_holder, parent, false)
        )
        return view
    }

    override fun onBindViewHolder(holder: ImageUploadViewHolder, position: Int) {

        var id = "4" // herer passs the property id
        var file = File(imgFilePath)
        holder.imageView.setImageURI(listImageUrl[position])

        serviceViewModel.getMultiPart(Keys.IMAGE,file!!)?.let { fields.add(it) }
        serviceViewModel.getMultiPart(Keys.PROPERTY_IMG_ID,id!!)?.let { fields.add(it) }

        serviceViewModel.multipartservice(
            Keys.IMG_END_POINT,context,fields,
            Keys.IMG_RED_CODE,true,token,true)

    }

    override fun getItemCount(): Int {
       return listImageUrl.size
    }

}
class ImageUploadViewHolder(itemView:View):RecyclerView.ViewHolder(itemView){
    val imageView = itemView.rv_img_uploader_item

}