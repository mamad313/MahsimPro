package com.example.mahsimecommerce.fragments.Profile

import android.app.Activity
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.mahsimecommerce.Api
import com.example.mahsimecommerce.R
import com.example.mahsimecommerce.RetrofitClient
import com.example.mahsimecommerce.helper.Setting
import com.example.mahsimecommerce.models.ResponseOrders
import com.example.mahsimecommerce.models.ResultsProfileData
import com.example.mahsimecommerce.models.ResultsResult
import kotlinx.android.synthetic.main.fragment_profile_info.view.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.io.ByteArrayOutputStream

var bitmapArray: ByteArray? = null
class ProfileInfo : Fragment() {
    val REQ_CAMERA_PERM = 1001
    val REQ_CODE_TAKE_PHOTO = 1005
    val REQ_CODE_GALLERY = 1006

    lateinit var progressBarVar: ProgressBar
    lateinit var imgProfile: ImageView
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        container?.removeAllViews()

        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_profile_info, container, false)


        imgProfile = view.findViewById(R.id.imgProfile)
        progressBarVar = view.findViewById(R.id.progressBar)

        view.btnPickGallery.setOnClickListener {
            val intent: Intent = Intent()
            intent.action = Intent.ACTION_GET_CONTENT
            intent.type = "image/*"
            startActivityForResult(Intent.createChooser(intent,"Select image"), REQ_CODE_GALLERY)
        }
        val service: Api = RetrofitClient.instance
        val call: Call<ResultsResult> = service.getProfileGet("{\"userId\":\"${Setting(context as Activity).getLoginInfo().toString()}\"}")
        Log.d("Setting","Setting -> ${Setting(context as Activity).getLoginInfo().toString()}")

        call.enqueue(
            object : Callback<ResultsResult> {
                override fun onFailure(call: Call<ResultsResult>, t: Throwable) {
                    Log.d("onFailure", "onFailure")
                }
                override fun onResponse(call: Call<ResultsResult>, response: Response<ResultsResult>) {
                    val list = response.body()!!.results
                    Log.d("onResponse Profile","onResponse Profile -> $list")
                    if(list.isNotEmpty()){
                    val user: ResultsProfileData = ResultsProfileData(list.toString())
                    view.txtAddress.setText(list.last().adress)
                    view.txtPhone.setText(list.last().phone)

                    val picArr : ByteArray? = list.last().pic
                    val btm: Bitmap? = picArr?.let {
                        BitmapFactory.decodeByteArray(picArr,0,
                            it.size)
                    }
                    imgProfile.setImageBitmap(btm)
                    }
                    Log.d("onResponse", "onResponse Profile-> $response ")
                    Log.d("onResponse", "onResponse Profile-> $list ")
                }
            }
        )

        view.btnSave.setOnClickListener {

            val userProfileData: ResultsProfileData = ResultsProfileData()
            userProfileData.adress=(view.txtAddress.text.toString())
            userProfileData.phone=(view.txtPhone.text.toString())
            userProfileData.pic= bitmapArray
            userProfileData.userId= Setting(context as Activity).getLoginInfo().toString()

            val call: Call<ResponseOrders> = service.getProfile(userProfileData)
            call.enqueue(
                object : Callback<ResponseOrders> {
                    override fun onFailure(call: Call<ResponseOrders>, t: Throwable) {
                        Log.d("onFailure", "onFailure")
                    }
                    override fun onResponse(call: Call<ResponseOrders>, response: Response<ResponseOrders>) {
                        Toast.makeText(context as Activity, getString(R.string.profileSaved),
                            Toast.LENGTH_LONG).show()
                        Log.d("onResponse", "onResponse ")
                    }
                }
            )
        }
        return view
    }

    fun takePhoto(){
        val intentCamera: Intent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
//        val packageManager = requireActivity().packageManager // check
        if(intentCamera.resolveActivity(requireActivity().packageManager)!=null)
            startActivityForResult(intentCamera, REQ_CODE_TAKE_PHOTO)
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
            takePhoto()
        }
    }

    fun convertBmpToArray(bitmap: Bitmap){
        val stream: ByteArrayOutputStream = ByteArrayOutputStream()
        bitmap.compress(Bitmap.CompressFormat.JPEG, 100, stream)
        bitmapArray = stream.toByteArray()
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if(requestCode == REQ_CODE_TAKE_PHOTO && resultCode == AppCompatActivity.RESULT_OK){
            val bitmap: Bitmap = data?.extras?.get("data") as Bitmap
            imgProfile.setImageBitmap(bitmap)

            convertBmpToArray(bitmap)
        }else if (requestCode == REQ_CODE_GALLERY && resultCode == AppCompatActivity.RESULT_OK){
            val selectedImage: Uri? = data?.data
            var bitmap:Bitmap?=null
            bitmap = MediaStore.Images.Media.getBitmap(requireActivity().contentResolver, selectedImage) // check

            if(bitmap!=null) {
                convertBmpToArray(bitmap)
                imgProfile.setImageBitmap(bitmap)
            }
        }
    }
}