package com.lux.assert_lost_and_found

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.lux.assert_lost_and_found.databinding.FragmentPoliceBinding

class PoliceFragment : Fragment() {


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?


    ): View? {
        return binding.root
    }
    val binding:FragmentPoliceBinding by lazy { FragmentPoliceBinding.inflate(layoutInflater) }
    var items:MutableList<Police_Item> = mutableListOf()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        items.add(Police_Item("지갑","성동구 어쩌구","2022-02-02","lclclclc"))

        binding.recyclerPolice.adapter=PoliceAdapter(context as SecondActivity,items)
        binding.recyclerPolice.layoutManager=LinearLayoutManager(activity,LinearLayoutManager.VERTICAL,false)


    }

    fun loadData(){
        val thread:Thread= Thread(){
            
        }
    }








}