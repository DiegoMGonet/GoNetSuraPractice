package com.gonet.kotlinpractice

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.gonet.kotlinpractice.databinding.KpActivityMainBinding
import com.gonet.kotlinpractice.flows.cart.KPCartActivity
import com.gonet.kotlinpractice.flows.description.KPSummaryActivity
import com.gonet.kotlinpractice.flows.home.fragments.KPHomeFragListener
import com.gonet.kotlinpractice.general.extensions.goToSection

class KPMainActivity :
    AppCompatActivity(),
    KPHomeFragListener
{
    private lateinit var binding: KpActivityMainBinding
    private var viewBadgeItems: TextView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.kp_activity_main)

        binding.bottomNavOptions.setOnItemSelectedListener {
            goToSection(it.itemId)
            true
        }

        binding.bottomNavOptions.selectedItemId = R.id.menu_home_option
    }

    override fun goToSummary(id: Int) {
        val intent = Intent(this, KPSummaryActivity::class.java).apply {
            putExtra("KP_ID_PRODUCT", id)
        }
        startActivity(intent)
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        val inflater: MenuInflater = menuInflater
        inflater.inflate(R.menu.kp_main_menu, menu)
        val cartOption = menu.findItem(R.id.menu_cart_option)
        val menuAction = cartOption.actionView
        viewBadgeItems = menuAction?.findViewById(R.id.textView_cart_badge)

        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.menu_cart_option -> {
                val intent = Intent(this, KPCartActivity::class.java)
                startActivity(intent)
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    override fun updateNumItems(items: Int) {
        updateCartBadge(items)
    }

    private fun updateCartBadge(bagItems: Int = 0) {
        if (bagItems == 0) {
            viewBadgeItems?.visibility = View.GONE
        } else {
            viewBadgeItems?.visibility = View.VISIBLE
            viewBadgeItems?.text = bagItems.toString()
        }
    }
}