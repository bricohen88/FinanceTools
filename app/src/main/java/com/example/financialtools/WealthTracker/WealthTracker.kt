package com.example.financialtools.WealthTracker

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.viewModelScope
import androidx.navigation.fragment.findNavController
import com.example.financialtools.FinanceViewModel
import com.example.financialtools.StockViewModel
import com.example.financialtools.StockViewModelFactory
import com.example.financialtools.database.StockApplication
import com.example.financialtools.databinding.FragmentWealthTrackerBinding
import kotlinx.android.synthetic.main.fragment_wealth_tracker.*
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch


class WealthTracker : Fragment() {

    lateinit var binding: FragmentWealthTrackerBinding
    val viewModel: FinanceViewModel by activityViewModels()
    val stockViewModel: StockViewModel by activityViewModels {
        StockViewModelFactory(
            (activity?.application as StockApplication).database.stockDao()
        )
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.d("WealthTracker","get stock info requested from wealth tracker")

        stockViewModel.viewModelScope.launch {
            val currentStocks = stockViewModel.retrieveStocksDb()
            val updateStocks = stockViewModel.getStocks(currentStocks)
            stockViewModel.updateDbPrices(updateStocks)
        }

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentWealthTrackerBinding.inflate(inflater, container, false)

        val adapter = StockAdapter()
        stockViewModel.viewModelScope.launch {
            stockViewModel.getAll().collect {
                adapter.submitList(it)

            }
        }

        val recyclerView = binding.stockRecyclerview
        recyclerView.adapter = adapter

        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.wealthAddButton.setOnClickListener {
            val action = WealthTrackerDirections.actionWealthTrackerToWealthAddFragment()
            findNavController().navigate(action)
        }

        binding.update.setOnClickListener {
            stockViewModel.viewModelScope.launch {
                val currentStocks = stockViewModel.retrieveStocksDb()
                val updateStocks = stockViewModel.getStocks(currentStocks)
                stockViewModel.updateDbPrices(updateStocks)

            }

        }




    }
}