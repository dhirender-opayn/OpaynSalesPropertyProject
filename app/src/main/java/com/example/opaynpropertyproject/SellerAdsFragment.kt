package com.example.opaynpropertyproject
import android.os.Bundle
 import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.opaynpropertyproject.adapters.YourAdsRecyclerViewAdapter
import com.example.opaynpropertyproject.adapters.ads_adapters.SellerTypeRecyclerViewAdapter
import com.example.opaynpropertyproject.api.ApiResponse
import com.example.opaynpropertyproject.api.Keys
import com.example.opaynpropertyproject.api_model.PropertyListingModel
 import com.example.opaynpropertyproject.comman.BaseFragment
import kotlinx.android.synthetic.main.fragment_add_ads1.*
import kotlinx.android.synthetic.main.fragment_seller_ads.*


class SellerAdsFragment : BaseFragment(),ApiResponse {
    var property_list : PropertyListingModel? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_seller_ads, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        serviceViewModel.getservice(Keys.GET_DEALER_ADD_END_POINT,requireContext(),Keys.GET_DEALER_ADD_REQ_CODE,true,token,true,this)

    }

    override fun onResponse(requestcode: Int, response: String) {
        when(requestcode){
            Keys.GET_DEALER_ADD_REQ_CODE -> {
                property_list = gson.fromJson(response,PropertyListingModel::class.java)
                val property_list = property_list!!.data
                rv_your_ads.adapter = YourAdsRecyclerViewAdapter(property_list,requireActivity())
            }
        }

    }


}