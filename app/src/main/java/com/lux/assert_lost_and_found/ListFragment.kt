package com.lux.assert_lost_and_found

import android.app.Activity
import android.opengl.Visibility
import android.os.Bundle
import android.view.*
import android.widget.Adapter
import android.widget.AdapterView
import android.widget.ArrayAdapter
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.lux.assert_lost_and_found.databinding.FragmentListBinding

class ListFragment:Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return binding.root
    }
    val binding:FragmentListBinding by lazy { FragmentListBinding.inflate(layoutInflater) }
    var items = mutableListOf<List_Item>()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)

        binding.spinnerCategory1.adapter=ArrayAdapter.createFromResource(activity as SecondActivity,R.array.category1,R.layout.spinner_sample2)
        (binding.spinnerCategory1.adapter as ArrayAdapter<CharSequence>).setDropDownViewResource(R.layout.spinner_sample)
        binding.spinnerCategory1.onItemSelectedListener=object : AdapterView.OnItemSelectedListener{
            override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
                when (binding.spinnerCategory1.selectedItemPosition){
                    0->{
                        binding.spinnerCategory2.visibility=View.VISIBLE
                        binding.spinnerCategory2.adapter=ArrayAdapter.createFromResource(activity as SecondActivity,R.array.category2_1,R.layout.spinner_sample2)
                        (binding.spinnerCategory2.adapter as ArrayAdapter<CharSequence>).setDropDownViewResource(R.layout.spinner_sample)
                    }
                    6->{
                        binding.spinnerCategory2.visibility=View.VISIBLE
                        binding.spinnerCategory2.adapter=ArrayAdapter.createFromResource(activity as SecondActivity,R.array.category2_2,R.layout.spinner_sample2)
                        (binding.spinnerCategory2.adapter as ArrayAdapter<CharSequence>).setDropDownViewResource(R.layout.spinner_sample)
                    }
                    else->{
                        binding.spinnerCategory2.visibility=View.INVISIBLE
                    }
                }
            }

            override fun onNothingSelected(p0: AdapterView<*>?) {
                TODO("Not yet implemented")
            }
        }
        binding.spinnerReceived.adapter=ArrayAdapter.createFromResource(activity as SecondActivity,R.array.received,R.layout.spinner_sample2)
        (binding.spinnerReceived.adapter as ArrayAdapter<CharSequence>).setDropDownViewResource(R.layout.spinner_sample)



        items.add(List_Item("에어팟","서울시 성동구 어저구","2022-03-02","01","ㅁㄴㅇㄹ",R.drawable.button_blue))

        binding.recyclerList.adapter=ListAdapter(activity as SecondActivity,items)
        binding.recyclerList.layoutManager=LinearLayoutManager(activity,LinearLayoutManager.VERTICAL,false)


    }




    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.option_list,menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            R.id.option_report->{

            }
        }
        return super.onOptionsItemSelected(item)
    }



}