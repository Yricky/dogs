package com.yricky.dogs.ui.fragment

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.yricky.dogs.DogApp
import com.yricky.dogs.ui.activity.DogEditActivity
import com.yricky.dogs.ui.adapter.DogListAdapter

/**
 * @author  Yricky
 * @date  2021/10/22 下午7:33
 */
class DogListFragment:Fragment() {
    private val mAdapter by lazy{
        DogListAdapter()
    }
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return RecyclerView(requireContext()).apply {
            overScrollMode = View.OVER_SCROLL_NEVER
            post {
                layoutManager = getGridLayoutManager(300)
                adapter = mAdapter.also {
                    it.onItemClickListener = { dog,_ ->
                        DogApp.app.currEditDog = dog
                        startActivity(Intent(requireActivity(), DogEditActivity::class.java))
                    }
                }
            }
        }
    }

    override fun onResume() {
        DogApp.app.currEditDog?.let{
            mAdapter.notifyItemChanged(it)
        }
        DogApp.app.currEditDog = null
        super.onResume()
    }

    override fun onPause() {
        super.onPause()
        mAdapter.save()
    }
}

fun RecyclerView.getGridLayoutManager(itemWidth:Int): GridLayoutManager {
    val dm = resources.displayMetrics
    val density = dm.density // 屏幕密度（0.75 / 1.0 / 1.5）
    // 屏幕宽度算法:屏幕宽度（像素）/屏幕密度
    val screenWidth = (width / density).toInt() // 屏幕宽度(dp)
    return GridLayoutManager(context, (screenWidth / itemWidth).coerceAtLeast(1))
}