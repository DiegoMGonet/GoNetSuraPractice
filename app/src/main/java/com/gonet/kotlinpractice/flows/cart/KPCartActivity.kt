package com.gonet.kotlinpractice.flows.cart

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.lifecycleScope
import com.gonet.kotlinpractice.R
import com.gonet.kotlinpractice.databinding.KpActivityCartBinding
import com.gonet.kotlinpractice.flows.cart.adapter.KPProductBagAdapter
import com.gonet.kotlinpractice.flows.cart.states.KPBagProductsState
import com.gonet.kotlinpractice.flows.cart.viewModels.KPCartViewModel
import com.gonet.kotlinpractice.general.models.Product
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel

class KPCartActivity : AppCompatActivity() {
    private lateinit var binding: KpActivityCartBinding
    private val cartViewModel: KPCartViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.kp_activity_cart)
        supportActionBar?.title = "Carrito"

        lifecycleScope.launch {
            cartViewModel.uiState.collectLatest {
                when(it){
                    is KPBagProductsState.Success -> populateProducts(it.listProducts)
                }
            }
        }
    }

    private fun populateProducts(listProducts: List<Product>) {
        val adapter = KPProductBagAdapter(listProducts) {
            cartViewModel.removeProduct(it)
            Toast.makeText(this, "Eliminado de la lista", Toast.LENGTH_SHORT).show()
        }
        binding.recyclerBagList.adapter = adapter
    }
}