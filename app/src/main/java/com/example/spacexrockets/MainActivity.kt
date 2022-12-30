package com.example.spacexrockets

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.spacexrockets.api.GetRockets
import com.example.spacexrockets.api.RetrofitHelper
import com.example.spacexrockets.repository.RocketRepository
import com.example.spacexrockets.viewModels.RocketViewModel
import com.example.spacexrockets.viewModels.RocketViewModelFactory

class MainActivity : AppCompatActivity() {

    lateinit var rocketViewModel: RocketViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val getRockets = RetrofitHelper.getInstance().create(GetRockets::class.java)
        val repository = RocketRepository(getRockets)

        rocketViewModel = ViewModelProvider(this,RocketViewModelFactory(repository)).get(RocketViewModel::class.java)

        rocketViewModel.allDetails.observe(this, Observer {
            Log.e("SwapnilData",it.toString())
        })


    }
}