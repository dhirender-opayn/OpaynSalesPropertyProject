package com.example.opaynpropertyproject.addAdsSlides

import android.content.DialogInterface
import android.graphics.Bitmap
import android.net.Uri
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.canhub.cropper.CropImageContract
import com.canhub.cropper.CropImageView
import com.canhub.cropper.options
import com.example.opaynpropertyproject.R
import com.example.opaynpropertyproject.adapters.ads_adapters.ImageUploadRecyclerAdapter
import com.example.opaynpropertyproject.adapters.singleton.SingletonObject
import com.example.opaynpropertyproject.api.ApiResponse
import com.example.opaynpropertyproject.api.Keys
import com.example.opaynpropertyproject.comman.BaseFragment
import com.example.opaynpropertyproject.comman.Utils
import kotlinx.android.synthetic.main.fragment_add_ads3.*
import okhttp3.MultipartBody
import java.io.File


class AddAdsFragment3 : BaseFragment(),View.OnClickListener,ApiResponse {
    val fields = ArrayList<MultipartBody.Part>()
    var imgList:ArrayList<Uri> = ArrayList<Uri>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_add_ads3, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        pre_btn3.setOnClickListener(this)
        img_upload.setOnClickListener(this)
        next_btn_ads3.setOnClickListener(this)
        skip_btn3.setOnClickListener(this)
        if (SingletonObject.propertyFilling.list_of_img != null){
            imgList = SingletonObject.propertyFilling.list_of_img!!
        }
    }

    override fun onClick(v: View?) {
        when(v!!.id){
            R.id.pre_btn3 -> {
                Utils.addReplaceFragment(
                    requireContext(),
                    AddAdsFragment2(),
                    R.id.nav_container1,
                    true,
                    false,
                    false
                )
            }
            R.id.img_upload -> {
                cropImage.launch(
                    options() {
                        setGuidelines(CropImageView.Guidelines.ON)
                        setOutputCompressFormat(Bitmap.CompressFormat.PNG)
                    }
                )
            }
            R.id.next_btn_ads3 ->{
              // what code is here ??? if validation are here so image uploded is not mandantory and and if when respon is success when go next screen so what do here , ???

            }
            R.id.skip_btn3 -> {
                Utils.addReplaceFragment(
                    requireContext(),
                    AddAdsFragment4(),
                    R.id.nav_container1,
                    true,
                    true,
                    true
                )
            }
        }
    }
    private val cropImage = registerForActivityResult(CropImageContract()) { result ->
        if (result.isSuccessful) {

            // use the returned uri
            val uriContent = result.uriContent
            val uriFilePath = result.getUriFilePath(requireActivity()) // optional usage
            Log.e("rr",uriContent.toString())
            if (uriContent != null) {
                imgList.add(uriContent)
                SingletonObject.propertyFilling.list_of_img = imgList
            }
            rv_img_upload.adapter = ImageUploadRecyclerAdapter(imgList, uriFilePath!!,requireContext())
//            image.setImageURI(uriContent)
//            var id = "4" // herer passs the property id
//            var file = File(uriFilePath)
//            serviceViewModel.getMultiPart(Keys.IMAGE,file!!)?.let { fields.add(it) }
//            serviceViewModel.getMultiPart(Keys.PROPERTY_IMG_ID,id!!)?.let { fields.add(it) }
//
//            serviceViewModel.multipartservice(Keys.IMG_END_POINT,requireContext(),fields,Keys.IMG_RED_CODE,true,token,true)
        } else {
            // an error occurred
            val exception = result.error
        }
    }

    override fun onResponse(requestcode: Int, response: String) {
        when(requestcode){
            Keys.IMG_RED_CODE->{
                Utils.addReplaceFragment(
                    requireContext(),
                    AddAdsFragment4(),
                    R.id.nav_container1,
                    true,
                    true,
                    true
                )
            }
        }
    }

}