package com.example.opaynpropertyproject.api

import com.example.opaynpropertyproject.api_model.CustomerHomeModel
import com.example.opaynpropertyproject.api_model.SearchModelSuccess
import com.example.opaynpropertyproject.api_model.SellPropertyModel
import com.example.opaynpropertyproject.api_model.seller_home_model.SellerPropertyListingModel

object Keys {


    var isSearchClick: Boolean =false
    var searchList: ArrayList<SearchModelSuccess.Data.Data>? = null
    var SELECTED_PROPERTY: Boolean = false
    var UPDATE_EDIT: Boolean = false
    var IS_BACK_BTN_PRESS: Boolean = false
    var fav_flag: Boolean = false
    var isGoogleAccount: Boolean= false
    val GOOGLE_ACCOUNT: String = "google_account"
    val RECEIVER_ID = 2
    val SENDER_ID = 1
    val UPLOAD_PIC = "image"
    var add_fav_flag: Boolean = false
    var SENDER: Boolean = false
    var customerList: ArrayList<CustomerHomeModel.Data.Data>? = null
    const val BASEURL = "http://7a11-180-188-237-214.ngrok.io/realestate-api/public/api/"
    val QUERY_DES: String ="description"
    val QUERY_SUB: String = "subject"
    val FURNISHING: String = "furnishing"
    val ENTRANCE: String = "entrance"
    val AGE_OF_PROPERTY: String = "age"
    val AREA_TYPE: String = "area_type"
    val POSTED_BY: String = "posted_by"
    val PROPERTY_BATH_ROOM: String = "bath_rooms"
    val PROPERTY_NO_BED = "bed_rooms"
    val POSSESSION_TYPE = "possession"
    val ADS_DATA = "AdsData"
    val SUCESSCODE = 200
    val UNAUTHRISECODE = 401
    val SERVERERROR = 201
    val BACKENDERROR = 412
    val PROFILE_DATA = "profile_data"
    val POSITION = "position"
    //signup End point
    var signupEndPoint = "signup"
    var signup_step_One = 1 // login signup step is called 1 and assign profile is step 2
    var signup_step_two = 2
    var Req_log = 1
    val signup_name = "name"
    val signup_email = "email"
    val signup_password = "password"
    val signup_agree = "agree"
    val signup_Step = "step"

    // signup step 2 --- buyer or seller
    var SIGNUP_ID = "id"
    var SIGNUP_USER_TYPE = "user_type"

    //Email Verify by OTP
    var EMAIL_VERIFY_END_POINT = "email-verify"
    var USER_INPUT_OTP = "code"
    var MAIL_REQ_CODE = 4

    //Login End points
    var loginEndPoint = "login"
    val login_email = "email"
    val login_password = "password"
    var login_log = 1

    //RESET
    var USER_OTP_RESEND_END_POINT = "resend-code"
    var RESET_EMAIL = "email"
    var RESET_REQ_CODE = 6


    //RESET PASSWORD
    var RESET_MATCH_END_POINT = "forget-password"
    var RESET_MATCH_REQ_Code = 7
    var RESET_PASSWORD = "password"
    var RESET_COMFIRM_PASSWORD = "confirm_password"
    var RESET_CODE = "code"
    var RESET_PASS = "reset_password"
    var RESET_PASS_AGAIN = "reset_pass_again"

    //OTP RESEND
    var RESET_EMAIL_END_POINT = "forget-password/send-code"
    var MAIL_RESEND_REQ_CODE = 5

    //FAQ
    val FAQ_END_POINT = "faqs"

    //Property Search
    val PROPERTY_SEARCH_END_POINT ="properties"


    // Search api
    val KEYWORD = "keyword"



    //PROPERTY
    val ADD_PROPERTY_END_POINT = "dealer/property"
    val PROPERTY_DELETE_END_POINT = "dealer/property/"
    val PROPERTY_PRICE ="price"
    val DESCRIPTION = "description"
    val STATUS = "status"

