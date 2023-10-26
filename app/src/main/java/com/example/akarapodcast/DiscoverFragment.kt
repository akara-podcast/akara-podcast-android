package com.example.akarapodcast

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RadioButton
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.viewbinding.ViewBindings
import com.example.akarapodcast.databinding.FragmentDiscoverBinding

class DiscoverFragment : Fragment() {
    private lateinit var binding: FragmentDiscoverBinding
    lateinit var discover: RadioButton
    lateinit var following: RadioButton

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentDiscoverBinding.inflate(inflater, container, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initWidget()

        // add action listener to each radio buttons
        discover.setOnClickListener {
            radioTapped(it)
        }
        following.setOnClickListener {
            radioTapped(it)
        }
    }


    private fun initWidget(){
        discover = binding.discover
        following = binding.following
    }


    private fun radioTapped(view: View){
        var selectedID = view.id
        if (selectedID == R.id.discover){
            updateRadioGroup(discover)
        }
        else{
            updateRadioGroup(following)
        }
    }

    private fun updateRadioGroup(selected: RadioButton){
        discover.background = ContextCompat.getDrawable(requireContext(), R.drawable.radio_off)
        following.background = ContextCompat.getDrawable(requireContext(), R.drawable.radio_off)


        selected.background = ContextCompat.getDrawable(requireContext(), R.drawable.radio_on)
    }
}