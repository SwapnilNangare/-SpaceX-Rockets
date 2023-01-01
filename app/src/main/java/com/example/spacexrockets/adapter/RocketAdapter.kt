package com.example.spacexrockets.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.spacexrockets.R
import com.example.spacexrockets.models.rocketlist.MainRocketModelItem

class RocketAdapter(
    val context: Context,
    val rocketList: ArrayList<MainRocketModelItem>,
    private val onClickListener: (String) -> Unit,
) : RecyclerView.Adapter<RocketAdapter.viewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): viewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = inflater.inflate(R.layout.list_item, parent, false)
        return viewHolder(view)
    }


    override fun onBindViewHolder(holder: viewHolder, position: Int) {

        rocketList[position]
        holder.tv_Name.text = rocketList.get(position).name
        holder.tv_Country.text = rocketList.get(position).country
        holder.tv_Eg_Count.text = rocketList.get(position).engines.number.toString()


        val uri: List<String> = rocketList.get(position).flickr_images

        if (uri.isNotEmpty()) {
            Glide.with(context)
                .load(uri.get(0))
                .into(holder.img_Flicker_Image)



        }
        holder.itemView.setOnClickListener {

            onClickListener.invoke(rocketList.get(position).id)
        }


    }

    override fun getItemCount(): Int {
        return rocketList.size
    }

    class viewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val tv_Name = itemView.findViewById<TextView>(R.id.tv_Name)
        val tv_Country = itemView.findViewById<TextView>(R.id.tv_Country)
        val tv_Eg_Count = itemView.findViewById<TextView>(R.id.tv_Eg_Count)
        val img_Flicker_Image = itemView.findViewById<ImageView>(R.id.img_Flicker_Image)

    }
}