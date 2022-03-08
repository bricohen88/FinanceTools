package com.example.financialtools.Interest

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.financialtools.R
import com.example.financialtools.databinding.FragmentInterestCalcResultBinding

private const val TAG = "interestResult"

class InterestCalcResult : Fragment() {

    private var binding: FragmentInterestCalcResultBinding? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentInterestCalcResultBinding.inflate(inflater,container,false)
        return binding?.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val safeArgs: InterestCalcResultArgs by navArgs()
        val interestRate = safeArgs.interestRate
        val startingAmount = safeArgs.startingAmount
        binding?.interestResultText?.text = getString(R.string.interest_input_string, startingAmount, interestRate)
        binding?.interestRecycleView?.adapter = InterestAdapter(requireContext(),interestRate,startingAmount)
        val divider = DividerItemDecoration(requireContext(), LinearLayoutManager.VERTICAL)
        binding?.interestRecycleView?.addItemDecoration(divider)


    }
}