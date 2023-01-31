package com.example.testbinlist

import android.app.Activity
import android.content.Context
import android.os.Bundle
import android.view.Display.Mode
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import com.example.testbinlist.databinding.ModelItemBinding
import com.example.testbinlist.jsonpesponse.MyModel1


class AdapterMyModel:RecyclerView.Adapter<AdapterMyModel.ModelHolder>() {
    //private lateinit var mClickListener:OnRecViewItemClickListener
    val modelList= ArrayList<MyModel1>()
    class ModelHolder(item:View):RecyclerView.ViewHolder(item) {
        val binding = ModelItemBinding.bind(item)
        fun bind(model:MyModel1)= with(binding){
            itemTextCountryName.text = model.country.name
            itemTextNameBank.text = model.bank.name
            itemtextBin.text = model.bin.toString()
            itemEmoji.text = model.country.emoji
            itemView.setOnClickListener {
                //фрагмент с данными именно этой модели
                var bundle: Bundle = Bundle()
                bundle.putSerializable("modelItem",model)
                var frag = ResultFragment()
                frag.arguments = bundle
                var activity:AppCompatActivity = it.context as AppCompatActivity
                activity.supportFragmentManager.beginTransaction().replace(R.id.place_holder,frag).commit()
            }
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ModelHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.model_item,parent,false)
        return ModelHolder(view)
    }

    override fun onBindViewHolder(holder: ModelHolder, position: Int) {
        holder.bind(modelList.get(position))
    }

    override fun getItemCount(): Int {
        return modelList.size
    }
    fun addModel(model: MyModel1){
        modelList.add(model)
        notifyDataSetChanged()
    }
    fun addAll(models:ArrayList<MyModel1>){
        for(elem:MyModel1 in models){
            addModel(elem)
        }
    }
}