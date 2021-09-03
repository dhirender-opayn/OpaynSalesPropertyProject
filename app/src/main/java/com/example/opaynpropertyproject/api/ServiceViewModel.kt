import android.content.Context
import android.util.Log
import com.example.opaynpropertyproject.api.ApiResponse
import com.example.opaynpropertyproject.api.Keys
import com.example.opaynpropertyproject.api_model.ErrorModel
import com.google.gson.Gson
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.MultipartBody
import okhttp3.RequestBody.Companion.asRequestBody
import okhttp3.ResponseBody
import org.json.JSONObject
import retrofit2.Call
import retrofit2.Response
import java.io.File
import kotlin.collections.ArrayList
import kotlin.collections.HashMap

class ServiceViewModel {
    var reuestCode: Int = 0

    var responsestring = ""
    var user_id = ""

    fun multipartservice(
        url: String,
        context: Context,
        fields: ArrayList<MultipartBody.Part>,
        reuestcode: Int,
        isheader: Boolean,
        token: String,
        isprogress: Boolean
    ) {

        val apiService = WebapiInterface.create(context, isheader, token)
        val response = apiService.mutipartservice(url, fields)
        if (isprogress) {
            ProgressDialogs.showDialog(context)
        }

        response.enqueue(object : retrofit2.Callback<ResponseBody> {
            override fun onFailure(call: Call<ResponseBody>, t: Throwable?) {
                ProgressDialogs.dismissProgressDialog()

            }

            override fun onResponse(call: Call<ResponseBody>, response: Response<ResponseBody>?) {

                if (response != null) {
                    ProgressDialogs.dismissProgressDialog()
                    when (response.code()) {
                        Keys.SUCESSCODE -> {
                            if (response.body() != null) {
                                reuestCode = reuestcode
                                var jsonObject = JSONObject(response.body()!!.string().toString())
                                responsestring = jsonObject.toString()

                                ProgressDialogs.checklog("responsestring", responsestring)

                            }

                        }
                        Keys.UNAUTHRISECODE -> {

                            ProgressDialogs.showToast(context, response.message())

                        }
                        Keys.SERVERERROR -> {
                            reuestCode = Keys.SERVERERROR
//                            setChanged()
//                            notifyObservers()
                            ProgressDialogs.showToast(context, response.message())


                        }
                        Keys.BACKENDERROR -> {
//                            ProgressDialogs.showToast(context,response.message())

                            var jsonObject = JSONObject(response.errorBody()!!.string().toString())
                            reuestCode = Keys.BACKENDERROR


//
                            //   ProgressDialogs.showToast(context,jsonObject.getString("message"))

//                            val message = jsonObject.getString("message")

                        }
                    }

                } else {

                    ProgressDialogs.dismissProgressDialog()
                }
            }

        })

    }


    fun getservice(
        url: String,
        context: Context,
        reuestcode: Int,
        isheader: Boolean,
        token: String,
        isprogress: Boolean,
        responselistner: ApiResponse
    ) {

        val apiService = WebapiInterface.create(context, isheader, token)

        val response = apiService.getservice(url)
        if (isprogress) {
            ProgressDialogs.showDialog(context)
        }
        response.enqueue(object : retrofit2.Callback<ResponseBody> {
            override fun onFailure(call: Call<ResponseBody>, t: Throwable?) {
                ProgressDialogs.dismissProgressDialog()
                ProgressDialogs.showToast(context, t?.message.toString())

            }

            override fun onResponse(call: Call<ResponseBody>, response: Response<ResponseBody>?) {

                if (response != null) {
                    ProgressDialogs.dismissProgressDialog()
                    when (response.code()) {
                        Keys.SUCESSCODE -> {
                            if (response.body() != null) {
                                reuestCode = reuestcode
                                var jsonObject = JSONObject(response.body()!!.string().toString())
                                responsestring = jsonObject.toString()
                                responselistner.onResponse(reuestcode,responsestring)


                            }

                        }
                        Keys.UNAUTHRISECODE -> {

                            ProgressDialogs.showToast(context, response.message())

                        }
                        Keys.SERVERERROR -> {
                            reuestCode = Keys.SERVERERROR
//                            setChanged()
//                            notifyObservers()
                            ProgressDialogs.showToast(context, response.message())


                        }
                        Keys.BACKENDERROR -> {
                            reuestCode = Keys.BACKENDERROR
                            ProgressDialogs.showToast(context, response.message())
                            //   ProgressDialogs.showToast(context,"Internal server Error")

                        }
                    }


                } else {

                    ProgressDialogs.dismissProgressDialog()
                }
            }

        })

    }


    fun postservice(
        url: String,
        context: Context,
        map: HashMap<String, Any>,
        reuestcode: Int,
        isheader: Boolean,
        token: String,
        isprogress: Boolean,
        responselistner: ApiResponse
    ) {
        val apiService = WebapiInterface.create(context, isheader, token)
        val response = apiService.commonpost(Keys.BASEURL + url, map)
        if (isprogress) {
            ProgressDialogs.showDialog(context)
        }
        response.enqueue(object : retrofit2.Callback<ResponseBody> {
            override fun onFailure(call: Call<ResponseBody>, t: Throwable?) {
                ProgressDialogs.showToast(context, t?.message.toString())
                ProgressDialogs.dismissProgressDialog()
            }

            override fun onResponse(call: Call<ResponseBody>, response: Response<ResponseBody>?) {
                if (response != null) {
                    ProgressDialogs.dismissProgressDialog()
                    when (response.code()) {

                        Keys.SUCESSCODE -> {
                            if (response.body() != null) {
                                val jsonObject = JSONObject(response.body()!!.string().toString())
                                responsestring = jsonObject.toString()
                                reuestCode = reuestcode
                                responselistner.onResponse(reuestCode, responsestring)

                                Log.e("signup", "Signup successfully")
                            }

                        }
                        Keys.UNAUTHRISECODE -> {


                            ProgressDialogs.showToast(context, response.message())
                        }
                        Keys.SERVERERROR -> {
                            reuestCode = Keys.SERVERERROR

                            ProgressDialogs.showToast(context, response.message())
                        }
                        Keys.BACKENDERROR -> {
                            reuestCode = Keys.BACKENDERROR
                            parseError(response, context, responselistner)
                            // ProgressDialogs.showToast(context,"Internal server Error")
                        }
                    }
                } else {
                    ProgressDialogs.dismissProgressDialog()
                }
            }
        })

    }

    fun parseError(response: Response<*>, context: Context, responselistner: ApiResponse) {
        val jsonObject = JSONObject(response.errorBody()!!.string().toString())
        responsestring = jsonObject.toString()
        responselistner.onResponse(reuestCode, responsestring)

//        var errorModel: ErrorModel? = null
//        var gson = Gson()
//        errorModel = gson.fromJson(responsestring, ErrorModel::class.java)
//        if (errorModel.message.length > 0) {
//            ProgressDialogs.showToast(context, errorModel.message.toString())
//        } else {
//            Log.e("message_Error", "Error message is empty")
//        }
    }


    fun getMultiPart(key: String?, file: File): MultipartBody.Part? {
        val requestFile = file.asRequestBody("multipart/form-data".toMediaTypeOrNull())
        return MultipartBody.Part.createFormData(key!!, file.name, requestFile)
    }

    fun getMultiPart(key: String?, file: String?): MultipartBody.Part? {
        return MultipartBody.Part.createFormData(key!!, file!!)
    }


}