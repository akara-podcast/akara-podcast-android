package com.example.akarapodcast

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RadioButton
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import com.example.akarapodcast.databinding.FragmentHomeBinding

class HomeFragment : Fragment() {
    private lateinit var binding: FragmentHomeBinding
    private lateinit var discover: RadioButton
    private lateinit var following: RadioButton

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentHomeBinding.inflate(inflater, container, false)
        replaceFragment(DiscoverFragment())
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initWidget()

        // add action listener to each radio buttons
        discover.setOnClickListener {
            radioTapped(it)
            replaceFragment(DiscoverFragment())
        }
        following.setOnClickListener {
            radioTapped(it)
            replaceFragment(FollowingFragment())
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

    private fun replaceFragment(fragment: Fragment){

        // FragmentManager
        val fragmentManager = fragmentManager

        // FragmentTransaction
        val fragmentTransaction = fragmentManager!!.beginTransaction()

        // Replace HomeFragment in lytFragment
        fragmentTransaction.replace(binding.content.id, fragment)

        // Commit transaction
        fragmentTransaction.commit()

    }
}