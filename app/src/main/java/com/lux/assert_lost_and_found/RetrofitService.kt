package com.lux.assert_lost_and_found

import okhttp3.MultipartBody
import retrofit2.Call
import retrofit2.http.*

interface RetrofitService {
    @GET("4c42546d79766f6c37396a497a5173/json/lostArticleInfo/{start}/{end}/")
    fun getJson(@Path("start")  startIndex:String, @Path("end") endIndex:String) : Call<TaxiResponse>

    @Multipart
    @POST("Assert/insertDBGetPlace.php")
    fun postDataToServer (@PartMap dataPartMap:MutableMap<String,String>,@Part filePart1:MultipartBody.Part,@Part filePart2:MultipartBody.Part  ) : Call<Data>


}