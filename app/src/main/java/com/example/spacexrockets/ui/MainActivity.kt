package com.example.spacexrockets.ui

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.spacexrockets.MyApplication
import com.example.spacexrockets.adapter.RocketAdapter
import com.example.spacexrockets.api.GetRocketService
import com.example.spacexrockets.api.RetrofitHelper
import com.example.spacexrockets.databinding.ActivityMainBinding
import com.example.spacexrockets.repository.RocketRepository
import com.example.spacexrockets.utils.Resource
import com.example.spacexrockets.viewModels.RocketViewModel
import com.example.spacexrockets.viewModels.RocketViewModelFactory

class MainActivity : AppCompatActivity() {

    lateinit var rocketViewModel: RocketViewModel
    lateinit var adapter: RocketAdapter

    lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupUI()

        val repository = (application as MyApplication).rocketRepository


        rocketViewModel = ViewModelProvider(this, RocketViewModelFactory(repository))[RocketViewModel::class.java]
        setupObservers()

    }

    private fun setupUI() {
        binding.recyclerView.layoutManager = LinearLayoutManager(this)

    }

    private fun setupObservers() {
        rocketViewModel.rockets.observe(this, Observer {
            when (it) {
                is Resource.Error -> {
                    binding.progressBar.visibility = View.GONE
                    it.data?.let { rockets ->
                        adapter = RocketAdapter(
                            this,
                            rockets,

                            ) { rocketId ->
                            nextActivity(rocketId)
                        }
                    }
                    binding.recyclerView.adapter = adapter
                    Toast.makeText(this, it.message, Toast.LENGTH_SHORT).show()
                }
                is Resource.Loading -> {
                    binding.progressBar.visibility = View.VISIBLE
                }
                is Resource.Success -> {
                    binding.progressBar.visibility = View.GONE
                    it.data?.let { rockets ->
                        adapter = RocketAdapter(
                            this,
                            rockets,

                            ) { rocketId ->
                            nextActivity(rocketId)
                        }
                    }
                    binding.recyclerView.adapter = adapter
                }
            }
        })
    }

    private fun nextActivity(id: String) {
        val intent = Intent(this, RocketDetailedActivity::class.java)
        intent.putExtra("EXTRA_ID", id)
        startActivity(intent)
    }

    companion object {
        private const val TAG = "MainActivity"
    }
}