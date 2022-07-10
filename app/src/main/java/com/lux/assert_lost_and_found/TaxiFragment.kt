package com.lux.assert_lost_and_found

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.lux.assert_lost_and_found.databinding.FragmentTaxiBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit

class TaxiFragment:Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return binding.root
    }
    val binding:FragmentTaxiBinding by lazy { FragmentTaxiBinding.inflate(layoutInflater) }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //items.add(TaxiItem("신발","관악구","택시회사","신발 잃어버림","2","2022-02-02"))




    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val retrofit:Retrofit=RetrofitHelper.getGsonInstance()
        val service:RetrofitService=retrofit.create(RetrofitService::class.java)
        val call:Call<TaxiResponse> = service.getJson("1","500")
        call.enqueue(object :Callback<TaxiResponse>{
            override fun onResponse(call: Call<TaxiResponse>, response: Response<TaxiResponse>) {
                val taxiResponse=response.body()
                val taxiArticleInfo=taxiResponse?.lostArticleInfo
                val taxiItems:MutableList<TaxiItem> = taxiArticleInfo?.row as MutableList<TaxiItem>
                binding.recyclerTaxi.adapter=TaxiAdapter(context as SecondActivity,taxiItems)
                binding.recyclerTaxi.layoutManager=LinearLayoutManager(context,LinearLayoutManager.VERTICAL,false)
            //Toast.makeText(context, "${taxiItems?.size}", Toast.LENGTH_SHORT).show()

            }

            override fun onFailure(call: Call<TaxiResponse>, t: Throwable) {
                Toast.makeText(context , "${t.message}", Toast.LENGTH_SHORT).show()
            }

        })
    }

}