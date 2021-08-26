
import android.app.Dialog
import android.content.Context
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.net.ConnectivityManager
import android.util.Log
import android.view.Gravity
import android.widget.Toast
import com.example.opaynpropertyproject.R

object ProgressDialogs
{
    private var mProgressDialog: Dialog? = null
    fun showDialog(context: Context) {
        try {
            if (mProgressDialog != null)
                mProgressDialog?.dismiss()
            mProgressDialog = Dialog(context)
            mProgressDialog?.setCancelable(false)
            mProgressDialog?.show()
            mProgressDialog?.setContentView(R.layout.progresslayout)
            mProgressDialog?.window?.setLayout(
                context.resources.displayMetrics.widthPixels,
                context.resources.displayMetrics.heightPixels
            )
            mProgressDialog?.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
            mProgressDialog?.show()
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }
    fun dismissProgressDialog() {
        try {
            if (mProgressDialog != null)
                mProgressDialog?.dismiss()
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }
    fun showToast(context: Context,msg:String)
    {
        val myToast = Toast.makeText(context,msg,Toast.LENGTH_LONG)
        myToast.setGravity(Gravity.CENTER,0,0)
        myToast.show()

    }
    fun checklog(title:String,msg: String)
    {
        Log.e(title+"==>",msg)
    }
    fun isNetworkAvailable(context: Context): Boolean
    {

        val manager = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val networkInfo = manager.activeNetworkInfo

        var isAvailable = false
        if (networkInfo != null && networkInfo.isConnected)
        {
            isAvailable = true

        }
        return isAvailable
    }

}