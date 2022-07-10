package com.lux.assert_lost_and_found

import android.content.DialogInterface
import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import com.lux.assert_lost_and_found.databinding.ActivityDetailExplainBinding
import okhttp3.MediaType
import okhttp3.MultipartBody
import okhttp3.RequestBody
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.http.Multipart
import java.io.File

class DetailExplainActivity : AppCompatActivity() {
    
    val binding:ActivityDetailExplainBinding by lazy { ActivityDetailExplainBinding.inflate(layoutInflater) }
    
    val datas:Bundle by lazy { intent.extras?:Bundle() }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //setContentView(R.layout.activity_detail_explain)
        setContentView(binding.root)

        Toast.makeText(this, "${datas.getStringArray("lostImgPath")!![0]}", Toast.LENGTH_SHORT).show()
        val detailExplain:String= binding.etExplain.text.toString()




        binding.btnRegister.setOnClickListener {
            val builder:AlertDialog.Builder=AlertDialog.Builder(this)
            builder.setMessage("분실물이 등록되었어요!")
            builder.setPositiveButton("메인으로 가기", DialogInterface.OnClickListener { dialogInterface, i ->
                datas.putString("explanation",detailExplain)
                //dataSave(datas)

            })
        }


    }

    fun dataSave(datas:Bundle){

        val retrofit: Retrofit =RetrofitHelperForDothome.getInstanceForDothome()
        val retrofitService:RetrofitService=retrofit.create(RetrofitService::class.java)

        val filePart:MultipartBody.Part
        var filePart2: MultipartBody.Part? =null

        val lostImgPath:Array<String> = datas.getStringArray("lostImgPath") as Array<String>

        val file:File=File(lostImgPath[0])
        val requestBody:RequestBody= RequestBody.create(MediaType.parse("image/*"),file)
        filePart=MultipartBody.Part.createFormData("lostImg1",file.name,requestBody)
        
        if (lostImgPath[1]!=null){
            val file2:File= File(lostImgPath[1])
            val requestBody2:RequestBody= RequestBody.create(MediaType.parse("image/*"),file2)
            filePart2=MultipartBody.Part.createFormData("lostImg2",file2.name,requestBody2)
        }

        var dataPart:MutableMap<String,String> = mutableMapOf()
        dataPart.put("getRegion",datas.getString("getRegion","구"))
        dataPart.put("getPlace",datas.getString("getPlace",""))
        dataPart.put("getType1",datas.getString("getType1",""))
        dataPart.put("getType2",datas.getString("getType2",""))
        dataPart.put("postTitle",datas.getString("postTitle",""))
        dataPart.put("getTime",datas.getString("getTime",""))
        dataPart.put("getDate",datas.getString("getDate",""))
        dataPart.put("lostImg1",datas.getString("lostImg1",""))
        dataPart.put("lostImg2",datas.getString("lostImg2",""))
        dataPart.put("explanation",datas.getString("explanation",""))

        

    }
}