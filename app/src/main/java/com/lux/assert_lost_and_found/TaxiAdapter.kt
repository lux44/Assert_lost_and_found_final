package com.lux.assert_lost_and_found

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class TaxiAdapter (var context: Context, var items:MutableList<TaxiItem> ) :RecyclerView.Adapter<TaxiAdapter.VH>() {

    inner class VH(itemView:View) : RecyclerView.ViewHolder(itemView){
        val getName:TextView by lazy { itemView.findViewById(R.id.tv_item_title) }
        val getArea:TextView by lazy { itemView.findViewById(R.id.tv_item_location) }
        val getPosition:TextView by lazy { itemView.findViewById(R.id.tv_item_number) }
        val status:TextView by lazy { itemView.findViewById(R.id.tv_get_thing) }
        val readCnt:TextView by lazy { itemView.findViewById(R.id.tv_item_id) }
        val regDate:TextView by lazy { itemView.findViewById(R.id.tv_item_date) }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VH {
        val inflater:LayoutInflater= LayoutInflater.from(context)
        val itemView:View=inflater.inflate(R.layout.recycler_item_taxi,parent,false)
        return VH(itemView)
    }
    override fun onBindViewHolder(holder: VH, position: Int) {
        val item=items.get(position)
        holder.getName.text=item.GET_NAME
        holder.getArea.text=item.GET_AREA
        holder.getPosition.text=item.GET_POSITION
        holder.status.text=item.STATUS
        holder.readCnt.text=item.READ_CNT
        holder.regDate.text=item.REG_DATE
    }
    override fun getItemCount(): Int = items.size
}