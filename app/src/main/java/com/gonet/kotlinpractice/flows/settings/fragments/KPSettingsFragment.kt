package com.gonet.kotlinpractice.flows.settings.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.gonet.kotlinpractice.databinding.KpFragmentFavoritesBinding
import com.gonet.kotlinpractice.databinding.KpFragmentHomeBinding
import com.gonet.kotlinpractice.databinding.KpFragmentSettingsBinding

class KPSettingsFragment: Fragment() {
    private var _binding: KpFragmentSettingsBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = KpFragmentSettingsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}