package com.example.testbinlist.jsonpesponse


data class MyModel1 (var bin:Int,var number: Number2,var scheme:String,var type:String,var debit:String,var brand:String,
var prepaid:Boolean,var country: Country,var bank: Bank):java.io.Serializable{
    override fun toString(): String {
        return "MyModel1(bin=${bin.toString()},number=${number.toString()}, scheme='$scheme', type='$type', debit='$debit', brand='$brand', prepaid=$prepaid, country=${country.toString()}, bank=${bank.toString()})"
    }
}
class Number2(var length:Int,var lugn:Boolean):java.io.Serializable{
    override fun toString(): String {
        return "Number2(length=$length, lugn=$lugn)"
    }
}
class Country(var numeric:Int,var alpa2:String,var name: String,var emoji:String,var currency:String,var latitude:Int,var longitude:Int):java.io.Serializable{
    override fun toString(): String {
        return "Country(numeric=$numeric, alpa2='$alpa2', name='$name', emoji='$emoji', currency='$currency', latitude=$latitude, longitude=$longitude)"
    }
}
class Bank(var name: String,var url:String,var phone:String,var city:String):java.io.Serializable{
    override fun toString(): String {
        return "Bank(name='$name', url='$url', phone='$phone', city='$city')"
    }
}