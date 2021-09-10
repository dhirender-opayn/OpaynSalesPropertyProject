package com.example.opaynpropertyproject.api

object Keys {



    const val BASEURL = "http://c560-180-188-237-46.ngrok.io/realestate-api/public/api/"
    val ADS_DATA = "AdsData"
    val SUCESSCODE = 200
    val UNAUTHRISECODE = 401
    val SERVERERROR = 201
    val BACKENDERROR = 412

    //get STATE
    var STATEENDPOINT = "states"
    var STATE_REQ_CODE = 8

    //get City
    var CITY = "cities/"
    var CITY_REQ_CODE = 9

    //Get Sell Type - Property Type
    var SELL_TYPE_END_POINT = "dealer/property-attributes"
    var SELL_REQ_CODE = 10

    //Property type
    var PROPERTY_REQ_CODE = 11


    //Property Hashmap code

    var ADD_PROPERTY_FIRST_REQ_CODE = 12
    var ADD_PROFILE_PROPERTY_RED_CODE = 13
    var IMG_RED_CODE = 14
    var IMAGE = "image"
    var STEP = "step"
    var LIST_TYPE = "list_type"
    var PROPERTY_TYPE ="property_type"
    var STATE_ID = "state"
    var CITY_ID = "city"
    var ADDRESS = "address"
    var PINCODE = "pincode"

    var PROPERTY_ID: String = "id"
    var PROPERTY_PROFILE_AREA: String = "area"
    var AMENITIES: String = "amenities"

    var IMG_END_POINT = "dealer/property/image"
    var PROPERTY_IMG_ID = "property_id"






    //USER TYPE
    var DEALER = 1
    var BUYER = 2
    var PROFILE_Log = 2

    var USER_ID = "id"
    var USER_NAME = "user_name"
    var USER_EMAIL = "email"
    var USER_PASSWORD = "password"
    var USER_AGREE = "agree"

    //Parcelable model
    var PRACELABLE_KEY = "parcelable_key"


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

    //OTP RESEND
    var USER_OTP_RESEND_END_POINT = "resend-code"
    var MAIL_RESEND_REQ_CODE = 5


    //Login End points
    var loginEndPoint = "login"
    val login_email = "email"
    val login_password = "password"
    var login_log = 1

    //RESET
    var RESET_EMAIL = "email"
    var RESET_EMAIL_END_POINT = "forget-password/send-code"
    var RESET_REQ_CODE = 6

    //RESET PASSWORD
    var RESET_MATCH_END_POINT = "forget-password"
    var RESET_MATCH_REQ_Code = 7
    var RESET_PASSWORD = "password"
    var RESET_COMFIRM_PASSWORD = "confirm_password"
    var RESET_CODE = "code"
    var RESET_PASS = "reset_password"
    var RESET_PASS_AGAIN = "reset_pass_again"

    //PROPERTY
    val ADD_PROPERTY_END_POINT = "dealer/property"


    //convet string to json
//        var  model: UserJson?=null
//        var gson = Gson()
//        jsondata=preferenceManager.getString(Keys.USERDATA).toString()
//        model=gson.fromJson(jsondata, UserJson::class.java)
}