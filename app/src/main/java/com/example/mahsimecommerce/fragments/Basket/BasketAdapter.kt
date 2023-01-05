package com.example.mahsimecommerce.fragments.Basket

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.mahsimecommerce.Api
import com.example.mahsimecommerce.R
import com.example.mahsimecommerce.RetrofitClient
import com.example.mahsimecommerce.data.AppDatabase
import com.example.mahsimecommerce.data.User
import com.example.mahsimecommerce.models.ChildOrders
import com.example.mahsimecommerce.models.ResponseOrders
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.basket_item.view.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class BasketAdapter(context:Context): RecyclerView.Adapter<BasketAdapter.BasketViewHolder>() {


    private var userList = emptyList<User>()
    lateinit var orderVar: ChildOrders
    val context = context

    class BasketViewHolder(itemList: View) : RecyclerView.ViewHolder(itemList) {

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BasketViewHolder {
        return BasketViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.basket_item, parent, false)
        )
    }

    override fun onBindViewHolder(holder: BasketViewHolder, position: Int) {
        val currentItem = userList[position]
        holder.itemView.titleBasket.text = currentItem.name
        holder.itemView.priceBasket.text = currentItem.price
        holder.itemView.countBasket.text = "1"
        var number = holder.itemView.countBasket.text.toString()
        var number2 = number.toInt()
//        holder.itemView.detailsBasket.text = currentItem.product
        Picasso.get().load(currentItem.product)
            .error(R.drawable.placeholder)
            .placeholder(R.drawable.placeholder).into(holder.itemView.imageBasket)
        holder.itemView.buttonAdd.setOnClickListener {
            number2 += 1
            holder.itemView.countBasket.text = number2.toString()
        }
        holder.itemView.buttonReduce.setOnClickListener {
            if (number2 > 1)
                number2 -= 1
            holder.itemView.countBasket.text = number2.toString()
        }
        holder.itemView.cardViewLayout.setOnClickListener {
//            val action = ListFragmentDirections.actionListFragmentToUpdateFragment(currentItem)
//            holder.itemView.findNavController().navigate(action)
        }
        holder.itemView.removeBasket.setOnClickListener {
            CoroutineScope(Dispatchers.IO).launch {
                // get the dao and call insertMultipleImages() on it
                AppDatabase.getDatabase(context).userDao().delete(currentItem)
            }
        }
        holder.itemView.endBasket.setOnClickListener{
//            val userOrderData: ChildOrders = ChildOrders(null, userList[position].id.toString(),null,null,userList[position].price.toInt(),userList[position].name )
            val userOrderData: ChildOrders = ChildOrders()
            userOrderData.userId = userList[position].id.toString()
            userOrderData.price = userList[position].price
            userOrderData.name = userList[position].name
            Log.d("userOrderData","userOrderData -> $userOrderData")
            val service: Api = RetrofitClient.instance

            val call: Call<ResponseOrders> = service.setOrders(userOrderData)
            call.enqueue(
                object : Callback<ResponseOrders> {
                    override fun onFailure(call: Call<ResponseOrders>, t: Throwable) {
                        Log.d("onFailure", "onFailure")
                    }
                    override fun onResponse(call: Call<ResponseOrders>, response: Response<ResponseOrders>) {
//                        Toast.makeText(context as Activity, getString(R.string.profileSaved),
//                            Toast.LENGTH_LONG).show()
                        Log.d("onResponse", "onResponse ")
                    }
                }
            )
        }
    }

    override fun getItemCount(): Int {
        return userList.size
    }

    fun setData(user: List<User>) {
        this.userList = user
        notifyDataSetChanged()
    }

}


