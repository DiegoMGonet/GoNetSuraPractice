package com.gonet.kotlinpractice.flows.home.fragments

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import com.gonet.kotlinpractice.databinding.KpFragmentHomeBinding
import com.gonet.kotlinpractice.flows.home.adapter.KPProductAdapter
import com.gonet.kotlinpractice.flows.home.states.KPProductItemAction
import com.gonet.kotlinpractice.flows.home.states.KPQueryProductsState
import com.gonet.kotlinpractice.flows.home.viewmodels.KPHomeViewModel
import com.gonet.kotlinpractice.general.models.Product
import kotlinx.coroutines.flow.collectLatest
import org.koin.androidx.viewmodel.ext.android.viewModel

class KPHomeFragment: Fragment() {
    private var _binding: KpFragmentHomeBinding? = null
    private val binding get() = _binding!!
    private val homeViewModel: KPHomeViewModel by viewModel()
    private var listener: KPHomeFragListener? = null

    override fun onAttach(context: Context) {
        super.onAttach(context)
        listener = context as? KPHomeFragListener
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = KpFragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        updateNumberItems()

        lifecycleScope.launchWhenStarted {
            homeViewModel.uiState.collectLatest {
                when(it){
                    is KPQueryProductsState.Success -> populateProducts(it.listProducts)
                    KPQueryProductsState.Loading -> {}
                    is KPQueryProductsState.Error -> showError(it.errorMessage)
                }
            }
        }
    }

    override fun onDetach() {
        super.onDetach()
        listener = null
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun populateProducts(listProducts: List<Product>) {
        val adapter = KPProductAdapter(listProducts) {
            when(it) {
                is KPProductItemAction.KPItemClick -> listener?.goToSummary(it.id)
                is KPProductItemAction.KPAddToBag  -> {
                    Toast.makeText(requireContext(), "Agregado al carrito", Toast.LENGTH_SHORT).show()
                    homeViewModel.addToBag(it.id)
                    updateNumberItems()
                }
                is KPProductItemAction.KPChangeFav -> {
                    val option = if (it.isChecked) "Agregado a" else "Eliminado de"
                    Toast.makeText(requireContext(), "$option favoritos", Toast.LENGTH_SHORT).show()
                    homeViewModel.modifyFavoriteById(it.id, it.isChecked)
                }
            }
        }
        binding.recyclerProductsList.adapter = adapter
    }

    private fun updateNumberItems() {
        val items = homeViewModel.checkBagItemsNumber()
        listener?.updateNumItems(items)
    }

    private fun showError(message: String){
        Toast.makeText(requireContext(), message, Toast.LENGTH_LONG).show()
    }
}

interface KPHomeFragListener {
    fun goToSummary(id: Int)
    fun updateNumItems(items: Int)
}