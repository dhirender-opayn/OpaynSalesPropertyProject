package com.example.opaynpropertyproject.api_model

import com.google.firebase.firestore.ServerTimestamp
import java.util.*

class ChatFirebaseModel{

    var last_message: String =""
    var receiver_id: String = ""
    var receiver_image: String = ""
    var receiver_name: String =" "
    var sender_id: String = ""
    var sender_image: String= ""
    var sender_name: String= ""
    var type: String= ""
    @ServerTimestamp
    var created_at: Date? = null

}

