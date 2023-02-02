package com.gonet.kotlinpractice.flows.favorites.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.gonet.kotlinpractice.databinding.KpItemProductBinding
import com.gonet.kotlinpractice.general.extensions.toCurrency
import com.gonet.kotlinpractice.general.extensions.toWeight
import com.gonet.kotlinpractice.general.models.Product
import com.squareup.picasso.Picasso

class KPProductAdapter(
    private val listProducts: List<Product>,
    private val onItemClicked: (id: Int) -> Unit
): RecyclerView.Adapter<KPProductAdapter.ViewHolder>() {
    inner class ViewHolder(val binding: KpItemProductBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = KpItemProductBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        with(holder.binding) {
            val (id, name, urlImage, _, price, weight, _) = listProducts[position]
            textViewProductName.text = name
            textViewProductPrice.text = price.toCurrency()
            textViewProductWeight.text = weight.toWeight()
            toggleFavorite.isEnabled = true
            Picasso.with(root.context).load(urlImage).into(imageViewProduct)
            cardViewMain.setOnClickListener { onItemClicked.invoke(id) }
        }
    }

    override fun getItemCount() = listProducts.size

}