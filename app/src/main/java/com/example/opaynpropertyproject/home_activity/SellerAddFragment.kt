package com.example.opaynpropertyproject.home_activity

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.opaynpropertyproject.R
import com.example.opaynpropertyproject.`interface`.GetPositionInterface
import com.example.opaynpropertyproject.adapter.property_setup_adapters.ImageUploadRecyclerAdapter
import com.example.opaynpropertyproject.seller_home_adapter.SellerPropertyRecyclerViewAdapter
import com.example.opaynpropertyproject.api.ApiResponse
import com.example.opaynpropertyproject.api.Keys
import com.example.opaynpropertyproject.api_model.seller_home_model.SellerPropertyListingModel
import com.example.opaynpropertyproject.comman.BaseFragment
import com.example.opaynpropertyproject.comman.Utils
import com.example.opaynpropertyproject.home_activity.HomeActivity.Companion.token
import com.example.opaynpropertyproject.singleton.SingletonObject
import kotlinx.android.synthetic.main.fragment_seller.*
import kotlinx.android.synthetic.main.photoupload_add_fragment.*


class SellerAddFragment : BaseFragment(), ApiResponse, GetPositionInterface {
    var property_list: ArrayList<SellerPropertyListingModel.Data>? = ArrayList()
    val propertyHash_list = HashMap<String, Any>()
    var delete_position = 0
    var sellerPropertyRecyclerViewAdapter: SellerPropertyRecyclerViewAdapter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_seller, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        serviceViewModel.getservice(
            Keys.GET_DEALER_ADD_END_POINT,
            requireContext(),
            Keys.GET_DEALER_ADD_REQ_CODE,
            true,
            token,
            true,
            this
        )

    }

    override fun onResponse(requestcode: Int, response: String) {
        when (requestcode) {
            Keys.GET_DEALER_ADD_REQ_CODE -> {
                val property_model = gson.fromJson(response, SellerPropertyListingModel::class.java)
                property_list!!.addAll(property_model.data)
                if (property_list != null) {
                    // rv_your_ads.adapter = SellerPropertyRecyclerViewAdapter(property_list!!,this,requireActivity())
                    propertyAdapter()
                    SingletonObject.propertyFilling.fullPropertySetUpModel = property_list
                }

            }
            Keys.PROPERTY_DELETE_REQ_CODE -> {
                removeItem(delete_position)
            }
        }
    }

    override fun getPosition(position: Int) {
        val propertyId = property_list?.get(position)!!.id

        propertyHash_list.put(Keys.PROPERTY_ID, propertyId)
        serviceViewModel.deleteserviceBody(
            Keys.PROPERTY_DELETE_END_POINT + propertyId,
            requireContext(),
            propertyHash_list,
            Keys.PROPERTY_DELETE_REQ_CODE,
            true,
            token,
            true,
            this
        )
        delete_position = position
    }

    fun removeItem(position: Int) {
        if (property_list!!.size > 1) {
            property_list!!.removeAt(position)
            SingletonObject.propertyFilling.update_property = property_list!!.size
            // SellerPropertyRecyclerViewAdapter(property_list!!, this,requireContext()).notifyItemChanged(position)
            sellerPropertyRecyclerViewAdapter?.notifyItemChanged(position)
        } else {
            Log.e("emptProperty", "No Properties")
            property_list!!.clear()
            propertyAdapter()
        }
    }

    fun propertyAdapter() {
        sellerPropertyRecyclerViewAdapter =
            SellerPropertyRecyclerViewAdapter(property_list!!, this, requireContext())
        rv_your_ads.adapter = sellerPropertyRecyclerViewAdapter
    }


}