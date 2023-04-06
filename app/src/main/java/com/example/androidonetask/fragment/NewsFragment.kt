package com.example.androidonetask.fragment

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.androidonetask.R
import com.example.androidonetask.activity.ImageViewActivity
import com.example.androidonetask.adapter.NewsFragmentAdapter
import com.example.androidonetask.databinding.FragmentNewsBinding
import com.example.androidonetask.utils.ClickListener
import com.example.androidonetask.utils.RankElement

class NewsFragment : Fragment(), ClickListener {

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

        initRecyclerView()

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        activity?.title = this.javaClass.simpleName
    }

    private fun initRecyclerView() {
        binding.recViewNews.layoutManager = LinearLayoutManager(context)
        binding.recViewNews.adapter = NewsFragmentAdapter(
            RankElement.fillList(), this@NewsFragment
        )
    }

    override fun onClickItem(position: Int) {
        val postFragment = PostFragment()
        val bundle = Bundle()
        bundle.putString("key", position.toString())
        postFragment.arguments = bundle
        parentFragmentManager.beginTransaction().apply {
            replace(R.id.hostFragment, postFragment)
            addToBackStack(null)
            commit()
        }
    }

    override fun onClickView(view: View) {
        val intent = Intent(requireContext(), ImageViewActivity::class.java)
        startActivity(intent)
    }
}
