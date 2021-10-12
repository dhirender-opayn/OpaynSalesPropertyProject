package com.example.opaynpropertyproject

import android.graphics.Bitmap
import android.os.Bundle
import android.util.Log
import android.view.View
import com.canhub.cropper.CropImageContract
import com.canhub.cropper.CropImageView
import com.canhub.cropper.options
import com.example.opaynpropertyproject.api.ApiResponse
import com.example.opaynpropertyproject.api.Keys
import com.example.opaynpropertyproject.api_model.LoginSuccessModel
import com.example.opaynpropertyproject.comman.BaseActivity
import com.example.opaynpropertyproject.comman.SharedPreferenceManager
import com.example.opaynpropertyproject.comman.Utils
import com.example.opaynpropertyproject.home_activity.HomeActivity.Companion.token
import com.example.opaynpropertyproject.login_signup_activity.ForgetPasswordActivity
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_account_setting.*
import kotlinx.android.synthetic.main.toolbar.*
import okhttp3.MultipartBody
import java.io.File

class AccountSettingActivity : BaseActivity(), View.OnClickListener, ApiResponse {
    var id = 0
    val fields = ArrayList<MultipartBody.Part>()
    var fileImag: File? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_account_setting)
        supportActionBar!!.hide()
//        if (intent.getParcelableExtra<GetProfileSuccess>("key") != null) {
//            val user = intent.getParcelableExtra<GetProfileSuccess>("key")
//            profile_setting_user_name.setText(user?.data?.user?.name)
//            profile_setting_email.setText(user?.data?.user?.email)
//            profile_setting_phone.setText(user?.data?.user?.mobile)
//        }
        setClick()
        profileHeader()
        setData()
    }

    private fun profileHeader() {
        ads.visibility = View.VISIBLE
        ads.setText(getString(R.string.account_setting))
        menu_bar.visibility = View.VISIBLE
        notification_count.visibility = View.INVISIBLE
        menu_bar.setImageResource(R.drawable.ic_baseline_arrow_back_ios_24)
        search_bar_container.visibility = View.INVISIBLE
        header_filer.visibility = View.INVISIBLE
    }


    private fun setData() {
        val mGon = SharedPreferenceManager(this).getString(Keys.USERDATA)
        val model = gson.fromJson(mGon, LoginSuccessModel::class.java)
        profile_setting_user_name.setText(model.data.user.name)
        profile_setting_email.setText(model.data.user.email)
        profile_setting_phone.setText(model.data.user.mobile.toString())
        Picasso.get().load(model.data.user.profile.image).placeholder(R.drawable.down_arrow).into(account_setting_img_holder)


    }

    private fun setClick() {
        menu_bar.setOnClickListener(this)
        profile_save_btn.setOnClickListener(this)
        change_password.setOnClickListener(this)
        pic_edit_button.setOnClickListener(this)
    }

    override fun onClick(v: View?) {
        when (v!!.id) {
            R.id.menu_bar -> {
                onBackPressed()
            }
            R.id.profile_save_btn -> {
                profileApi()
            }
            R.id.change_password -> {

                openA(ForgetPasswordActivity::class)
            }
            R.id.pic_edit_button -> {
                cropImage.launch(
                    options() {
                        setGuidelines(CropImageView.Guidelines.ON)
                        setOutputCompressQuality(50)
                        setOutputCompressFormat(Bitmap.CompressFormat.PNG)
                    }
                )
            }
        }

    }

//    private fun setData() {
//        val mGon = SharedPreferenceManager(this).getString(Keys.USERDATA)
//        val model = gson.fromJson(mGon, LoginSuccessModel::class.java)
//        id = model.data.user.id
//        val mUser_name = model.data.user.name
//        val mUser_email = model.data.user.email
//        val mUser_mobile = model.data.user.mobile
//    }

    private fun profileApi() {



        serviceViewModel.getMultiPart(Keys.USERID, SharedPreferenceManager(this).getString(Keys.USERID).toString())?.let { fields.add(it) }
        serviceViewModel.getMultiPart(Keys.USER_NAME, profile_setting_user_name.text.toString())?.let { fields.add(it) }
        serviceViewModel.getMultiPart(Keys.USER_EMAIL, profile_setting_email.text.toString())?.let { fields.add(it) }
        serviceViewModel.getMultiPart(Keys.USER_MOBILE, profile_setting_phone.text.toString())?.let { fields.add(it) }


        if (fileImag!=null)
        {
            serviceViewModel.getMultiPart(Keys.IMAGE, fileImag!!)?.let { fields.add(it) }
        }
        if (Keys.isCustomer)
        {
            serviceViewModel.multipartservice(
                Keys.PROFILE_CUSTOMER_END_POINT, this, fields,
                Keys.PROFILE_CUSTOMER_RED_CODE, true, token, true, this
            )
        }
        else {
            serviceViewModel.multipartservice(
                Keys.PROFILE_END_POINT, this, fields,
                Keys.PROFILE_RED_CODE, true, token, true, this
            )

        }
    }

    override fun onResponse(requestcode: Int, response: String) {
        when (requestcode) {
            Keys.PROFILE_RED_CODE -> {

                SharedPreferenceManager(this).saveString(Keys.USERDATA, response)
                Utils.customSnakebar(profile_save_btn, getString(R.string.data_updated))
            }
            Keys.PROFILE_CUSTOMER_RED_CODE -> {
                SharedPreferenceManager(this).saveString(Keys.USERDATA, response)
                Utils.customSnakebar(profile_save_btn, getString(R.string.data_updated))
            }
            Keys.BACKENDERROR -> {
                Utils.customSnakebar(profile_save_btn, getString(R.string.error))
            }
        }
    }

    private val cropImage = registerForActivityResult(CropImageContract()) { result ->
        if (result.isSuccessful)
        {
            // use the returned uri
            val uriContent = result.uriContent
            val uriFilePath = result.getUriFilePath(this) // optional usage
            Log.e("rr", uriContent.toString())
            if (uriContent != null) {
                var file = File(uriFilePath.toString())
                Log.e("file", file.length().toString())
                Log.e("file2", file.isFile.toString())
                fileImag = file
                account_setting_img_holder.setImageURI(uriContent)
            }

        } else
        {
            // an error occurred
            val exception = result.error
        }
    }

}