package com.example.opaynpropertyproject.home_activity
import android.os.Bundle
 import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.opaynpropertyproject.R
import com.example.opaynpropertyproject.seller_home_adapter.SellerPropertyRecyclerViewAdapter
 import com.example.opaynpropertyproject.api.ApiResponse
import com.example.opaynpropertyproject.api.Keys
import com.example.opaynpropertyproject.api_model.seller_home_model.SellerPropertyListingModel
import com.example.opaynpropertyproject.comman.BaseFragment
import com.example.opaynpropertyproject.home_activity.HomeActivity.Companion.token
import com.example.opaynpropertyproject.singleton.SingletonObject
import kotlinx.android.synthetic.main.fragment_seller.*


class SellerAddFragment : BaseFragment(),ApiResponse {
    var property_list :   ArrayList<SellerPropertyListingModel.Data>? = ArrayList()

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
        serviceViewModel.getservice(Keys.GET_DEALER_ADD_END_POINT,requireContext(),Keys.GET_DEALER_ADD_REQ_CODE,true,token,true,this)

    }
    override fun onResponse(requestcode: Int, response: String) {
        when(requestcode){
            Keys.GET_DEALER_ADD_REQ_CODE -> {
                val property_model = gson.fromJson(response,SellerPropertyListingModel::class.java)
                property_list!!.addAll(property_model.data)
                if (property_list!= null){
                    rv_your_ads.adapter = SellerPropertyRecyclerViewAdapter(property_list!!,requireActivity())
                    SingletonObject.propertyFilling.fullPropertySetUpModel = property_list
                }

            }
        }

    }


}