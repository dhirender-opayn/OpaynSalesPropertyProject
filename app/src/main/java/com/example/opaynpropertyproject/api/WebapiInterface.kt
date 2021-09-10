
import android.content.Context
import android.util.Log
import com.example.opaynpropertyproject.api.Keys
import com.example.opaynpropertyproject.api_model.SellPropertyModel
import com.example.opaynpropertyproject.comman.Utils
import okhttp3.*
import okhttp3.logging.HttpLoggingInterceptor
import okio.IOException
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.*
import java.net.SocketException
import java.net.SocketTimeoutException
import java.util.concurrent.TimeUnit

interface WebapiInterface
{




    @FormUrlEncoded
    @POST()
    fun commonpost(@Url url: String,@FieldMap map:HashMap<String,Any>) :Call<ResponseBody>

    @Multipart
    @POST()
    fun mutipartservice(@Url url: String,@Part fields: ArrayList<MultipartBody.Part>): Call<ResponseBody>
    @GET()
    fun getservice(@Url url: String): Call<ResponseBody>

    @POST()
    fun commonpostRequestBody(@Url url: String,@Body map: RequestBody): Call<ResponseBody>?

    companion object Factory
    {
        val BASE_URL = Keys.BASEURL
        fun create(context: Context, isheader: Boolean, token:String) : WebapiInterface
        {
            val interceptor = HttpLoggingInterceptor()
            interceptor.level = HttpLoggingInterceptor.Level.BODY
            val httpClient = OkHttpClient.Builder()
            httpClient.connectTimeout(30, TimeUnit.SECONDS)
            httpClient.writeTimeout(30,TimeUnit.SECONDS)
            httpClient.readTimeout(30,TimeUnit.SECONDS)
            httpClient.addInterceptor(interceptor)
            if(isheader)
            {
                Log.e("token",token)
                httpClient.addInterceptor(object: Interceptor
                {
                    @Throws(IOException::class)
                    override  fun intercept(chain: Interceptor.Chain): okhttp3.Response
                    {
                        var builders=chain.request()
                        try {
                            builders = chain.request().newBuilder()
                                .header("Authorization", "Bearer "+token)
                                .header("country", "61")
                                .header("Accept", "application/json")
                                .build()

                        }
                        catch (exception:Exception)
                        {
                            when (exception) {
                                is SocketTimeoutException -> {
                                    //message.sendToTarget()
                                }
                                is SocketException -> {
                                    //message.sendToTarget()
                                }
                                is IOException -> {
                                    // message.sendToTarget()
                                }
                                else -> exception.stackTrace
                            }
                        }

                        return chain.proceed(builders)
                    }
                })

            }
            else{
                httpClient.addInterceptor(object: Interceptor
                {
                    @Throws(IOException::class)

                    override  fun intercept(chain: Interceptor.Chain): okhttp3.Response
                    {
                        var builders=chain.request()
                        try {
                            builders = chain.request().newBuilder()
                                .header("Accept", "application/json")
                                .header("country", "61")
                                .build()

                        }
                        catch (exception:Exception)
                        {
                            when (exception) {
                                is SocketTimeoutException -> {
                                    //message.sendToTarget()
                                }
                                is SocketException -> {
                                    //message.sendToTarget()
                                }
                                is IOException -> {
                                    // message.sendToTarget()
                                }
                                else -> exception.stackTrace
                            }
                        }

                        return chain.proceed(builders)
                    }
                })
            }

            val retrofit= Retrofit.Builder()
                .baseUrl( Keys.BASEURL)
                .client(httpClient.build())
                .addConverterFactory(GsonConverterFactory.create())
                .build();
            return retrofit.create(WebapiInterface::class.java)

        }

    }

}