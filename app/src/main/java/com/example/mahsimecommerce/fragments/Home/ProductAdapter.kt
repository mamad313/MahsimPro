package com.example.mahsimecommerce.fragments.Home

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.example.mahsimecommerce.R
import com.example.mahsimecommerce.models.ChildProduct
import com.example.mahsimecommerce.models.ChildSlider
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.recycler_item.view.*


class ProductAdapter(private val productList: List<ChildProduct>): RecyclerView.Adapter<ProductAdapter.ViewHolderRecycler>() {

    inner class ViewHolderRecycler(view: View) : RecyclerView.ViewHolder(view){
        val imageView : ImageView = view.findViewById(R.id.imageBack)
        var title : TextView = view.findViewById(R.id.title_tv)
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ViewHolderRecycler {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.recycler_item, parent, false)
        return ViewHolderRecycler(view)    }

    override fun onBindViewHolder(holder: ViewHolderRecycler, position: Int) {
        holder.title.text = productList[holder.adapterPosition].title
        Picasso.get().load(productList[holder.adapterPosition].url)
            .error(R.drawable.placeholder)
            .placeholder(R.drawable.placeholder).into(holder.imageView)

        holder.itemView.recycler_item.setOnClickListener {
            val action = HomeFragmentDirections.actionHomeFragmentToInnerFragment(productList[holder.adapterPosition])
            holder.itemView.findNavController().navigate(action)
            Log.d("slm","slm dorotste")
        }
    }

    override fun getItemCount(): Int {
        return productList.size
    }


}