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
import com.example.opaynpropertyproject.api_model.CustomerHomeModel
import com.example.opaynpropertyproject.api_model.GetWishListModel
import com.example.opaynpropertyproject.api_model.SearchModelSuccess
import com.example.opaynpropertyproject.api_model.SellPropertyModel
import com.example.opaynpropertyproject.comman.BaseActivity
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
    var position_fav = 0
    var property_id = 0
    var isSinglePropertyFlag = false
    var property_list: ArrayList<CustomerHomeModel.Data.Data>? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_selected_property)
        overridePendingTransition(0, 0)
        supportActionBar?.hide()

        selectedPropertyPositionID = intent.getIntExtra(Keys.POSITION, -1)


        //visiter
        rv_visiter_review.adapter = VisiterPropertiesAdapter()
//        //Facilities
//        rv_selected_property_facilities.adapter = FacilitiesRecyclerViewAdapter()
        selected_ads_back_btn.setOnClickListener {
            onBackPressed()
        }

        propertyDetails()

        single_fav.visibility = View.VISIBLE

        single_fav.setOnClickListener {

            if (!isSinglePropertyFlag) {
                single_fav.setImageResource(R.drawable.ic_red_heart)
                savedProperty()

                isSinglePropertyFlag = true
            } else {
                single_fav.setImageResource(R.drawable.ic_white_heart)
                savedProperty()
                isSinglePropertyFlag = false
            }
        }
    }

    private fun propertyDetails() {

        if (Keys.isCustomer) {
            if (Keys.isSearchClick) {
                customerSearch()
            } else {
                customerHomeSelection()
            }
        } else {
            //Dealer Single Selection
            property_list = Keys.customerList


            if (property_list != null) {
                for (item in property_list!!.indices) {
                    if (property_list!![item].id == selectedPropertyPositionID) {
                        selected_ads_sold.visibility = View.INVISIBLE
                        selected_ads_edit.visibility = View.INVISIBLE
                        position_fav = item


//============================================33333333333333333333333333333=================


                        property_id = property_list?.get(item)!!.id


                        //================33333333333333333333333333333333333333333======================


                        yours_ads_head_address.setText(property_list?.get(item)?.name.toString())
                        yours_ads_sub__address.setText(property_list?.get(item)?.address.toString())
                        yours_ads_price.setText("$ " + property_list?.get(item)?.price.toString())
                        yours_ads_bed.setText(property_list?.get(item)?.profile?.bed_rooms.toString() + " Bed")
                        yours_ads_bathroom.setText(property_list?.get(item)!!.profile.bath_rooms.toString() + " Ba")
                        yours_ads_area.setText(property_list?.get(item)!!.profile.area.toString() + " sqft")
                        selected_ads_img.adapter = ImageSliderAdapter(this)


                    }

                }

            }

        }
    }

    private fun customerHomeSelection() {
        val property_list = Keys.customerList
        Log.e("87989878", property_list.toString())
        //Facilities
        rv_selected_property_facilities.adapter = FacilitiesRecyclerViewAdapter()

        if (property_list != null) {
            for (item in property_list.indices) {
                if (property_list[item].id == selectedPropertyPositionID) {

                    position_fav = item

                    selected_ads_sold.visibility = View.INVISIBLE
                    selected_ads_edit.visibility = View.INVISIBLE
                    selected_customer_ads_forward.visibility = View.VISIBLE
                    selected_ads_forward.visibility = View.INVISIBLE

                    yours_ads_head_address.setText(property_list?.get(item)?.name.toString())
                    yours_ads_sub__address.setText(property_list?.get(item)?.address.toString())
                    yours_ads_price.setText("$ " + property_list?.get(item)?.price.toString())
                    yours_ads_bed.setText(property_list?.get(item)?.profile.bed_rooms.toString() + " Bed")
                    yours_ads_bathroom.setText(property_list?.get(item).profile.bath_rooms.toString() + " Ba")
                    yours_ads_area.setText(property_list?.get(item).profile.area.toString() + " sqft")
                    selected_ads_img.adapter = ImageSliderAdapter(this)



                    if (property_list?.get(item)?.profile.possession.isNotNull()) {
                        tv_possession_status.setText(property_list?.get(item)?.profile.possession.toString())
                    } else {
                        possession_container.visibility = View.GONE
                    }
                    if (property_list?.get(item)?.profile.area_type.isNotNull()) {
                        tv_area_status.setText(property_list?.get(item)?.profile.area_type.toString())
                    } else {
                        area_container.visibility = View.GONE
                    }

                    if (property_list?.get(item)?.profile.posted_by.isNotNull()) {
                        tv_posted_by_status.setText(property_list?.get(item)?.profile.posted_by.toString())
                    } else {
                        posted_by_container.visibility = View.GONE
                    }

                    if (property_list?.get(item)?.profile.age.isNotNull()) {
                        tv_property_age_status.setText(property_list?.get(item)?.profile.age.toString())
                    } else {
                        property_age_container.visibility = View.GONE
                    }
                    if (property_list?.get(item)?.profile.entrance.isNotNull()) {
                        tv_entrance_status.setText(property_list?.get(item)?.profile.entrance.toString())
                    } else {
                        entrance_container.visibility = View.GONE
                    }
                    if (property_list?.get(item)?.profile.furnishing.isNotNull()) {
                        tv_furnishing_status.setText(property_list?.get(item)?.profile.furnishing.toString())
                    } else {
                        furnishing_container.visibility = View.GONE
                    }

                    selected_customer_ads_forward.setOnClickListener {
                        val sendIntent: Intent = Intent().apply {
                            action = Intent.ACTION_SEND
                            putExtra(Intent.EXTRA_TEXT, "This is my text to send.")
                            type = "text/plain"
                        }
                        val bundle = Bundle()
                        bundle.putString("keys", "http://schemas.android.com/apk/res/android")
                        val shareIntent = Intent.createChooser(sendIntent, null)
                        ContextCompat.startActivity(this, shareIntent, bundle)
                    }


                }

            }

        }
    }


    private fun customerSearch() {
        val property_list_search = Keys.searchList
        for (item in property_list_search!!.indices) {
            if (property_list_search!![item].id == selectedPropertyPositionID) {

                            position_fav = item
                            selected_ads_sold.visibility = View.INVISIBLE
                            selected_ads_edit.visibility = View.INVISIBLE
                            selected_customer_ads_forward.visibility = View.VISIBLE
                            selected_ads_forward.visibility = View.INVISIBLE

                            yours_ads_head_address.setText(property_list_search?.get(item)?.name.toString())
                            yours_ads_sub__address.setText(property_list_search?.get(item)?.address.toString())
                            yours_ads_price.setText("$ " + property_list_search?.get(item)?.price.toString())
                            yours_ads_bed.setText(property_list_search?.get(item)?.profile.bed_rooms.toString() + " Bed")
                            yours_ads_bathroom.setText(property_list_search?.get(item).profile.bath_rooms.toString() + " Ba")
                            yours_ads_area.setText(property_list_search?.get(item).profile.area.toString() + " sqft")

                            selected_ads_img.adapter = ImageSliderAdapter(this)


                            //======================================================//
                            if (property_list_search?.get(item)?.profile.possession.isNotNull()) {
                                tv_possession_status.setText(property_list_search?.get(item)?.profile.possession.toString())
                            } else {
                                possession_container.visibility = View.GONE
                            }
                            if (property_list_search?.get(item)?.profile.area_type.isNotNull()) {
                                tv_area_status.setText(property_list_search?.get(item)?.profile.area_type.toString())
                            } else {
                                area_container.visibility = View.GONE
                            }

                            if (property_list_search?.get(item)?.profile.posted_by.isNotNull()) {
                                tv_posted_by_status.setText(property_list_search?.get(item)?.profile.posted_by.toString())
                            } else {
                                posted_by_container.visibility = View.GONE
                            }
                            if (property_list_search?.get(item)?.profile.age.isNotNull()) {
                                tv_property_age_status.setText(property_list_search?.get(item)?.profile.age.toString())
                            } else {
                                property_age_container.visibility = View.GONE
                            }
                            if (property_list_search?.get(item)?.profile.entrance.isNotNull()) {
                                tv_entrance_status.setText(property_list_search?.get(item)?.profile.entrance.toString())
                            } else {
                                entrance_container.visibility = View.GONE
                            }
                            if (property_list_search?.get(item)?.profile.furnishing.isNotNull()) {
                                tv_furnishing_status.setText(property_list_search?.get(item)?.profile.furnishing.toString())
                            } else {
                                furnishing_container.visibility = View.GONE
                            }


                            //=====================================================================//
                            selected_customer_ads_forward.setOnClickListener {
                                val sendIntent: Intent = Intent().apply {
                                    action = Intent.ACTION_SEND
                                    putExtra(Intent.EXTRA_TEXT, "This is my text to send.")
                                    type = "text/plain"
                                }
                                val bundle = Bundle()
                                bundle.putString(
                                    "keys",
                                    "http://schemas.android.com/apk/res/android"
                                )
                                val shareIntent = Intent.createChooser(sendIntent, null)
                                ContextCompat.startActivity(this, shareIntent, bundle)


                    }


            }
        }
    }


    private fun savedProperty() {

        if (isSinglePropertyFlag) {
            val property_id = property_list?.get(position_fav)!!.id
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
            isSinglePropertyFlag = false

        } else {
            val property_id = property_list?.get(position_fav)!!.id
            fav_hashMap_List.put(Keys.FAV_PROPERTY_ID, property_id)
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
        }
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
        }

    }

}