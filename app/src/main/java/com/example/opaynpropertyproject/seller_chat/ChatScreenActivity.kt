package com.example.opaynpropertyproject.seller_chat

import android.graphics.Bitmap
import android.os.Bundle
import android.util.Log
import android.view.View
import com.canhub.cropper.CropImageContract
import com.canhub.cropper.CropImageView
import com.canhub.cropper.options
import com.example.opaynpropertyproject.R
import com.example.opaynpropertyproject.adapter.ChatScreenAdapter
import com.example.opaynpropertyproject.api.Keys
import com.example.opaynpropertyproject.api_model.ChatFirebaseModel
import com.example.opaynpropertyproject.comman.BaseActivity
import com.example.opaynpropertyproject.comman.Utils
import com.example.opaynpropertyproject.home_activity.HomeActivity
import com.example.opaynpropertyproject.singleton.SingletonObject
import com.google.firebase.firestore.*
import com.google.firebase.firestore.EventListener
import com.google.firebase.ktx.Firebase
import com.google.firebase.storage.ktx.storage
import com.greetupp.extensions.isNotNull
import kotlinx.android.synthetic.main.chat_toolbar.view.*
import kotlinx.android.synthetic.main.fragment_chat_screen.*
import okhttp3.MultipartBody
import java.io.File
import java.util.*
import kotlin.collections.ArrayList
import android.app.ProgressDialog
import android.net.Uri
import android.view.LayoutInflater
import com.google.firebase.storage.StorageReference
import com.google.android.gms.tasks.OnCompleteListener
import com.vanniktech.emoji.EmojiPopup
import com.vanniktech.emoji.EmojiTextView

class ChatScreenActivity : BaseActivity(), View.OnClickListener {

    val fields = ArrayList<MultipartBody.Part>()

    var chatsModel: ChatFirebaseModel = ChatFirebaseModel()

