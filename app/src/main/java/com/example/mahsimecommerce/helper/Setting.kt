package com.example.mahsimecommerce.helper

import android.app.Activity
import android.content.Context
import android.content.SharedPreferences
import com.example.mahsimecommerce.fragments.Basket.BasketFragment

public class Setting(private var context: Context) {
    var mypref: SharedPreferences =context.getSharedPreferences("myApp", Activity.MODE_PRIVATE)

    public fun saveLogin(userId:String, username:String, email: String){
        val editor: SharedPreferences.Editor =  mypref.edit()
        editor.putString("userId", userId)
        editor.putString("username", username)
        editor.putString("email", email)
        editor.apply()
    }

    public fun getLoginInfo(): String? {
        return mypref.getString("userId",null)
    }
    public fun getUserName(): String? {
        return mypref.getString("userName",null)
    }
    public fun getEmail(): String? {
        return mypref.getString("email",null)
    }
    public fun Logout(){
        val editor:SharedPreferences.Editor = mypref.edit()
        editor.remove("userId")
        editor.remove("username")
        editor.remove("email")
        editor.apply()
    }

}