package com.example.testbinlist

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import androidx.fragment.app.activityViewModels
import com.example.testbinlist.databinding.FragmentResultBinding
import com.example.testbinlist.jsonpesponse.DataModelforFragments
import com.example.testbinlist.jsonpesponse.MyModel1

class ResultFragment : Fragment() {
    lateinit var binding: FragmentResultBinding
    private val dataModelforFragments:DataModelforFragments by activityViewModels()
    lateinit var intent: Intent
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentResultBinding.inflate(inflater)
        return binding.root}

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        System.out.println("fragment result")
        System.out.println(arguments?.get("model"))
        System.out.println(arguments?.get("modelItem"))
        init()
        if (arguments?.get("model")!= null){
            dataModelforFragments.result_search_Mymodel.value = arguments?.get("model") as MyModel1?
        }

    }
    fun init(){try {
        var m = arguments?.get("model")
        if(m==null){m=arguments?.get("modelItem")}
        var mtest = arguments?.get("modelItem")
        System.out.println("TESTITEM")
        var i: MyModel1 = m as MyModel1
        System.out.println(m.country.name)
        binding.apply {
            textBin.text= i.bin.toString()
            textCardNumberLenght.text = i.number.length.toString()
            textCardNumberLunghr.text = i.number.lugn.toString()
            textscheme.text = i.scheme
            textType.text = i.type
            textBrand.text = i.brand
            textPrepaid.text = i.prepaid.toString()
            textBankName.text = i.bank.name
            textBankCIty.text = i.bank.city
            textBankUrl.text = i.bank.url
            textBankPhone.text = i.bank.phone
            textBankPhone.linksClickable
            textCountryName.text = i.country.name
            textCountryEmoji.text = i.country.emoji
            textCountryLatitude.text=i.country.latitude.toString()
            textCountryLongitude.text = i.country.longitude.toString()
            textBankPhone.setOnClickListener {
                intent = Intent(Intent.ACTION_DIAL)
                intent.setData(Uri.parse("tel:${textBankPhone.text}"))
                startActivity(intent)
            }
            textBankUrl.setOnClickListener {
                intent = Intent(Intent.ACTION_VIEW, Uri.parse("http://${textBankUrl.text}"))
                startActivity(intent)
            }
            textBankCIty.setOnClickListener {
                intent =Intent(Intent.ACTION_VIEW)
                intent.setData(Uri.parse("geo:${textCountryLatitude.text},${textCountryLongitude.text}"))
                startActivity(intent)
            }
            textCountryName.setOnClickListener {
                intent =Intent(Intent.ACTION_VIEW)
                intent.setData(Uri.parse("geo:${textCountryLatitude.text},${textCountryLongitude.text}"))
                startActivity(intent)

            }
            btnNav.setOnClickListener {
                var act = activity as MainActivity
                act.openDrawer()
            }

        }
    }catch (ex:Exception){}}

    companion object {
       @JvmStatic
        fun newInstance() = ResultFragment()
    }
}