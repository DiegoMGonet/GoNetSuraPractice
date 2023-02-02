package com.gonet.kotlinpractice.flows.cart.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.gonet.kotlinpractice.databinding.KpItemProductBagBinding
import com.gonet.kotlinpractice.general.extensions.toCurrency
import com.gonet.kotlinpractice.general.extensions.toWeight
import com.gonet.kotlinpractice.general.models.Product
import com.squareup.picasso.Picasso

class KPProductBagAdapter(
    listProducts: List<Product>,
    private val listener: (id: Int) -> Unit
): RecyclerView.Adapter<KPProductBagAdapter.ViewHolder>() {
    private val listData = listProducts.toMutableList()

    inner class ViewHolder(val binding: KpItemProductBagBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = KpItemProductBagBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        with(holder.binding) {
            val (id, name, urlImage, _, price, weight, _) = listData[position]
            textViewProductName.text = name
            textViewProductPrice.text = price.toCurrency()
            textViewProductWeight.text = weight.toWeight()
            Picasso.with(root.context).load(urlImage).into(imageViewProduct)
            buttonRemoveProduct.setOnClickListener {
                listData.removeAt(position)
                notifyDataSetChanged()
                listener.invoke(id)
            }
        }
    }

    override fun getItemCount() = listData.size

}