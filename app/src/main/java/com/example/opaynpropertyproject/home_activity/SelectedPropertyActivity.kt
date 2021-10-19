package com.example.opaynpropertyproject.home_activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.core.view.isVisible
import com.example.opaynpropertyproject.R
import com.example.opaynpropertyproject.VisiterPropertiesAdapter
import com.example.opaynpropertyproject.api.ApiResponse
import com.example.opaynpropertyproject.api.Keys
import com.example.opaynpropertyproject.api_model.*
import com.example.opaynpropertyproject.comman.BaseActivity
import com.example.opaynpropertyproject.customer.CustomerHomeActivity
import com.example.opaynpropertyproject.seller_home_adapter.FacilitiesRecyclerViewAdapter
import com.example.opaynpropertyproject.singleton.SingletonObject
import com.google.android.gms.common.api.ApiException
import com.greetupp.extensions.isNotNull
import com.greetupp.extensions.isNull
import com.opaynkart.ui.adapters.ImageSliderAdapter
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_account_setting.*
import kotlinx.android.synthetic.main.activity_selected_property.*
import java.io.File

class SelectedPropertyActivity : BaseActivity(), ApiResponse {
    var selectedPropertyPositionID = 0
    val fav_hashMap_List = HashMap<String, Any>()
     var property_id = 0
     var islike = false
    var property_list1: ArrayList<CustomerHomeModel.Data.Data>? = null
    var property_list_search: ArrayList<SearchModelSuccess.Data.Data>? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_selected_property)
        overridePendingTransition(0, 0)
        supportActionBar?.hide()

        selectedPropertyPositionID = intent.getIntExtra(Keys.POSITION, -1)
        detailApi()
        //visiter
        rv_visiter_review.adapter = VisiterPropertiesAdapter()
//        //Facilities
//        rv_selected_property_facilities.adapter = FacilitiesRecyclerViewAdapter()
        selected_ads_back_btn.setOnClickListener {
            onBackPressed()
        }



        single_fav.visibility = View.VISIBLE

        single_fav.setOnClickListener {

            if (!islike) {
                single_fav.setImageResource(R.drawable.ic_red_heart)
                savedProperty()
            } else {
                single_fav.setImageResource(R.drawable.ic_white_heart)
                savedProperty()

            }
        }
    }

    private fun propertyDetails(detailPropertyModel: DetailPropertyModel)
    {
        if (Keys.isCustomer){
            selected_ads_edit.visibility = View.INVISIBLE
            selected_customer_ads_forward.visibility = View.VISIBLE
            selected_ads_forward.visibility = View.INVISIBLE
            yours_ads_head_address.setText(detailPropertyModel.data.name.toString())
            yours_ads_sub__address.setText(detailPropertyModel.data.address.toString())
            yours_ads_price.setText("$ " + detailPropertyModel.data.price.toString())
            yours_ads_bed.setText(detailPropertyModel.data.profile?.bed_rooms.toString() + " Bed")
            yours_ads_bathroom.setText(detailPropertyModel.data.profile.bath_rooms.toString() + " Ba")
            yours_ads_area.setText(detailPropertyModel.data.profile.area.toString() + " sqft")
            selected_ads_img.adapter = ImageSliderAdapter(this)
            property_id=detailPropertyModel.data.id
        }


    }



    private fun savedProperty()
    {
            if (!islike)
            {
                 fav_hashMap_List.put(Keys.FAV_PROPERTY_ID, property_id)
                serviceViewModel.postservice(
                    Keys.ADD_PROPERTY_FAV_END_POINT,
                    this,
                    fav_hashMap_List,
                    Keys.ADD_PROPERTY_FAV_REQ_CODE,
                    true,
                    HomeActivity.token,
                    true,
                    this
                )
                islike = true
            }
            else
            {
                 serviceViewModel.deleteserviceBody(
                    Keys.ADD_PROPERTY_FAV_END_POINT,
                    this,
                    fav_hashMap_List,
                    Keys.DEL_WISHLIST_REQ_CODE,
                    true,
                    HomeActivity.token,
                    true,
                    this
                )
                islike = false
            }
    }




    private  fun detailApi(){

        serviceViewModel.getservice(
            Keys.SELECTED_PROPERTY_END_POINT + selectedPropertyPositionID ,
            this,
            Keys.SELECTED_PROPERTY_REQ_CODE,
            true,
            CustomerHomeActivity.token,
            true,
            this
        )
    }


    override fun onResponse(requestcode: Int, response: String) {
        when (requestcode) {
            Keys.ADD_PROPERTY_FAV_REQ_CODE -> {
                val wishListModel = gson.fromJson(response, GetWishListModel::class.java)
                Toast.makeText(this, wishListModel.message, Toast.LENGTH_LONG).show()
                Toast.makeText(this, "Like", Toast.LENGTH_LONG).show()
            }
            Keys.DEL_WISHLIST_REQ_CODE -> {
                Toast.makeText(this, "Dislike", Toast.LENGTH_LONG).show()
                Log.e("deleteLike", "dislikeDeleteProperty")
            }
            Keys.SELECTED_PROPERTY_REQ_CODE -> {
                val detailModel = gson.fromJson(response, DetailPropertyModel::class.java)
                propertyDetails(detailModel)
            }
        }

    }

}