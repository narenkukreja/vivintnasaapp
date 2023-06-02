package com.example.vivintnasaapp.view.ui

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.vivintnasaapp.R
import com.example.vivintnasaapp.databinding.ActivityVivintNasaBinding
import com.example.vivintnasaapp.view.adapters.NasaRecyclerViewAdapter
import com.example.vivintnasaapp.viewmodel.viewmodels.NasaViewModel
import kotlinx.coroutines.launch

class VivintNasaActivity : AppCompatActivity() {

    private lateinit var viewModel: NasaViewModel
    private lateinit var binding: ActivityVivintNasaBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityVivintNasaBinding.inflate(layoutInflater)
        setContentView(binding.root)
        viewModel = ViewModelProvider(this)[NasaViewModel::class.java]

        lifecycleScope.launch {
            viewModel.getImages("apollo 11", "moon landing")
            val nasaImagesRV = binding.nasaImagesRV
            viewModel.nasaCollectionStateFlow.collect { state ->
                when (state) {
                    is NasaViewModel.UIState.Error -> {
                        binding.nasaImagesRV.visibility = View.GONE
                        Toast.makeText(
                            this@VivintNasaActivity,
                            getString(R.string.text_error_state),
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                    is NasaViewModel.UIState.Loading -> {
                        binding.nasaImagesRV.visibility = View.GONE
                    }
                    is NasaViewModel.UIState.Success -> {
                        binding.nasaImagesRV.visibility = View.VISIBLE
                        val nasaCollectionAdapter = NasaRecyclerViewAdapter(this@VivintNasaActivity as Context, state.itemsList)
                        nasaImagesRV.apply {
                            adapter = nasaCollectionAdapter
                            layoutManager = LinearLayoutManager(this@VivintNasaActivity)
                        }
                    }
                }
            }
        }
    }
}