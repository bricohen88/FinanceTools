package com.example.financialtools

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Context
import android.os.Bundle
import android.renderscript.ScriptGroup
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.fragment.app.activityViewModels
import com.example.financialtools.databinding.FragmentRetireCalcBinding
import androidx.core.content.ContextCompat.getSystemService


class RetireCalc : Fragment() {

    lateinit var binding : FragmentRetireCalcBinding
    val viewModel: FinanceViewModel by activityViewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentRetireCalcBinding.inflate(inflater, container, false)
        return binding.root
    }

    @SuppressLint("StringFormatInvalid")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        updateGoalSwitch()

        binding.submitRetire.setOnClickListener {
            val currentWealth = binding.currentWealthText.text.toString()
            val monthlySaving = binding.monthlySavingText.text.toString()
            val goal = binding.retireGoalText.text.toString()

            if (currentWealth.isNullOrEmpty() || monthlySaving.isNullOrEmpty() || goal.isNullOrEmpty()) {
                Toast.makeText(requireContext(), "Please enter all values", Toast.LENGTH_SHORT).show()
            } else {

                val retirementYear = viewModel.calcRetirement(
                    currentWealth.toInt(),
                    monthlySaving.toInt(),
                    goal.toInt(),
                    binding?.retireGoalSwitch.isChecked
                )

                binding.retirementYear7.text =
                    getString(R.string.retire_answer_seven, 7, retirementYear[0])
                binding.retirementYear10.text =
                    getString(R.string.retire_answer_seven, 10, retirementYear[1])
                binding.retirementYear13.text =
                    getString(R.string.retire_answer_seven, 13, retirementYear[2])
            }
            //Hide the keyboard
            val inputManager = requireContext().getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager
            binding.currentWealthText.requestFocus()
            inputManager.hideSoftInputFromWindow(binding.currentWealthText.windowToken, InputMethodManager.HIDE_NOT_ALWAYS)


        }

        binding.retireGoalSwitch.setOnClickListener {
            updateGoalSwitch()
        }




    }

    private fun updateGoalSwitch() {
        binding?.retireGoalLayout?.hint = if (binding.retireGoalSwitch.isChecked) {
             "Monthly Income Goal"
        } else {
            "Total Savings Goal"
        }
    }

}