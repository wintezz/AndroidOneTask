package com.example.androidonetask.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.androidonetask.adapter.NewsFragmentAdapter
import com.example.androidonetask.databinding.FragmentNewsBinding

class NewsFragment : Fragment(){

    private lateinit var binding: FragmentNewsBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentNewsBinding
            .inflate(
                inflater, container,
                false
            )

        activity?.title = "NewsFragment"

        binding.recViewNews.layoutManager = LinearLayoutManager(activity)
        binding.recViewNews.adapter = NewsFragmentAdapter(fillList())

        return binding.root
    }

    private fun fillList(): List<String> {
        val data = mutableListOf<String>()
        (0..1000).forEach { i -> data.add("$i") }
        return data
    }

    /*override fun Listener(elements: List<String>, position: Int) {
        parentFragmentManager.beginTransaction()
            .apply {
                replace(R.id.hostFragment, PostFragment)
                commit()
            }

    }*/

  /*  companion object {
        @JvmStatic
        fun newInstance(someInt: Int) =
            PostFragment().apply {
                arguments = Bundle().apply {
                    putInt("Int", someInt)
                }
            }
    }*/

}