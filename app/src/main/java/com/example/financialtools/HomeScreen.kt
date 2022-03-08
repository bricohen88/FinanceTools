package com.example.financialtools

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.financialtools.databinding.FragmentHomeScreenBinding


class Home : Fragment() {

    var binding: FragmentHomeScreenBinding? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentHomeScreenBinding.inflate(inflater, container, false)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding?.apply {
            launchInterestCalc?.setOnClickListener {
                val action = HomeDirections.actionHome2ToInterestCalc()
                findNavController().navigate(action)
            }
            launchRetireCalc?.setOnClickListener {
                val action = HomeDirections.actionHome2ToRetireCalc()
                findNavController().navigate(action)
            }
            launchWealthTracker?.setOnClickListener {
                val action = HomeDirections.actionHome2ToWealthTracker()
                findNavController().navigate(action)
            }
        }
    }

}
