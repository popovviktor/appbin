package com.example.testbinlist

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.testbinlist.databinding.FragmentHistoryBinding
import com.example.testbinlist.jsonpesponse.DataModelforFragments
import com.example.testbinlist.jsonpesponse.MyModel1


class HistoryFragment : Fragment() {
    lateinit var binding: FragmentHistoryBinding
    private val dataModelforFragments: DataModelforFragments by activityViewModels()
    private val adapter = AdapterMyModel()
    var arrmodels = ArrayList<MyModel1>()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentHistoryBinding.inflate(inflater)
        return binding.root
    }
    private fun init(){
        try {
            val i = dataModelforFragments.result_search_String.value
            var strread: String = i as String
            System.out.println("HistoryActivity")
            var c = Controller()
            c.deserializationStringtoArrayModels(strread,arrmodels)

        }catch (ex:Exception){System.out.println("История запросов пуста")}
        if (arrmodels.size>0){
            binding.apply {
                rcview.layoutManager= LinearLayoutManager(this@HistoryFragment.activity)
                rcview.adapter = adapter
                System.out.println(arrmodels.size)
                System.out.println(arrmodels.get(0).country.name)
                System.out.println(arrmodels.size)

                adapter.addAll(arrmodels)//подумать как подгрудать модели в адаптрер и змэйнкода
            }}else{ System.out.println("Истоия раельональ пуста")}
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        init()

    }

    companion object {

        @JvmStatic
        fun newInstance() = HistoryFragment()
    }
}