package com.example.mahsimecommerce

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Html
import android.util.Log
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.drawerlayout.widget.DrawerLayout
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import com.example.mahsimecommerce.helper.Setting
import com.example.mahsimecommerce.models.ChildProduct
import com.example.mahsimecommerce.models.FatherProduct
import com.example.mahsimecommerce.models.ResultsProfileData
import com.example.mahsimecommerce.models.ResultsResult
import com.google.android.material.bottomnavigation.BottomNavigationMenuView
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.navigation.NavigationView
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.badge_text.view.*
import kotlinx.android.synthetic.main.fragment_profile_info.view.*
import kotlinx.android.synthetic.main.nav_header.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class MainActivity : AppCompatActivity() {

    private lateinit var notificationBadges: View
    lateinit var toggle: ActionBarDrawerToggle

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val drawerLayout : DrawerLayout = findViewById(R.id.drawerLayout)
        val navView : NavigationView = findViewById(R.id.navView)
        toggle = ActionBarDrawerToggle(this, drawerLayout, R.string.open, R.string.close)
        drawerLayout.addDrawerListener(toggle)
        navView.bringToFront()

        toggle.syncState()

        val service: Api = RetrofitClient.instance
        val call: Call<ResultsResult> = service.getProfileGet("{\"userId\":\"${Setting(this).getLoginInfo().toString()}\"}")
        Log.d("Setting","Setting -> ${Setting(this).getLoginInfo().toString()}")

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

                        val picArr : ByteArray? = list.last().pic
                        val btm: Bitmap? = picArr?.let {
                            BitmapFactory.decodeByteArray(picArr,0,
                                it.size)
                        }
                        profilePhoto.setImageBitmap(btm)
                        val setting = Setting(this@MainActivity)

                        usernameId.text = setting.getUserName()
                        emailId.text = setting.getEmail()
                    }
                    Log.d("onResponse", "onResponse Profile-> $response ")
                    Log.d("onResponse", "onResponse Profile-> $list ")
                }
            }
        )


        navView.setNavigationItemSelectedListener {

            when(it.itemId){
                R.id.logoutId -> Setting(this).Logout()
//                finish()//checkkkkkk
//                R.id.ordersId -> updateBadgeCount(count++)


            }
            true
        }


        supportActionBar?.title = Html.fromHtml("<font color=\"black\">"+ getString(R.string.app_name)+ "</font>")


        supportActionBar?.setDisplayHomeAsUpEnabled(true)



        // Bottom Navigation
        val bottomNavigationView = findViewById<BottomNavigationView>(R.id.bottom_navigation)
        val navHostFragment = supportFragmentManager.findFragmentById(R.id.fragment) as NavHostFragment
        val navController = navHostFragment.navController


        bottomNavigationView.setupWithNavController(navController)

    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (toggle.onOptionsItemSelected(item)){
            return true
        }

        return super.onOptionsItemSelected(item)
    }

    fun updateBadgeCount(count: Int = 0){

        val itemView: BottomNavigationView? = bottom_navigation.getChildAt(1) as? BottomNavigationView

        notificationBadges = LayoutInflater.from(this)
            .inflate(R.layout.badge_text, itemView, true)

        notificationBadges?.notification_badge?.text = count.toString()

        bottom_navigation?.addView(notificationBadges)
    }

}