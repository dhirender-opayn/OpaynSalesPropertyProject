package com.example.opaynpropertyproject.api

object Keys {
    const val BASEURL = "http://93d3-180-188-237-46.ngrok.io/realestate-api/public/api/"
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


    //convet string to json
//        var  model: UserJson?=null
//        var gson = Gson()
//        jsondata=preferenceManager.getString(Keys.USERDATA).toString()
//        model=gson.fromJson(jsondata, UserJson::class.java)
}