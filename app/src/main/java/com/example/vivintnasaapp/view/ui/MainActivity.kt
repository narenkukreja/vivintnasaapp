package com.example.vivintnasaapp.view.ui

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.vivintnasaapp.application.MyApplication
import com.example.vivintnasaapp.databinding.ActivityMainBinding
import com.example.vivintnasaapp.view.adapters.NasaRecyclerViewAdapter
import com.example.vivintnasaapp.viewmodel.viewmodels.NasaViewModel
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {

    private lateinit var viewModel: NasaViewModel
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        viewModel = ViewModelProvider(this)[NasaViewModel::class.java]

        lifecycleScope.launch {
            viewModel.getImages("apollo 11", "moon landing")
            val nasaImagesRV = binding.nasaImagesRV
            viewModel.nasaImageStateFlow.collect { state ->
                when (state) {
                    is NasaViewModel.UIState.Error -> {
                        binding.nasaImagesRV.visibility = View.GONE
                        Toast.makeText(
                            this@MainActivity,
                            "We are having trouble getting what you need, please try again later.",
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                    is NasaViewModel.UIState.Loading -> {
                        binding.nasaImagesRV.visibility = View.GONE
                    }
                    is NasaViewModel.UIState.Success -> {
                        binding.nasaImagesRV.visibility = View.VISIBLE
                        val imagesAdapter = NasaRecyclerViewAdapter(this@MainActivity as Context, state.itemsList)
                        nasaImagesRV.apply {
                            adapter = imagesAdapter
                            layoutManager = LinearLayoutManager(this@MainActivity)
                        }
                        Log.d(MyApplication.TAG, "List size is " + state.itemsList.size)
                    }
                }
            }
        }
    }
}