package com.example.testbinlist.jsonpesponse

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class DataModelforFragments:ViewModel() {
    //for fragmentresult
    val result_search_Mymodel:MutableLiveData<MyModel1> by lazy {
        MutableLiveData<MyModel1>()
    }
    //for SharePrefernces
    val result_search_String:MutableLiveData<String> by lazy {
        MutableLiveData<String>()
    }

}