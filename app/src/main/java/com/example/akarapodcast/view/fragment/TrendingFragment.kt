package com.example.akarapodcast.view.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.akarapodcast.databinding.FragmentTrendingBinding

class TrendingFragment : Fragment() {
    private lateinit var binding: FragmentTrendingBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentTrendingBinding.inflate(inflater, container, false)

        return binding.root
    }

}