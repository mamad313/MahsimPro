package com.example.mahsimecommerce.fragments.Basket

import android.app.Activity
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.mahsimecommerce.Api
import com.example.mahsimecommerce.R
import com.example.mahsimecommerce.RetrofitClient
import com.example.mahsimecommerce.helper.Setting
import com.example.mahsimecommerce.models.*
import com.example.mahsimecommerce.viewmodel.RoomViewModel
import kotlinx.android.synthetic.main.basket_item.*
import kotlinx.android.synthetic.main.basket_item.view.*
import kotlinx.android.synthetic.main.fragment_basket.view.*
import kotlinx.android.synthetic.main.nav_header.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class BasketFragment : Fragment() {

//    private val args by navArgs<BasketFragmentArgs>()
    private lateinit var mUserViewModel: RoomViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_basket, container, false)

        val adapter = BasketAdapter(requireContext())
        val recyclerView = view.recycler_view_basket
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(requireContext())


        mUserViewModel = ViewModelProvider(this).get(RoomViewModel::class.java)
        mUserViewModel.readALlData.observe(viewLifecycleOwner, Observer { user ->
            adapter.setData(user)

//
//            Log.d("userAdapter","userAdapter -> ${user.last().price}")
//            Log.d("userAdapter","userAdapter -> ${user.last().name}")
//            Log.d("userAdapter","c -> ${user.last().id}")


        })

//        view.endBasket.setOnClickListener{
//            val service: Api = RetrofitClient.instance
//            val call: Call<ResponseOrders> = service.setOrders(userOrderData)
//            call.enqueue(
//                object : Callback<ResponseOrders> {
//                    override fun onFailure(call: Call<ResponseOrders>, t: Throwable) {
//                        Log.d("onFailure", "onFailure")
//                    }
//                    override fun onResponse(call: Call<ResponseOrders>, response: Response<ResponseOrders>) {
//                        Toast.makeText(context as Activity, getString(R.string.profileSaved),
//                            Toast.LENGTH_LONG).show()
//                        Log.d("onResponse", "onResponse ")
//                    }
//                }
//            )
//        }



//        view.removeBasketBtn.setOnClickListener {
//            mUserViewModel.deleteUser(args.currentBasket)
//            Log.d("deleteUser","deleteUser")
//        }



        return view
    }

}