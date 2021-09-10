package com.example.opaynpropertyproject.addAdsSlides

import ServiceViewModel
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.opaynpropertyproject.R
import com.example.opaynpropertyproject.adapters.singleton.SingletonObject.propertyFilling
import com.example.opaynpropertyproject.api.Keys
import kotlinx.android.synthetic.main.fragment_add_ads4.*


class AddAdsFragment4 : Fragment() {
    lateinit var serviceViewModel: ServiceViewModel
    lateinit var addPropertyHasMap: HashMap<String, Any>
    var price = ""
    var property_des = ""
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_add_ads4, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        serviceViewModel = ServiceViewModel()
        addPropertyHasMap = HashMap<String, Any>()
        price = price_input.text.toString()
        property_des = property_description.text.toString()
        propertyFilling.property_price = price
        propertyFilling.property_description = property_des
        finish_btn.setOnClickListener {
            serviceViewModel.postservice(Keys.ADD_PROPERTY_END_POINT,requireContext(),some list,)
        }
    }
}