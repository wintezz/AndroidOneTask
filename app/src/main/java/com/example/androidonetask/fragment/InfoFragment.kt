package com.example.androidonetask.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.androidonetask.R
import com.example.androidonetask.adapter.InfoFragmentAdapter
import com.example.androidonetask.databinding.FragmentInfoBinding
import com.example.androidonetask.utils.ClickListenerDetail
import com.example.androidonetask.utils.RankElement

class InfoFragment : Fragment(), ClickListenerDetail {

    private lateinit var binding: FragmentInfoBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentInfoBinding
            .inflate(
                inflater, container,
                false
            )

        activity?.title = "InfoFragment"

        binding.recViewInfo.layoutManager = LinearLayoutManager(activity)
        binding.recViewInfo.adapter = InfoFragmentAdapter(
            RankElement.fillList(),
            this@InfoFragment
        )

        return binding.root
    }

    override fun onClickView(view: View) {
        parentFragmentManager.beginTransaction().apply {
            replace(R.id.hostFragment, DetailFragment())
            addToBackStack(null)
            commit()

        }
    }
}