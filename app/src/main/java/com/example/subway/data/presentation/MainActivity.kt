package com.example.subway.data.presentation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.subway.R
import com.example.subway.databinding.ActivityMainBinding
import androidx.navigation.ui.setupActionBarWithNavController
import com.example.subway.extensions.toGone
import com.example.subway.extensions.toVisible
import androidx.navigation.fragment.NavHostFragment

class MainActivity : AppCompatActivity() {

    private val binding: ActivityMainBinding by lazy { ActivityMainBinding.inflate(layoutInflater) }
    private val navigationController by lazy {
        (supportFragmentManager.findFragmentById(R.id.mainNavigationHostContainer) as NavHostFragment).navController
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        initViews()
        bindViews()
    }
    override fun onSupportNavigateUp(): Boolean {
        return navigationController.navigateUp() || super.onSupportNavigateUp()
    }

    private fun initViews() {
        setSupportActionBar(binding.toolbar)
        setupActionBarWithNavController(navigationController)
    }

    private fun bindViews() {
        navigationController.addOnDestinationChangedListener { _, destination, _ ->
            if (destination.id == R.id.station_arrivals_dest) {
                binding.toolbar.toVisible()
            } else {
                binding.toolbar.toGone()
            }
        }
    }
}