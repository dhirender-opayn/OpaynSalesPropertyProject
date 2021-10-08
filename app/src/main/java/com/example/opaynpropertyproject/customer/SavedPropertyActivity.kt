package com.example.opaynpropertyproject.customer

import android.os.Bundle
import android.util.Log
import android.view.View
import com.example.opaynpropertyproject.R
import com.example.opaynpropertyproject.`interface`.GetPositionInterface
import com.example.opaynpropertyproject.adapter.customerAdapter.SavedPropertyRecyclerAdapter
import com.example.opaynpropertyproject.adapter.property_setup_adapters.ImageUploadRecyclerAdapter
import com.example.opaynpropertyproject.api.ApiResponse
import com.example.opaynpropertyproject.api.Keys
import com.example.opaynpropertyproject.api_model.GetWishListModel
import com.example.opaynpropertyproject.api_model.seller_home_model.SellerPropertyListingModel
import com.example.opaynpropertyproject.comman.BaseActivity
import com.example.opaynpropertyproject.home_activity.HomeActivity
import com.example.opaynpropertyproject.singleton.SingletonObject
import kotlinx.android.synthetic.main.activity_saved_property.*
import kotlinx.android.synthetic.main.toolbar.*


class SavedPropertyActivity : BaseActivity(), View.OnClickListener, ApiResponse,
    GetPositionInterface {
    var get_wishlist: ArrayList<GetWishListModel.Data> = ArrayList()
    val del_wishlist_hashMap_List = HashMap<String, Any>()
    var wishlist_adapter: SavedPropertyRecyclerAdapter? = null
    var delWishList_property_position = 0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_saved_property)
        supportActionBar?.hide()


        savedHeader()
        clickListner()

        wishlistApiHit()
    }

    private fun wishlistApiHit() {
        serviceViewModel.getservice(
            Keys.ADD_PROPERTY_FAV_END_POINT,
            this,
            Keys.GET_FAV_REQ_CODE,
            true,
            HomeActivity.token,
            true, this
        )
    }

    private fun clickListner() {
        menu_bar.setOnClickListener(this)
    }


    private fun savedHeader() {
        ads.visibility = View.VISIBLE
        menu_bar.setImageResource(R.drawable.ic_baseline_arrow_back_ios_24)
        ads.setText(getString(R.string.favourite))
        header_filer.visibility = View.INVISIBLE
        menu_bar.visibility = View.VISIBLE
        search_bar_container.visibility = View.INVISIBLE
        notification_count.visibility = View.INVISIBLE
    }

    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.menu_bar -> {
                onBackPressed()
            }
        }
    }

    override fun onResponse(requestcode: Int, response: String) {
        when (requestcode) {
            Keys.GET_FAV_REQ_CODE -> {
                val wishlistModel = gson.fromJson(response, GetWishListModel::class.java)
                get_wishlist.addAll(wishlistModel.data)
                setWishlistAdapter()
            }
            Keys.DEL_WISHLIST_REQ_CODE -> {
                get_wishlist.removeAt(delWishList_property_position)
                wishlist_adapter?.notifyDataSetChanged()
            }

        }
    }

    override fun getPosition(position: Int) {

        if (get_wishlist.size > 1) {
            val property_id = get_wishlist.get(position).id
            del_wishlist_hashMap_List.put(Keys.FAV_PROPERTY_ID, property_id)
            serviceViewModel.deleteserviceBody(
                Keys.ADD_PROPERTY_FAV_END_POINT,
                this,
                del_wishlist_hashMap_List,
                Keys.DEL_WISHLIST_REQ_CODE,
                true,
                HomeActivity.token,
                true,
                this
            )
            delWishList_property_position = position
        } else {
            Log.e("empt", "NoImages")
            get_wishlist.clear()
            setWishlistAdapter()
        }
    }

    private fun setWishlistAdapter() {
        wishlist_adapter = SavedPropertyRecyclerAdapter(get_wishlist, this, this)
        rv_saved_property.adapter = wishlist_adapter
    }


}