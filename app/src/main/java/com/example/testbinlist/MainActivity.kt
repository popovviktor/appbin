package com.example.testbinlist


import android.content.SharedPreferences
import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import com.example.testbinlist.databinding.ActivityMainBinding
import com.example.testbinlist.jsonpesponse.*
import com.google.gson.Gson
import java.io.BufferedReader
import java.io.File
import java.io.InputStreamReader
import java.net.HttpURLConnection
import java.net.URL

class MainActivity : AppCompatActivity(){
    var pref:SharedPreferences? = null
    private val dataModelforFragments:DataModelforFragments by viewModels()
    lateinit var binding2:ActivityMainBinding
    lateinit var file:File
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding2 = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding2.root)
        pref = getSharedPreferences("Table_history", MODE_PRIVATE)
        supportFragmentManager.beginTransaction().replace(R.id.place_holder,MainFragment()).commit()
        dataModelforFragments.result_search_String.value = readData()
        dataModelforFragments.result_search_Mymodel.observe(this,{
            savedata(it)
            dataModelforFragments.result_search_String.value = readData() as String
            System.out.println("Данные в ситорию записаны")
            System.out.println(readData())

        })
        System.out.println("sss")
        System.out.println(dataModelforFragments.result_search_String.value)
        System.out.println("sss")
        binding2.navView.setNavigationItemSelectedListener {
            when(it.itemId){
                R.id.id_search->{
                    supportFragmentManager.beginTransaction().replace(R.id.place_holder,MainFragment()).commit()
                    var dr  = binding2.drawL as DrawerLayout
                    dr.closeDrawer(GravityCompat.START)
                }
                R.id.id_history->{
                    supportFragmentManager?.beginTransaction()?.replace(R.id.place_holder,HistoryFragment())?.commit()
                    var dr  = binding2.drawL as DrawerLayout
                    dr.closeDrawer(GravityCompat.START)
                }
                R.id.id_clear->{
                    clear()
                    if(supportFragmentManager.fragments[0].toString().indexOf("History")>-1){
                        supportFragmentManager.beginTransaction().replace(R.id.place_holder,HistoryFragment()).commit()
                    }
                    var dr  = binding2.drawL as DrawerLayout
                    dr.closeDrawer(GravityCompat.START)
                    Toast.makeText(this@MainActivity,"История очищена",Toast.LENGTH_SHORT).show()
                }

            }

            true
        }

    }

    fun savedata(res:MyModel1){
        val editor = pref?.edit()
        var gson = Gson()
        var strjson = gson.toJson(res)
        var str:String = pref?.getString("lastModel","")!!
        str+=strjson
        editor?.putString("lastModel",str+"LLLL")
        editor?.apply()
        System.out.println("Сохранится в данных"+str)



    }
    fun readData():String{
        var str:String = pref?.getString("lastModel","")!!
        System.out.println("Чтение данных произошло")
        return str;
    }
    fun clear(){
        val editor = pref?.edit()
        editor?.clear()
        editor?.apply()
        dataModelforFragments.result_search_String.value = null
    }
    fun openDrawer(){
        var dr = binding2.drawL as DrawerLayout
        dr.openDrawer(GravityCompat.START)
    }
}