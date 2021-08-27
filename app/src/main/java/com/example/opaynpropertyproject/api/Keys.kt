package com.example.opaynpropertyproject.api

object Keys {
    const val BASEURL = "http://3.22.185.42/realestate-api/public/api/"
    val SUCESSCODE = 200
    val UNAUTHRISECODE =401
    val SERVERERROR =201
    val BACKENDERROR =412

//USER TYPE
    var DEALER = 1
    var BUYER = 2
    var PROFILE_Log = 2

    //signup End point
    var signupEndPoint="signup"
    var signup_step_One = 1 // login signup step is called 1 and assign profile is step 2
    var Req_log=1
    val signup_name = "name"
    val signup_email = "email"
    val signup_password = "password"
    val signup_agree = "agree"
    val signup_Step = "step"
    // signup step 2 --- buyer or seller
    var SIGNUP_ID = "id"
    var SIGNUP_USER_TYPE = "user_type"


    //Login End points
    var loginEndPoint = "login"
    val login_email = "email"
    val login_password = "password"
    var login_log = 1



    var USER_ID = "user_id"



    //convet string to json
//        var  model: UserJson?=null
//        var gson = Gson()
//        jsondata=preferenceManager.getString(Keys.USERDATA).toString()
//        model=gson.fromJson(jsondata, UserJson::class.java)
}