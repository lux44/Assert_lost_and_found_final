package com.lux.assert_lost_and_found

import android.app.AlertDialog
import android.app.DatePickerDialog
import android.app.TimePickerDialog
import android.content.Context
import android.content.DialogInterface
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import com.lux.assert_lost_and_found.databinding.ActivityDetailBinding
import org.w3c.dom.Text
import java.sql.Time
import java.text.SimpleDateFormat
import java.util.*

class DetailActivity : AppCompatActivity() {
    val binding:ActivityDetailBinding by lazy { ActivityDetailBinding.inflate(layoutInflater) }

    val getLat:Double by lazy { intent.getDoubleExtra("getLat",37.5609)}
    val getLng:Double by lazy { intent.getDoubleExtra("getLng",127.0347) }
    val getPlace:String by lazy { intent.getStringExtra("getPlace")?:"seoul" }
    val getType1:String by lazy { intent.getStringExtra("getType1")?:"" }
    val getType2:String by lazy { intent.getStringExtra("getType2") ?:""}
    val getRegion:String by lazy { intent.getStringExtra("getRegion")?:"구" }

    lateinit var postTitle:String
    lateinit var getTime:String
    lateinit var getDate: String
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //setContentView(R.layout.activity_detail)
        setContentView(binding.root)



        binding.btnTimePick.setOnClickListener {
            getTime(binding.tvGetTime,context = this)
        }
        binding.btnDatePick.setOnClickListener {
            getDate(binding.tvGetDate,this)
        }
        binding.btnBack.setOnClickListener {
            onBackPressed()
        }
        binding.btnNext.setOnClickListener {

            postTitle=binding.etDetail.text.toString()

            val intent:Intent= Intent(this,PictureActivity::class.java)
            Toast.makeText(this, "$postTitle", Toast.LENGTH_SHORT).show()
            intent.putExtra("getTime",getTime)
            intent.putExtra("getDate",getDate)
            intent.putExtra("postTitle",postTitle)
            intent.putExtra("getLat",getLat)
            intent.putExtra("getLng",getLng)
            intent.putExtra("getPlace",getPlace)
            intent.putExtra("getType1",getType1)
            intent.putExtra("getType2",getType2)
            intent.putExtra("getRegion",getRegion)
            startActivity(intent)




        }
    }

    fun getDate(tv_getDate:TextView,context: Context) {
        val calendar=Calendar.getInstance()

        val dateSetListener=DatePickerDialog.OnDateSetListener{
            datePicker, year, month, day ->
            calendar.set(Calendar.YEAR,year)
            calendar.set(Calendar.MONTH,month)
            calendar.set(Calendar.DAY_OF_MONTH,day)

            getDate=SimpleDateFormat("yyyy-MM-dd").format(calendar.time)
            tv_getDate.text=getDate

        }

        DatePickerDialog(context,dateSetListener,calendar.get(Calendar.YEAR),calendar.get(Calendar.MONTH),calendar.get(Calendar.DAY_OF_MONTH)).show()

    }

    fun getTime(tv_getTime:TextView,context: Context) {
        val calendar=Calendar.getInstance()

        val timeSetListener=TimePickerDialog.OnTimeSetListener{
            timePicker, hour, minute ->
            calendar.set(Calendar.HOUR_OF_DAY,hour)
            calendar.set(Calendar.MINUTE,minute)

            getTime=SimpleDateFormat("HH:mm").format(calendar.time)
            tv_getTime.text=getTime


        }

        TimePickerDialog(context,timeSetListener,calendar.get(Calendar.HOUR_OF_DAY),calendar.get(Calendar.MINUTE),true).show()

    }




}