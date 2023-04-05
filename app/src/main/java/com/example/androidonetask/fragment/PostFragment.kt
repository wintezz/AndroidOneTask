package com.example.androidonetask.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.androidonetask.databinding.FragmentPostBinding

class PostFragment : Fragment() {

    private lateinit var binding: FragmentPostBinding

    /*private val int = arguments?.getInt("Int") as Int*/
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentPostBinding
            .inflate( inflater,container,
                false)

        activity?.title = "PostFragment"

        return binding.root
    }

    /*companion object {
        @JvmStatic
        fun newInstance(someInt: Int) =
            PostFragment().apply {
                arguments = Bundle().apply {
                    putInt("Int", someInt)
                }
            }
    }*/

}