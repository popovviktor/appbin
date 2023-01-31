package com.example.testbinlist

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.testbinlist.databinding.FragmentMainBinding
import com.example.testbinlist.jsonpesponse.MyModel1
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MainFragment : Fragment() {

    lateinit var binding: FragmentMainBinding
    lateinit var mContext:Context
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentMainBinding.inflate(inflater)
        mContext = inflater.context
        return binding.root
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        //mContext = context
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        binding.fragMainBtnSearch.setOnClickListener{
            GlobalScope.launch {
                var Mmmm: MyModel1;
                try {
                    var c = Controller()
                    System.out.println("Попытка получить геоданные")
                    var resultSearch = c.createGetRequestandCreateModel(binding.textsearchfrag.text.toString().toInt())
                    System.out.println(resultSearch)
                    var bundle:Bundle = Bundle()
                    bundle.putSerializable("model",resultSearch)
                    var frag2 = ResultFragment()
                    frag2.arguments = bundle
                    activity?.supportFragmentManager?.beginTransaction()?.replace(R.id.place_holder,frag2)?.commit()
                }catch (ex:Exception){
                    System.out.println("не корректные данные")
                    val data = withContext(Dispatchers.Main){
                        uncurrentIn()
                    }
                }}
            }
        binding.btnHistory.setOnClickListener{
            activity?.supportFragmentManager?.beginTransaction()?.replace(R.id.place_holder,HistoryFragment())?.commit()
        }
        binding.btnOpenDraw.setOnClickListener{
            var act = activity as MainActivity
            act.openDrawer()
        }
    }
    fun uncurrentIn(){
        Toast.makeText(activity?.applicationContext,MyException().toString(),Toast.LENGTH_SHORT).show()
    }

    companion object {
         @JvmStatic
        fun newInstance() = MainFragment()
    }
}