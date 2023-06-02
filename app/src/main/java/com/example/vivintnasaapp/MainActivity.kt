package com.example.vivintnasaapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import com.example.vivintnasaapp.application.MyApplication
import com.example.vivintnasaapp.databinding.ActivityMainBinding
import com.example.vivintnasaapp.viewmodel.viewmodels.NasaViewModel
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {

    private lateinit var viewModel: NasaViewModel
    private lateinit var binding : ActivityMainBinding
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

                    }
                    is NasaViewModel.UIState.Loading -> {

                    }
                    is NasaViewModel.UIState.Success -> {
                        Log.d(MyApplication.TAG, "List size is " + state.itemsList.size)
                    }
                    else -> {

                    }
                }
            }
        }
    }
}