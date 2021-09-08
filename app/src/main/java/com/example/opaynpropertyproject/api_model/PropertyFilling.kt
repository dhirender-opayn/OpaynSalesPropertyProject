package com.example.opaynpropertyproject.api_model

class PropertyFilling {
    var sell_type: String = ""
    var sell_property_list: List<SellPropertyModel.Data.Option>? = null
    var sell_postion = 0

    var property_type: String = ""
    var state: String = ""
    var stateID: Int = 0
    var statePosition: Int = 0
    var stateSpinnerList = ArrayList<String>()
    var stateSpinnerModel = ArrayList<StateModel.Data>()

    var city: String = ""
    var citySpinnerList = ArrayList<String>()
    var citySpinnerModel = ArrayList<CityModel.Data>()
    var cityId = 0
    var cityPosition = 0
    var address: String = ""
    var pinCode: String = ""
    var poessionType: String = ""
    var area: String = ""
    var postedby = ""
    var amenties = ""
    var age_of_property = ""
    var entrance = ""
    var furnish = ""
    var sqft = ""
    var no_bed = ""
    var no_bathroom = ""
}