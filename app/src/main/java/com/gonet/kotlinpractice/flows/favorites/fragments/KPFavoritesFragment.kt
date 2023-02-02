package com.gonet.kotlinpractice.flows.favorites.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.gonet.kotlinpractice.databinding.KpFragmentFavoritesBinding
import com.gonet.kotlinpractice.databinding.KpFragmentHomeBinding

class KPFavoritesFragment: Fragment() {
    private var _binding: KpFragmentFavoritesBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = KpFragmentFavoritesBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}