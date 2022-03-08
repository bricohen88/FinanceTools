package com.example.financialtools.Interest

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.financialtools.R
import java.text.NumberFormat
import java.util.*
import kotlin.math.pow

class InterestAdapter(
    private val context: Context,
    private val interest: Int,
    private val starting_amount: Int
): RecyclerView.Adapter<InterestAdapter.InterestViewHolder>() {


    class InterestViewHolder(private val view: View): RecyclerView.ViewHolder(view) {
        val yearTextView: TextView = view.findViewById(R.id.item_year)
        val amountTextView: TextView = view.findViewById(R.id.item_amount)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): InterestViewHolder {
        val adapterLayout = LayoutInflater.from(parent.context)
            .inflate(R.layout.interest_item,parent,false)


        return InterestViewHolder(adapterLayout)

    }

    override fun onBindViewHolder(holder: InterestViewHolder, position: Int) {
        holder.yearTextView.text = position.toString()
        val format = NumberFormat.getCurrencyInstance(Locale.UK)
        holder.amountTextView.text = format.format(calcInt(interest, starting_amount, position)).toString()

    }

    override fun getItemCount(): Int {
        return 50
    }

    private fun calcInt(interest: Int, amount: Int, year: Int): Double {
        Log.d("InterestAdapter","calc year $year")
        return amount * (1 + interest/(12*100.0)).pow(12*year)
    }
}