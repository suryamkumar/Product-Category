package com.example.task_inovantinovant

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.lifecycleScope
import com.example.task_inovantinovant.databinding.ActivityMainBinding
import com.example.task_inovantinovant.screens.ProductDetails
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)

        // Set default title or loading text
        binding.customToolBar.titleName.text = "Loading..."

        // Load data from API and set title dynamically
        loadTitleFromApi()

        supportFragmentManager.beginTransaction()
            .replace(R.id.fragment_container, ProductDetails())
            .commit()
    }

    private fun loadTitleFromApi() {
        lifecycleScope.launch {
            val title = fetchTitleFromApi()
            binding.customToolBar.titleName.text = title
        }
    }

    // Simulated API call — replace with your actual API call
    private suspend fun fetchTitleFromApi(): String = withContext(Dispatchers.IO) {
        delay(1500) // simulate network delay
        "Product Details"  // data fetched from API
    }
}
