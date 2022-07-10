package com.lux.assert_lost_and_found

import com.google.gson.Gson
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.converter.scalars.ScalarsConverterFactory

class RetrofitHelper {

    companion object {
        fun getGsonInstance() : Retrofit{
            val builder=Retrofit.Builder()
                .baseUrl("http://openapi.seoul.go.kr:8088/")
                .addConverterFactory(GsonConverterFactory.create())
                .build()
            return builder
        }

    }
}
class RetrofitHelperForDothome {

    companion object{
        fun getInstanceForDothome() : Retrofit{
            val builder=Retrofit.Builder()
                .baseUrl("http://iwannagohome.dothome.co.kr")
                .addConverterFactory(ScalarsConverterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build()
            return builder
        }


    }


}