package com.gonet.kotlinpractice.general.extensions

import androidx.fragment.app.FragmentActivity
import com.gonet.kotlinpractice.R
import com.gonet.kotlinpractice.flows.favorites.fragments.KPFavoritesFragment
import com.gonet.kotlinpractice.flows.home.fragments.KPHomeFragment
import com.gonet.kotlinpractice.flows.settings.fragments.KPSettingsFragment

fun FragmentActivity.goToSection(option: Int) {
    val transaction = supportFragmentManager.beginTransaction()
    val homeFragment = supportFragmentManager.fragments.firstOrNull { it is KPHomeFragment }

    if (option == R.id.menu_home_option) {
        if(homeFragment == null) {
            transaction.add(R.id.fragment_main_container, KPHomeFragment()).commit()
        } else {
            transaction.show(homeFragment)
            supportFragmentManager.fragments.forEach {
                if (it !is KPHomeFragment && !it.isHidden) {
                    transaction.hide(it).commit()
                }
            }
        }
    } else {
        homeFragment?.let { transaction.hide(it) }
        val fragment = when(option) {
            R.id.menu_favorites_option -> KPFavoritesFragment()
            R.id.menu_settings_option -> KPSettingsFragment()
            else -> KPFavoritesFragment()
        }
        transaction.replace(R.id.fragment_main_container, fragment).commit()
    }
}