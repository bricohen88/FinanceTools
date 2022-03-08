package com.example.financialtools

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.financialtools.WealthTracker.StockResult
import com.example.financialtools.WealthTracker.StockData
import com.example.financialtools.WealthTracker.YahooFinanceApi
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import kotlin.math.log10

const val MONTHS = 12

class FinanceViewModel: ViewModel() {


    fun calcRetirement(current: Int, monthlySaving: Int, goal: Int, goalType: Boolean) : MutableList<Double> {

        val years = mutableListOf<Double>()
        for(interest in listOf(7,10,13)) {
            val monthlyInterestRate: Float = interest / (100.0f * MONTHS)
            var goalTotal: Double = if (goalType) { goal * 25.0 * MONTHS }
            else { goal * 1.0 }

            val numerator = log10((goalTotal + monthlySaving/monthlyInterestRate ) / ( current + monthlySaving / monthlyInterestRate))
            val denominator =  MONTHS * log10(1 + monthlyInterestRate)
            years.add(numerator/denominator)
        }
        return years
    }

}