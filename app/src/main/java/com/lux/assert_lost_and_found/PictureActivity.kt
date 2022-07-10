package com.lux.assert_lost_and_found

import android.content.Intent
import android.database.Cursor
import android.net.Uri
import android.opengl.Visibility
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.MediaStore
import android.view.View
import android.widget.Toast
import androidx.activity.result.ActivityResultCallback
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContract
import androidx.activity.result.contract.ActivityResultContracts
import androidx.loader.content.CursorLoader
import com.bumptech.glide.Glide
import com.lux.assert_lost_and_found.databinding.ActivityPictureBinding
import gun0912.tedimagepicker.builder.TedImagePicker
import gun0912.tedimagepicker.builder.type.MediaType

class PictureActivity : AppCompatActivity() {

    val binding:ActivityPictureBinding by lazy { ActivityPictureBinding.inflate(layoutInflater) }

    var selectedUri: List<Uri>? = null




    val datas:Bundle by lazy { intent.extras?:Bundle() }


    var lostImgPath:Array<String> = arrayOf<String>("","")


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //setContentView(R.layout.activity_picture)
        setContentView(binding.root)


        binding.btnBack.setOnClickListener {
            onBackPressed()
        }
        binding.btnNext.setOnClickListener {
            val intent:Intent= Intent(this,ProductLocationActivity::class.java)
            if (!lostImgPath[0].equals("")){
                datas.putStringArray("lostImgPath",lostImgPath)
                intent.putExtras(datas)
                startActivity(intent)
            }else {
                Toast.makeText(this, "사진 1장 이상은 꼭 선택하셔야 합니다!", Toast.LENGTH_SHORT).show()
            }


        }
        binding.ivRegisterPicture.setOnClickListener {
            TedImagePicker.with(this)
                .mediaType(MediaType.IMAGE)
                .selectedUri(selectedUri)
                .max(2,"이미지 선택은 2장까지 가능합니다.")
                .startMultiImage { list:List<Uri>->
                    showMultiImage(list)
                    selectedUri=list
                    //Toast.makeText(this, "${list.size}", Toast.LENGTH_SHORT).show()
                    for (i in list.indices){
                        lostImgPath[i]=getRealPathFromUri(list[i])
                    }
                    Toast.makeText(this, "${lostImgPath[0]}", Toast.LENGTH_SHORT).show()
                }

        }
    }

    fun getRealPathFromUri(uri:Uri) : String{
        val proj= arrayOf(MediaStore.Images.Media.DATA)
        val loader:CursorLoader=CursorLoader(this,uri,proj,null,null,null)
        val cursor: Cursor? =loader.loadInBackground()
        val column_index:Int= cursor!!.getColumnIndexOrThrow(MediaStore.Images.Media.DATA)
        cursor.moveToFirst()
        val result:String=cursor.getString(column_index)
        cursor.close()
        return result
    }
    fun showMultiImage(list:List<Uri>) : List<Uri>{
        this.selectedUri=list

        when(list.size){
            1->{
                binding.ivRegisterPicture.visibility=View.INVISIBLE
                binding.ivPicture1.visibility=View.VISIBLE
                binding.ivPicture2.visibility=View.INVISIBLE
                Glide.with(this).load(list[0]).into(binding.ivPicture1)
            }
            2->{
                binding.ivRegisterPicture.visibility=View.INVISIBLE
                binding.ivPicture1.visibility=View.VISIBLE
                binding.ivPicture2.visibility=View.VISIBLE
                Glide.with(this).load(list[0]).into(binding.ivPicture1)
                Glide.with(this).load(list[1]).into(binding.ivPicture2)
            }
        }
        return list
    }




}