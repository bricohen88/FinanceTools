package com.example.financialtools.Interest

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast.LENGTH_SHORT
import android.widget.Toast.makeText
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.example.financialtools.FinanceViewModel
import com.example.financialtools.databinding.FragmentInterestCalcBinding


class InterestCalc : Fragment() {

    val viewModel: FinanceViewModel by activityViewModels()
    var binding: FragmentInterestCalcBinding? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = FragmentInterestCalcBinding.inflate(inflater, container, false)
        // Inflate the layout for this fragment
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding?.submitInterest?.setOnClickListener {
            val interest = binding?.interestRateInput?.text.toString()
            val amount = binding?.principleInput?.text.toString()
            if (!interest.isNullOrEmpty() && !amount.isNullOrEmpty()) {
                val action =
                    InterestCalcDirections.actionInterestCalcToInterestCalcResult(interest.toInt(), amount.toInt())
                findNavController().navigate(action)
            } else {
                makeText(requireContext(), "Please enter both values", LENGTH_SHORT).show()
            }

        }


    }
}