    lateinit var messagesRef: CollectionReference
    private var mChatMessageEventListener: ListenerRegistration? = null
    val db = FirebaseFirestore.getInstance()
    val storage = Firebase.storage
    var chatList = ArrayList<ChatFirebaseModel>()
    var adapter: ChatScreenAdapter? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.fragment_chat_screen)
        supportActionBar?.hide()
        setChatAdapter()
        chatHeader()
        readMessage()
        onClicked()



        val popup =EmojiPopup.Builder.fromRootView(rootView).build(msg)
        emoji.setOnClickListener {
                popup.toggle()
        }


    }

    private fun chatHeader() {

    }

    private fun onClicked() {
        chat_screen_toolbar.chat_back_btn.setOnClickListener(this)
        send.setOnClickListener(this)
        rv_chat_screen.setOnClickListener(this)
        attachment.setOnClickListener(this)
        camera.setOnClickListener(this)
    }

    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.chat_back_btn -> {
                onBackPressed()
            }
            R.id.send -> {
                if (msg.isNotNull()) {
                    getEmojiTextView()
                    Keys.SENDER = true
//                    chatList.addAll(chatList)
//                    setChatAdapter()
                    adapter?.notifyDataSetChanged()
                    sendmessage("1", msg.text.toString())
                }
            }
            R.id.rv_chat_screen -> {
                Utils.hideKeyboard(this)
            }
            R.id.attachment -> {

            }
            R.id.camera -> {
                cropImage.launch(
                    options() {
                        setGuidelines(CropImageView.Guidelines.ON)
                        setOutputCompressQuality(50)
                        setOutputCompressFormat(Bitmap.CompressFormat.PNG)
                    }
                )
            }
        }
    }

    private fun getEmojiTextView(): EmojiTextView? {
        val tvEmoji = LayoutInflater
            .from(applicationContext)
            .inflate(R.layout.text_view_emoji, rv_chat_screen, false) as EmojiTextView
        tvEmoji.text = msg.text.toString()
        return tvEmoji
    }
    private fun sendmessage(type: String, msg2: String) {
        msg.setText("")
        chatsModel = ChatFirebaseModel()
        chatsModel.sender_id = Keys.SENDER_ID.toString()
        chatsModel.receiver_id = Keys.RECEIVER_ID.toString()
        chatsModel.sender_name = "Alex"
        chatsModel.receiver_name = "confusion"
        chatsModel.sender_image = ""
        chatsModel.receiver_image = ""
        chatsModel.type = type
        chatsModel.last_message = msg2

        db.collection("chatRooms").document("12").set(chatsModel)
        val instace = db.collection("chatRooms").document("12").collection("Messages").document()
            .set(chatsModel)
        instace.addOnSuccessListener { documentReference ->
        }

            .addOnFailureListener { e ->
                Log.w("TAGERROR", "Error adding document", e)
            }

//        //db listener
//        db.collection("chatRooms")
//            .addSnapshotListener { snapshots, e ->
//                if (e != null) {
//                    Log.w("TAG", "listen:error", e)
//                    return@addSnapshotListener
//                }
//
//                for (dc in snapshots!!.documentChanges) {
//                    when (dc.type) {
//
//                        DocumentChange.Type.ADDED -> Log.d("TAG", "Message Added: ${dc.document.data}")
//                        DocumentChange.Type.MODIFIED -> Log.d("TAG", "Modified city: ${dc.document.data}")
//                        DocumentChange.Type.REMOVED -> Log.d("TAG", "Removed city: ${dc.document.data}")
//                    }
//                }
//            }

    }

    private fun readMessage() {
        messagesRef = db.collection("chatRooms")
            .document("12")
            .collection("Messages")

        mChatMessageEventListener = messagesRef.orderBy("created_at").addSnapshotListener(
            EventListener { queryDocumentSnapshots, e ->
                if (e != null) {
                    return@EventListener
                }
                if (queryDocumentSnapshots != null) {

                    for (doc in queryDocumentSnapshots.documentChanges) {
                        when (doc.type) {
                            DocumentChange.Type.ADDED -> {
                                Log.e("hehehehehe", "ojdaodjapdad")
                                var model = ChatFirebaseModel()
                                model.sender_id = doc.document.data.get("sender_id").toString()
                                model.receiver_id = doc.document.data.get("receiver_id").toString()
                                model.sender_name = doc.document.data.get("sender_name").toString()
                                model.receiver_name = doc.document.data.get("receiver_name").toString()
                                model.sender_image = doc.document.data.get("sender_image").toString()
                                model.sender_image = ""
                                model.receiver_image = ""
                                model.type = doc.document.data.get("type").toString()
                                model.last_message = doc.document.data.get("last_message").toString()

                                //set name in chat screen
                                chat_screen_toolbar.chat_person_name.setText(doc.document.data.get("sender_name").toString())

                                chatList.add(model)
                                rv_chat_screen.smoothScrollToPosition(chatList.size - 1)
                                adapter?.notifyDataSetChanged()
                            }
                            DocumentChange.Type.MODIFIED -> {
                                Log.e("heheheh", "123")
                            }
                            DocumentChange.Type.REMOVED -> {
                                Log.e("heheheh", "1233")
                            }
                        }
                    }
                }
            })


        //read data
//        db.collection("chatRooms").document("12").collection("Messages").orderBy("created_at").get()
//            .addOnSuccessListener { result ->
//                for (doc in result.documentChanges)
//                {
//                    when(doc.type)
//                    {
//                        DocumentChange.Type.ADDED->{
//                             Log.e("chatScreenResultget", doc.document.data.get("sender_id").toString())
//
//                        }
//                    }
//
//
//                }
//                adapter?.notifyDataSetChanged()
//            }
//            .addOnFailureListener { expection ->
//                Log.e("READERROR", expection.toString())
//            }

    }


    private fun uploadImage(filePath: Uri) {
        if (filePath != null) {
            // Code for showing progressDialog while uploading

            // Code for showing progressDialog while uploading
            val progressDialog = ProgressDialog(this)
            progressDialog.setTitle("Uploading...")
            progressDialog.show()

            // Defining the child of storageReference

            // Defining the child of storageReference
            val ref: StorageReference = storage.reference
                .child(
                    "images/"
                            + UUID.randomUUID().toString()
                )
            // adding listeners on upload
            // or failure of image
            ref.putFile(filePath)
                .addOnSuccessListener {

                    progressDialog.dismiss()

                    ref.getDownloadUrl()
                        .addOnCompleteListener(OnCompleteListener<Uri?> { task ->
                          var   profileimageurl = task.result.toString()
                            sendmessage("2",profileimageurl)
                        })
                }

                .addOnFailureListener {
                    Log.e("sucesssss","12222222")
                    Utils.customSnakebar(send, "Unable to send image$it")
                }



                .addOnProgressListener {
                    Log.e("sucesssss","12hfhfghfh3")
                    val progress: Double = (100.0 * it.getBytesTransferred()/ it.getTotalByteCount())
                    progressDialog.setMessage(
                        "Uploaded " + progress.toInt() + "%"
                    )
                }

        }
    }


    private fun setChatAdapter() {
        adapter = ChatScreenAdapter(chatList, this)
        rv_chat_screen.adapter = adapter
    }

    private val cropImage = registerForActivityResult(CropImageContract()) { result ->
        if (result.isSuccessful) {

            // use the returned uri
            val uriContent = result.uriContent
            val uriFilePath = result.getUriFilePath(this) // optional usage
            Log.e("rr", uriContent.toString())
            if (uriContent != null) {
                if (uriFilePath != null) {
                    uploadImage(uriContent)



                }
            }

//            image.setImageURI(uriContent)
//            var id = "4" // herer passs the property id
//            var file = File(uriFilePath)
//            serviceViewModel.getMultiPart(Keys.IMAGE,file!!)?.let { fields.add(it) }
//            serviceViewModel.getMultiPart(Keys.PROPERTY_IMG_ID,id!!)?.let { fields.add(it) }
//
//            serviceViewModel.multipartservice(Keys.IMG_END_POINT,requireContext(),fields,Keys.IMG_RED_CODE,true,token,true)
        } else {
            // an error occurred
            val exception = result.error
        }
    }


}