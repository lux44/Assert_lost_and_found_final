package com.lux.assert_lost_and_found

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class ListAdapter constructor(val context: Context, var items:MutableList<List_Item>) :RecyclerView.Adapter<ListAdapter.VH>(){

    inner class VH (itemView:View) :RecyclerView.ViewHolder(itemView){
        val title:TextView by lazy { itemView.findViewById(R.id.tv_item_title) }
        val location:TextView by lazy { itemView.findViewById(R.id.tv_item_location) }
        val date:TextView by lazy { itemView.findViewById(R.id.tv_item_date) }
        val num:TextView by lazy { itemView.findViewById(R.id.tv_item_number) }
        val Id:TextView by lazy { itemView.findViewById(R.id.tv_item_id) }
        val img:ImageView by lazy { itemView.findViewById(R.id.iv_item) }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VH {
        val inflater:LayoutInflater= LayoutInflater.from(context)
        val itemView:View= inflater.inflate(R.layout.recycler_item,parent,false)
        return VH(itemView)
    }

    override fun onBindViewHolder(holder: VH, position: Int) {
        val item=items.get(position)
        holder.title.text=item.title
        holder.Id.text=item.Id
        holder.date.text=item.date
        holder.img.setImageResource(item.img)
        holder.location.text=item.location
        holder.num.text=item.num
        holder.itemView.setOnClickListener {
            val intent:Intent=Intent(context,ItemActivity::class.java)
            context.startActivity(intent)
        }
    }

    override fun getItemCount(): Int = items.size


}