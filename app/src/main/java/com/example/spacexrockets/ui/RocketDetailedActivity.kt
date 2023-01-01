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
import com.example.spacexrockets.repository.RocketRepository
import com.example.spacexrockets.viewModels.RocketDetailsViewModel
import com.example.spacexrockets.viewModels.RocketDetailsViewModelFactory
import kotlinx.android.synthetic.main.activity_rocket_detailed.*


class RocketDetailedActivity : AppCompatActivity() {

    lateinit var rocketDetailsViewModel: RocketDetailsViewModel

    lateinit var viewPager: ViewPager
    lateinit var viewPagerAdapter: ViewPagerAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_rocket_detailed)

        var id = ""
        val getAllDetailsOfRockets = RetrofitHelper.getInstance().create(GetRocketService::class.java)
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

            tv_NameOf_top.text = it.name
            txt_status.text = it.active.toString()
            tv_tv_cost_per.text = it.cost_per_launch.toString()
            txt_Success_Rate_percent.text = it.success_rate_pct.toString()
            txt_Description.text = it.description
            txt_Wikipedia_link.text = it.wikipedia
            txt_height.text = it.height.feet.toString()
            txt_Diameter.text = it.diameter.feet.toString()


            val uri: List<String> = it.flickr_images
            Log.e("ImgUrl", uri.toString())
            viewPagerAdapter = ViewPagerAdapter(this@RocketDetailedActivity, uri)
            viewPager.adapter = viewPagerAdapter

        })

    }
}