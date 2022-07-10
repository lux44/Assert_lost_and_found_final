package com.lux.assert_lost_and_found

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.fragment.app.Fragment
import com.lux.assert_lost_and_found.databinding.ActivitySecondBinding

class SecondActivity : AppCompatActivity() {
    val binding:ActivitySecondBinding by lazy { ActivitySecondBinding.inflate(layoutInflater) }

    val fragments:MutableList<Fragment> = mutableListOf()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //setContentView(R.layout.activity_second)
        setContentView(binding.root)

        setSupportActionBar(binding.toolbar)
        supportActionBar?.setDisplayShowTitleEnabled(false)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        fragments.add(PoliceFragment())
        fragments.add(TaxiFragment())
        fragments.add(ListFragment())

        supportFragmentManager.beginTransaction().add(R.id.container,fragments[0]).commit()
        binding.bnv.setOnItemSelectedListener {

            supportFragmentManager.fragments.forEach {
                supportFragmentManager.beginTransaction().hide(it).commit()
            }

            val tran=supportFragmentManager.beginTransaction()
            when(it.itemId){
                R.id.bnv_police->{
                    tran.show(fragments[0])
                }
                R.id.bnv_bus_or_taxi->{
                    if (!supportFragmentManager.fragments.contains(fragments[1])) tran.add(R.id.container,fragments[1])
                    tran.show(fragments[1])
                }
                R.id.bnv_list->{
                    if (!supportFragmentManager.fragments.contains(fragments[2])) tran.add(R.id.container,fragments[2])
                    tran.show(fragments[2])
                }
            }
            tran.commit()
            true
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return super.onSupportNavigateUp()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.option_menu,menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            R.id.option_user->{

            }
            R.id.option_chat->{

            }
        }
        return super.onOptionsItemSelected(item)
    }
}