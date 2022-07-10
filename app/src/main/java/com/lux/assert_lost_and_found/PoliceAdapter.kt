package com.lux.assert_lost_and_found

import android.content.Context
import android.view.LayoutInflater
import android.view.TextureView
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.appcompat.view.menu.ActionMenuItemView
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.Adapter

class PoliceAdapter constructor(val context: Context,var items:MutableList<Police_Item>) :RecyclerView.Adapter<PoliceAdapter.VH>(){
    inner class VH (itemView:View) : RecyclerView.ViewHolder(itemView){
        val lstPrdtNm:TextView by lazy { itemView.findViewById(R.id.tv_item_title) }
        val lstPlace:TextView by lazy { itemView.findViewById(R.id.tv_item_location) }
        val lstYmd:TextView by lazy { itemView.findViewById(R.id.tv_item_date) }
        val atcId:TextView by lazy { itemView.findViewById(R.id.tv_item_number) }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VH {
        val inflater:LayoutInflater= LayoutInflater.from(context)
        val itemView:View= inflater.inflate(R.layout.recycler_item,parent,false)
        return VH(itemView)
    }

    override fun onBindViewHolder(holder: VH, position: Int) {
        val item=items.get(position)
        holder.lstPrdtNm.text=item.lstPrdtNm
        holder.lstPlace.text=item.lstPlace
        holder.lstYmd.text=item.lstYmd
        holder.atcId.text=item.atcId
    }

    override fun getItemCount(): Int =items.size
}