    //END POINT
    val PROFILE_END_POINT = "dealer/profile"
    val PROFILE_CUSTOMER_END_POINT = "customer/profile"
    var STATEENDPOINT = "states"
    var SELL_TYPE_END_POINT = "dealer/property-attributes"
    var IMG_END_POINT = "dealer/property/image"
    var IMG_DEL_END_POINT = "dealer/property/image/delete"

    val GET_DEALER_ADD_END_POINT = "dealer/properties"
    // Contact us
    val CONTACT_US_END_POINT = "contact"
    val ADD_PROPERTY_FAV_END_POINT = "customer/wishlist"

    val GET_PROFILE_END_POINT = "dealer/profile"
    val GET_CUSTOMER_PROFILE_END_POINT = "customer/profile"


    val SELECTED_PROPERTY_END_POINT = "property/"
    val PROPERTY_TYPE_DETAIL = "property-attributes"


    var TOKEN="token"
    var USERDATA="userdata"
    var USERID="user_id"
    var USERMOBILE = ""

    //get STATE

    var STATE_REQ_CODE = 8

    //get City
    var CITY = "cities/"
    var CITY_REQ_CODE = 9

    //Get Sell Type - Property Type

    var SELL_REQ_CODE = 10

    //Property type
    var PROPERTY_REQ_CODE = 11


    //Property Hashmap code

    var ADD_PROPERTY_FIRST_REQ_CODE = 12
    var ADD_PROFILE_PROPERTY_RED_CODE = 13
    var ADD_PROFILE_PROPERTY_PRICING_CODE = 14
    val GET_DEALER_ADD_REQ_CODE = 15
    var IMG_DEL_REQ_CODE = 16
    var REQ_DEALERPEOPERTYDETAIL = 17
    var IMG_RED_CODE = 18
    val PROFILE_RED_CODE = 19
    val PROPERTY_DELETE_REQ_CODE = 20
    val FAQ_REQ_CODE = 21
    val CONTACT_US_REQ_CODE = 22
    val PROPERTY_SEARCH_REQ_CODE = 23
    val ADD_PROPERTY_FAV_REQ_CODE = 24
    val GET_PROFILE_REQ_CODE = 25
    val SELECTED_PROPERTY_REQ_CODE = 26
    val GET_FAV_REQ_CODE = 27
    val DEL_WISHLIST_REQ_CODE = 28
    val PROPERTY_TYPE_REQ_CODE = 29
    val PROFILE_CUSTOMER_RED_CODE = 30
    val GET_CUSTOMER_PROFILE_REQ_CODE = 31

    //customer REQ Code
     val CUSTOMER_HOME_REQ_CODE = 25

    var IMAGE = "image"
    var STEP = "step"
    var LIST_TYPE = "list_type"
    var PROPERTY_TYPE ="property_type"
    var STATE_ID = "state"
    var CITY_ID = "city"
    var ADDRESS = "address"
    var PINCODE = "pincode"

    var PROPERTY_ID: String = "id"
    var PHOTO_ID = "id" // they are same because of api has same name like in delete api id is used as photo id
    var PROPERTY_PROFILE_AREA: String = "area"
    var AMENITIES: String = "amenities"


    var PROPERTY_IMG_ID = "property_id"
    var FAV_PROPERTY_ID = "property_id"
    var DEALERPEOPERTYDETAIL = "dealer/property/"
     //Delete


    //USER TYPE
    var DEALER = 1
    var BUYER = 2

    var PROFILE_Log = 2

    var USER_ID = "id"
    var USER_NAME = "name"
    var USER_EMAIL = "email"
    val USER_MOBILE = "mobile"
    var USER_PASSWORD = "password"
    var USER_AGREE = "agree"

    //Parcelable model
    var PRACELABLE_KEY = "parcelable_key"


    //Customer
    val CUSTOMER = "Customer"
    val CUSTOMER_HOME_ADD_END_POINT = "properties"
    var isCustomer = false

    val SELL_Type  = "type"
    val AGE = "age"
    val POSSESSION = "possession"























    //convet string to json
//        var  model: UserJson?=null
//        var gson = Gson()
//        jsondata=preferenceManager.getString(Keys.USERDATA).toString()
//        model=gson.fromJson(jsondata, UserJson::class.java)
}