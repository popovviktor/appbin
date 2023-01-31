package com.example.testbinlist

import com.example.testbinlist.jsonpesponse.Bank
import com.example.testbinlist.jsonpesponse.Country
import com.example.testbinlist.jsonpesponse.MyModel1
import com.example.testbinlist.jsonpesponse.Number2
import com.google.gson.GsonBuilder
import org.json.JSONObject
import java.io.*
import java.net.HttpURLConnection
import java.net.URL

class Controller {
    fun createGetRequestandCreateModel(numberEightCard:Int):MyModel1 {
        var reader: BufferedReader? = null
        //try {
        var numlist: Int = numberEightCard//throws EXepcetion
        val url = URL("https://lookup.binlist.net/" + "$numlist")
        val con: HttpURLConnection = url.openConnection() as HttpURLConnection
        con.setRequestMethod("GET")
        con.setReadTimeout(1000)
        con.connect()
        reader = BufferedReader(InputStreamReader(con.getInputStream()))
        val buf = StringBuilder()
        var line: String? = null
        while (reader!!.readLine().also { line = it } != null) {
            buf.append(
                """
                $line
                
                """.trimIndent()
            )
        }
        con.disconnect()

        var jsonObj = JSONObject(buf.toString())

        val builder = GsonBuilder()
        val gson = builder.create()
        var info: MyModel1 = gson.fromJson(buf.toString(), MyModel1::class.java)
        info.bin = numlist
        info.country = gson.fromJson(jsonObj["country"].toString(), Country::class.java)
        info.number = gson.fromJson(jsonObj["number"].toString(), Number2::class.java)
        info.bank = gson.fromJson(jsonObj["bank"].toString(), Bank::class.java)

        if (reader != null) {
            reader!!.close()
        }
    return info;}
    fun deserializationStringtoArrayModels(arrstr:String,arrmodels:ArrayList<MyModel1>){
        arrmodels.clear()
        val arrstrsplit = arrstr.split("LLLL")
        for (buf:String in arrstrsplit){
            var jsonObj = JSONObject(buf.toString())
            val builder = GsonBuilder()
            val gson = builder.create()
            var info: MyModel1 = gson.fromJson(buf.toString(), MyModel1::class.java)

            info.country = gson.fromJson(jsonObj["country"].toString(), Country::class.java)
            info.number = gson.fromJson(jsonObj["number"].toString(), Number2::class.java)
            info.bank = gson.fromJson(jsonObj["bank"].toString(), Bank::class.java)
            arrmodels.add(info)

        }
    }

    }