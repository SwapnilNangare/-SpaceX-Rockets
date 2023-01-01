package com.example.spacexrockets.ui

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.spacexrockets.R
import com.example.spacexrockets.adapter.RocketAdapter
import com.example.spacexrockets.adapter.ViewPagerAdapter
import com.example.spacexrockets.api.GetRocketService
import com.example.spacexrockets.api.RetrofitHelper
import com.example.spacexrockets.repository.RocketRepository
import com.example.spacexrockets.utils.Resource
import com.example.spacexrockets.viewModels.RocketViewModel
import com.example.spacexrockets.viewModels.RocketViewModelFactory
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    lateinit var rocketViewModel: RocketViewModel
    lateinit var adapter: RocketAdapter

    lateinit var viewPagerAdapter: ViewPagerAdapter



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setupUI()
        val getRockets = RetrofitHelper.getInstance().create(GetRocketService::class.java)
        val repository = RocketRepository(getRockets)
        rocketViewModel = ViewModelProvider(
            this,
            RocketViewModelFactory(repository)
        )[RocketViewModel::class.java]

        setupObservers()

    }




    private fun setupUI() {
        recyclerView.layoutManager = LinearLayoutManager(this)

    }

    private fun setupObservers() {
        rocketViewModel.rockets.observe(this, Observer {
            when (it) {
                is Resource.Error -> {
                    progressBar.visibility = View.GONE
                    Toast.makeText(this, it.message, Toast.LENGTH_SHORT).show()
                }
                is Resource.Loading -> {
                    progressBar.visibility = View.VISIBLE
                }
                is Resource.Success -> {
                    progressBar.visibility = View.GONE
                    it.data?.let { rockets ->
                        adapter = RocketAdapter(
                            this,
                            rockets,
                        ) { rocketId ->
                            nextActivity(rocketId)
                        }
                    }
                    recyclerView.adapter = adapter
                }
            }

        })
    }

    private fun nextActivity(id: String) {
        val intent = Intent(this, RocketDetailedActivity::class.java)
        intent.putExtra("EXTRA_ID", id)
        startActivity(intent)
    }
}