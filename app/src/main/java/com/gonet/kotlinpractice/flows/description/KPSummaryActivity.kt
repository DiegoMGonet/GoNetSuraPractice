package com.gonet.kotlinpractice.flows.description

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.lifecycleScope
import com.gonet.kotlinpractice.R
import com.gonet.kotlinpractice.databinding.KpActivitySummaryProductItemBinding
import com.gonet.kotlinpractice.flows.description.states.KPSummaryState
import com.gonet.kotlinpractice.flows.description.viewmodels.KPSummaryViewModel
import com.gonet.kotlinpractice.general.extensions.toCurrency
import com.gonet.kotlinpractice.general.extensions.toWeight
import com.gonet.kotlinpractice.general.models.Product
import com.squareup.picasso.Picasso
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel

class KPSummaryActivity : AppCompatActivity() {
    private lateinit var binding: KpActivitySummaryProductItemBinding
    private val summaryViewModel: KPSummaryViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.kp_activity_summary_product_item)
        supportActionBar?.hide()

        val idProduct = intent.getIntExtra("KP_ID_PRODUCT", 0)
        summaryViewModel.getProduct(idProduct)

        lifecycleScope.launch {
            summaryViewModel.uiState.collectLatest {
                when(it) {
                    is KPSummaryState.ShowProductDetail -> it.product?.let {
                            product ->  showProductDetail(product)
                    }
                    is KPSummaryState.ShowMessage -> showMessage(it.message)
                }
            }
        }
    }

    private fun showProductDetail(product: Product){
        val (id, name, urlImage, description, price, weight, isFavorite) = product
        binding.apply {
            textViewProductName.text = name
            textViewProductPrice.text = price.toCurrency()
            textViewProductWeight.text = weight.toWeight()
            textViewProductDetails.text = description
            toggleFavorite.isEnabled = isFavorite
            Picasso.with(root.context).load(urlImage).into(imageViewProduct)
        }
    }

    private fun showMessage(message: String){
        Toast.makeText(this, message, Toast.LENGTH_LONG).show()
    }
}