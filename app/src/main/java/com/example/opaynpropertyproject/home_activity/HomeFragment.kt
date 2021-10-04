package com.example.opaynpropertyproject.home_activity

import android.app.Activity
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.opaynpropertyproject.R
import com.example.opaynpropertyproject.`interface`.GetPositionInterface
import com.example.opaynpropertyproject.adapter.HomeRecommendRecyclerAdapter
import com.example.opaynpropertyproject.adapter.home_adapter.WidgetHomeAdapter
import com.example.opaynpropertyproject.adapter.property_setup_adapters.CustomerHomeInnerAdapter
import com.example.opaynpropertyproject.adapter.property_setup_adapters.CustomerHomeOuterAdapter
import com.example.opaynpropertyproject.api.ApiResponse
import com.example.opaynpropertyproject.api.Keys
import com.example.opaynpropertyproject.api_model.CustomerHomeModel
import com.example.opaynpropertyproject.api_model.seller_home_model.SellerPropertyListingModel
import com.example.opaynpropertyproject.comman.BaseFragment
import com.example.opaynpropertyproject.customer.CustomerHomeActivity
import com.example.opaynpropertyproject.home_activity.HomeActivity.Companion.token
import com.opaynkart.ui.adapters.ImageSliderAdapter
import kotlinx.android.synthetic.main.fragment_home.*
import kotlinx.android.synthetic.main.fragment_seller.*
import kotlinx.android.synthetic.main.toolbar.*


class HomeFragment : BaseFragment(), ApiResponse, GetPositionInterface {

    lateinit var activity: Activity
    var home_property_list: ArrayList<SellerPropertyListingModel.Data>? = ArrayList()
    val fav_hashMap_List = HashMap<String, Any>()
    var fav_property_position = 0
    var homePropertyRecyclerViewAdapter: HomeRecommendRecyclerAdapter? = null
    var customer_home_list: ArrayList<CustomerHomeModel.Data.Data>? = ArrayList()
//    val homeActivity = requireActivity() as HomeActivity

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        HomeHeader()

        home_img_pager.adapter = ImageSliderAdapter(requireContext())

        if (Keys.isCustomer) {
            val keyword = "pg"
            //used getserice2 for try ================
            serviceViewModel.getserviceWithKeyword(
                Keys.CUSTOMER_HOME_ADD_END_POINT,
                requireContext(),
                Keys.CUSTOMER_HOME_REQ_CODE,
                true,
                token,
                keyword,
                true,
                this
            )
        } else {
            HitHomeApi()
        }
    }

    private fun HomeHeader() {
        activity = if (Keys.isCustomer) {
            requireContext() as CustomerHomeActivity
        } else {
            requireContext() as HomeActivity
        }
        activity.ads.visibility = View.VISIBLE
        activity.ads.setText("Home")
        activity.menu_bar.visibility = View.VISIBLE
        activity.search_bar_container.visibility = View.INVISIBLE
    }

    private fun HitHomeApi() {
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

    override fun getPosition(position: Int) {
        if (Keys.isCustomer) {
            Keys.customerList = customer_home_list
            val s_property_id = customer_home_list?.get(position)?.id
            val bundle = Bundle()
            if (s_property_id != null) {
                bundle.putInt(Keys.POSITION, s_property_id)

            }
            openA(SelectedPropertyActivity::class, bundle)
        } else {
            val property_id = home_property_list?.get(position)!!.id
            fav_hashMap_List.put(Keys.FAV_PROPERTY_ID, property_id)
            serviceViewModel.postservice(
                Keys.ADD_PROPERTY_FAV_END_POINT,
                requireContext(),
                fav_hashMap_List,
                Keys.ADD_PROPERTY_FAV_REQ_CODE,
                true,
                token,
                true,
                this
            )
            fav_property_position = position
        }

    }

    fun propertyAdapter() {
        homePropertyRecyclerViewAdapter =
            HomeRecommendRecyclerAdapter(home_property_list!!, requireActivity(), this)
        rv_your_ads.adapter = homePropertyRecyclerViewAdapter
    }

    fun removeItem(position: Int) {
        if (home_property_list!!.size > 1) {
            //ImageUploadRecyclerAdapter

            homePropertyRecyclerViewAdapter?.notifyItemChanged(position)
        } else {
            Log.e("emptProperty", "No Properties")
            home_property_list!!.clear()
        }
    }


    override fun onResponse(requestcode: Int, response: String) {
        when (requestcode) {
            Keys.GET_DEALER_ADD_REQ_CODE -> {
                val home_property_model =
                    gson.fromJson(response, SellerPropertyListingModel::class.java)
                home_property_list!!.addAll(home_property_model.data)

                if (home_property_list != null) {

                    rv_home_widget.adapter = WidgetHomeAdapter()
                    rv_recommended_property_home.adapter = HomeRecommendRecyclerAdapter(
                        home_property_list!!,
                        requireActivity(),
                        this
                    ) ///CustomerHomeWidgetAdapter()
                    //  rv_nearby_property.adapter = HomeRecommendRecyclerAdapter()
                }
            }

            Keys.CUSTOMER_HOME_REQ_CODE -> {
                val customerHomeModel =
                    gson.fromJson(response, CustomerHomeModel::class.java)
                customer_home_list!!.addAll(customerHomeModel.data.data)
                if (customer_home_list != null) {
                    rv_home_widget.adapter = WidgetHomeAdapter()
                    rv_recommended_property_home.adapter = CustomerHomeInnerAdapter(
                        customer_home_list!!,
                        requireActivity(),
                        this
                    )
                }
            }
            Keys.ADD_PROPERTY_FAV_REQ_CODE -> {

            }
        }
    }


}
