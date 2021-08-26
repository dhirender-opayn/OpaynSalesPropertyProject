
import android.content.Context
import android.util.Log
import com.example.opaynpropertyproject.Api.Keys
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.MultipartBody
import okhttp3.RequestBody.Companion.asRequestBody
import okhttp3.ResponseBody
import org.json.JSONObject
import retrofit2.Call
import retrofit2.Response
import java.io.File
import java.util.*
import kotlin.collections.ArrayList
import kotlin.collections.HashMap

class
ServiceViewModel : Observable()
{
    var  reuestCode:Int=0

    var  responsestring=""

    fun multipartservice(url:String, context: Context, fields: ArrayList<MultipartBody.Part>, reuestcode:Int, isheader: Boolean, token:String, isprogress:Boolean)
    {

        val apiService = WebapiInterface.create(context,isheader,token)
        val response  = apiService.mutipartservice(url,fields)
        if (isprogress)
        {
            ProgressDialogs.showDialog(context)
        }

        response.enqueue(object : retrofit2.Callback<ResponseBody>
        {
            override fun onFailure(call: Call<ResponseBody>, t: Throwable?)
            {
                ProgressDialogs.dismissProgressDialog( )

            }

            override  fun onResponse(call: Call<ResponseBody>, response: Response<ResponseBody>?)
            {

                if (response!=null)
                {
                    ProgressDialogs.dismissProgressDialog( )
                    when(response.code())
                    {
                        Keys.SUCESSCODE->
                        {
                            if (response.body()!=null)
                            {
                                reuestCode=reuestcode
                                var  jsonObject= JSONObject(response.body()!!.string().toString())
                                responsestring=jsonObject.toString()
                                ProgressDialogs.checklog("responsestring",responsestring)
                                setChanged()
                                notifyObservers()
                            }

                        }
                        Keys.UNAUTHRISECODE->{

                            ProgressDialogs.showToast(context,response.message())

                        }
                        Keys.SERVERERROR->{
                            reuestCode=Keys.SERVERERROR
//                            setChanged()
//                            notifyObservers()
                            ProgressDialogs.showToast(context,response.message())


                        }
                        Keys.BACKENDERROR->{
//                            ProgressDialogs.showToast(context,response.message())
                            var  jsonObject= JSONObject(response.errorBody()!!.string().toString())
//
                            ProgressDialogs.showToast(context,jsonObject.getString("message"))

                        }
                    }

                }
                else{

                    ProgressDialogs.dismissProgressDialog( )
                }
            }

        })

    }


    fun getservice(url:String,context: Context, reuestcode:Int, isheader: Boolean,token:String,isprogress:Boolean)
    {

        val apiService = WebapiInterface.create(context,isheader,token)

        val response  = apiService.getservice(url)
        if (isprogress)
        {
            ProgressDialogs.showDialog(context)
        }
        response.enqueue(object : retrofit2.Callback<ResponseBody>
        {
            override fun onFailure(call: Call<ResponseBody>, t: Throwable?)
            {
                ProgressDialogs.dismissProgressDialog( )
                ProgressDialogs.showToast(context,t?.message.toString())

            }

            override  fun onResponse(call: Call<ResponseBody>, response: Response<ResponseBody>?)
            {

                if (response!=null)
                {
                    ProgressDialogs.dismissProgressDialog( )
                    when(response.code())
                    {
                        Keys.SUCESSCODE->
                        {
                            if (response.body()!=null)
                            {
                                reuestCode=reuestcode
                                var  jsonObject= JSONObject(response.body()!!.string().toString())
                                responsestring=jsonObject.toString()
                                setChanged()
                                notifyObservers()
                            }

                        }
                        Keys.UNAUTHRISECODE->{

                            ProgressDialogs.showToast(context,response.message())

                        }
                        Keys.SERVERERROR->{
                            reuestCode= Keys.SERVERERROR
//                            setChanged()
//                            notifyObservers()
                            ProgressDialogs.showToast(context,response.message())


                        }
                        Keys.BACKENDERROR->{
                            ProgressDialogs.showToast(context,response.message())
                            //   ProgressDialogs.showToast(context,"Internal server Error")

                        }
                    }


                }
                else{

                    ProgressDialogs.dismissProgressDialog( )
                }
            }

        })

    }


    fun postservice(url:String,context: Context,map:HashMap<String,Any>, reuestcode:Int, isheader: Boolean,token:String,isprogress:Boolean)
    {
        val apiService = WebapiInterface.create(context,isheader,token)
        val response  = apiService.commonpost(Keys.BASEURL+url,map)
        if (isprogress)
        {
            ProgressDialogs.showDialog(context)
        }
        response.enqueue(object : retrofit2.Callback<ResponseBody>
        {
            override fun onFailure(call: Call<ResponseBody>, t: Throwable?)
            {
                ProgressDialogs.showToast(context,t?.message.toString())
                ProgressDialogs.dismissProgressDialog( )
            }

            override  fun onResponse(call: Call<ResponseBody>, response: Response<ResponseBody>?)
            {

                if (response!=null)
                {
                    ProgressDialogs.dismissProgressDialog( )
                    when(response.code())
                    {
                        Keys.SUCESSCODE->
                        {
                            if (response.body()!=null)
                            {
                                var  jsonObject= JSONObject(response.body()!!.string().toString())
                                responsestring=jsonObject.toString()
                                reuestCode=reuestcode
                                setChanged()
                                notifyObservers()

                            }

                        }
                        Keys.UNAUTHRISECODE->{
                             ProgressDialogs.showToast(context,response.message())

                        }
                        Keys.SERVERERROR->{
                            reuestCode= Keys.SERVERERROR
//                            setChanged()
//                            notifyObservers()
                            ProgressDialogs.showToast(context,response.message())


                        }
                        Keys.BACKENDERROR->{
                            ProgressDialogs.showToast(context,response.message())
                            // ProgressDialogs.showToast(context,"Internal server Error")

                        }
                    }


                }
                else{


                    ProgressDialogs.dismissProgressDialog( )
                }
            }

        })

    }


    fun getMultiPart(key: String?, file: File): MultipartBody.Part?
    {
        val requestFile = file.asRequestBody("multipart/form-data".toMediaTypeOrNull())
        return MultipartBody.Part.createFormData(key!!, file.name, requestFile)
    }

    fun getMultiPart(key: String?, file: String?): MultipartBody.Part?
    {
        return MultipartBody.Part.createFormData(key!!, file!!)
    }

}