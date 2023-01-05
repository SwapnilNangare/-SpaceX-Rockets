package com.example.spacexrockets.ui

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.viewpager.widget.ViewPager
import com.example.spacexrockets.R
import com.example.spacexrockets.adapter.ViewPagerAdapter
import com.example.spacexrockets.api.GetRocketService
import com.example.spacexrockets.api.RetrofitHelper
import com.example.spacexrockets.databinding.ActivityRocketDetailedBinding
import com.example.spacexrockets.repository.RocketRepository
import com.example.spacexrockets.viewModels.RocketDetailsViewModel
import com.example.spacexrockets.viewModels.RocketDetailsViewModelFactory


class RocketDetailedActivity : AppCompatActivity() {

    lateinit var rocketDetailsViewModel: RocketDetailsViewModel

    lateinit var viewPager: ViewPager
    lateinit var viewPagerAdapter: ViewPagerAdapter
    lateinit var binding: ActivityRocketDetailedBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRocketDetailedBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val actionbar = supportActionBar
        actionbar?.setDisplayHomeAsUpEnabled(true)
        actionbar?.setDisplayHomeAsUpEnabled(true)


        var id = ""
        val getAllDetailsOfRockets =
            RetrofitHelper.getInstance().create(GetRocketService::class.java)
        val rocketDetailsRepository = RocketRepository(getAllDetailsOfRockets)
        if (intent.hasExtra("EXTRA_ID")) {
            id = intent?.getStringExtra("EXTRA_ID").toString()
        }
        Log.e("IdIS", id)





        viewPager = findViewById(R.id.idViewPager)

        rocketDetailsViewModel =
            ViewModelProvider(this, RocketDetailsViewModelFactory(rocketDetailsRepository, id)).get(
                RocketDetailsViewModel::class.java
            )

        rocketDetailsViewModel.allDetailsOfRockets.observe(this, Observer {

            Log.e("allDetailsOfRockets", it.toString())

            binding.tvNameOfTop.text = it.name
            binding.txtStatus.text = it.active.toString()
            binding.tvTvCostPer.text = it.cost_per_launch.toString()
            binding.txtSuccessRatePercent.text = it.success_rate_pct.toString()
            binding.txtDescription.text = it.description
            binding.txtWikipediaLink.text = it.wikipedia
            binding.txtHeight.text = it.height.feet.toString()
            binding.txtDiameter.text = it.diameter.feet.toString()


            val uri: List<String> = it.flickr_images
            Log.e("ImgUrl", uri.toString())

            viewPagerAdapter = ViewPagerAdapter(this@RocketDetailedActivity, uri, {

            })
            viewPager.adapter = viewPagerAdapter

        })

    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }
}