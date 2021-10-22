package com.yricky.dogs.ui.adapter

import android.app.Dialog
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.google.gson.reflect.TypeToken
import com.yricky.android.utils.ThreadUtils
import com.yricky.dogs.databinding.ItemDogBinding
import com.yricky.dogs.dogsDir
import com.yricky.dogs.gson
import com.yricky.dogs.pojo.Dog
import java.io.File
import java.text.SimpleDateFormat
import java.util.*
import kotlin.collections.ArrayList

/**
 * @author  Yricky
 * @date  2021/10/22 下午7:33
 */
class DogListAdapter: RecyclerView.Adapter<DogListAdapter.ViewHolder>() {
    companion object{
        val df = SimpleDateFormat.getDateTimeInstance()
    }
    var onItemClickListener:((Dog,DogListAdapter)->Unit)? = null
    private val list:MutableList<Dog> by lazy{
        try{
            gson.fromJson(File(dogsDir,"dogs.json").reader(Charsets.UTF_8),object :
                TypeToken<ArrayList<Dog>>() {}.type)
        }catch (e:Throwable){
            ArrayList<Dog>(100).also{ arr ->
                (1..100).forEach {
                    arr.add(Dog().apply {
                        name = "Dog:$it"
                        date = System.currentTimeMillis()
                    })
                }
                File(dogsDir,"dogs.json").writeBytes(gson.toJson(arr).toByteArray(Charsets.UTF_8))
            }
        }

    }

    fun notifyItemChanged(dog:Dog){
        val index = list.indexOf(dog)
        if(index >= 0){
            notifyItemChanged(index)
        }
    }

    inner class ViewHolder(private val binding: ItemDogBinding) : RecyclerView.ViewHolder(binding.root) {
        private lateinit var d:Dog
        fun bindDog(dog:Dog){
            d = dog
            binding.tvDogName.text = d.name
            binding.tvDogDate.text = df.format(Date(d.date))
            binding.root.apply {
                setOnClickListener {
                    onItemClickListener?.invoke(d,this@DogListAdapter)
                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val b = ItemDogBinding.inflate(
            LayoutInflater.from(parent.context),parent,false
        )
        return ViewHolder(b)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindDog(list[position])
    }

    override fun getItemCount(): Int {
        return list.size
    }


    fun save(){
        ThreadUtils.postOnIOThread{
            File(dogsDir,"dogs.json").writeBytes(gson.toJson(list).toByteArray(Charsets.UTF_8))
        }
    }

}