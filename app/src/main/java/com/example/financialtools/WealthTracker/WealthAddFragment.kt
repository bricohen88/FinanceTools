package com.example.financialtools.WealthTracker

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.coroutineScope
import androidx.lifecycle.viewModelScope
import androidx.navigation.fragment.findNavController
import com.example.financialtools.FinanceViewModel
import com.example.financialtools.R
import com.example.financialtools.StockViewModel
import com.example.financialtools.StockViewModelFactory
import com.example.financialtools.database.StockApplication
import com.example.financialtools.database.StockEntity
import com.example.financialtools.databinding.FragmentWealthAddBinding
import kotlinx.coroutines.launch

class WealthAddFragment : Fragment() {

    lateinit var binding: FragmentWealthAddBinding
    val viewModel: FinanceViewModel by activityViewModels()
    val stockViewModel: StockViewModel by activityViewModels {
        StockViewModelFactory((activity?.application as StockApplication).database.stockDao())
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentWealthAddBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val inputStream = resources.openRawResource(R.raw.sampleText)
        val bufferedInputStream = inputStream.bufferedReader().use {it.readText()}


        binding.submitStock.setOnClickListener {
            if (binding.stockName.text.isNullOrEmpty()) {
                binding.stockName.error = "Please enter a stock name"
                binding.stockName.requestFocus()
                return@setOnClickListener
            }
            if (binding.stockShares.text.isNullOrEmpty()) {
                binding.stockShares.error = "Please enter number of shares"
                binding.stockShares.requestFocus()
                return@setOnClickListener
            }

            stockViewModel.viewModelScope.launch {
                val stock = listOf(StockEntity(binding.stockName.text.toString(), "", 0.0, 0.0))
                val results = stockViewModel.getStocks(stock)
                if (results.isEmpty()) {
                    binding.stockName.error = "Please check sumbol is valid"
                    binding.stockName.requestFocus()
                } else {
                    val updateStock = results.first()
                    stockViewModel.insertStock(
                        StockEntity(
                            updateStock.symbol,
                            updateStock.shortName,
                            updateStock.price,
                            binding.stockShares.text.toString().toDouble()
                        )
                    )
                }

            }

            val action = WealthAddFragmentDirections.actionWealthAddFragmentToWealthTracker()
            findNavController().navigate(action)
        }
    }
}