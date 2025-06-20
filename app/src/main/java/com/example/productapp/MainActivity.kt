package com.example.productapp

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.example.productapp.databinding.ActivityMainBinding
import com.example.productapp.fragment.AccountFragment
import com.example.productapp.fragment.CartFragment
import com.example.productapp.fragment.HomeFragment
import com.example.productapp.fragment.SearchFragment

class MainActivity : AppCompatActivity() {

    // View binding object to interact with UI
    private lateinit var binding: ActivityMainBinding

    // Create instance of fragments
    private val homeFragment = HomeFragment()
    private val searchFragment = SearchFragment()
    private val cartFragment = CartFragment()
    private val accountFragment = AccountFragment()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Default show fragment_home
        showHomeFragment()

        handleBottomNavigationView()
    }

    private fun handleBottomNavigationView() {
        binding.navigationView.setOnItemSelectedListener { menuItem ->

            // Show corresponding fragment depend on what is tapped
            when(menuItem.itemId) {
                R.id.mnuHome ->showHomeFragment()
                R.id.mnuSearch ->showSearchFragment()
                R.id.mnuCart ->showCartFragment()
                R.id.mnuAccount ->showAccountFragment()
            }
            true
        }
    }

    private fun showHomeFragment() {
        supportFragmentManager.beginTransaction().replace(binding.lytFragment.id, homeFragment).commit()
    }

    private fun showSearchFragment() {
        supportFragmentManager.beginTransaction().replace(binding.lytFragment.id,searchFragment).commit()
    }

    private fun showCartFragment() {
        supportFragmentManager.beginTransaction().replace(binding.lytFragment.id, cartFragment).commit()
    }

    private fun showAccountFragment() {
        supportFragmentManager.beginTransaction().replace(binding.lytFragment.id, accountFragment).commit()
    }

    fun replaceFragment(fragment: Fragment) {
        supportFragmentManager.beginTransaction()
            .replace(R.id.lytFragment, fragment)
            .commit()
    }

    fun selectBottomNavItem(itemId: Int) {
        binding.navigationView.selectedItemId = itemId
    }
}