package com.example.androidonetask.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.androidonetask.R
import com.example.androidonetask.adapter.NewsAdapter
import com.example.androidonetask.databinding.FragmentArtistBinding
import com.example.androidonetask.utils.RankElement

class NewsFragment : Fragment() {

    private var _binding: FragmentArtistBinding? = null
    private val binding get() = _binding!!
    private var adapter = NewsAdapter({ onClickItem(it) }, { onClickView() }, { onClickArtist() })

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentArtistBinding
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
        binding.recView.layoutManager = LinearLayoutManager(context)
        binding.recView.adapter = adapter
        adapter.updateList(RankElement.fillList())
    }

    /*private fun onClickItem(position: Int) {
        parentFragmentManager.beginTransaction().apply {
            replace(R.id.nav_host_fragment, PostFragment.newInstance(position))
            addToBackStack(null)
            commit()
        }
    }*/

    private fun onClickItem(position: Int) {
        findNavController().navigate(R.id.action_newsFragment_to_postFragment)
        bundleOf(PostFragment.POSITION_KEY to position)
    }

    private fun onClickView() {
        findNavController().navigate(R.id.action_newsFragment_to_imageViewActivity)
    }

    private fun onClickArtist() {
        findNavController().navigate(R.id.action_newsFragment_to_artistFragment)
    }

    override fun onDestroyView() {
        _binding = null
        super.onDestroyView()
    }
}
