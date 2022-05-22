package com.admin.budgetcalculator

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.widget.doAfterTextChanged
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.admin.budgetcalculator.databinding.FragmentMainBinding
import com.google.android.material.textfield.TextInputEditText

class MainFragment : Fragment() {


    private var _binding: FragmentMainBinding? = null
    private val binding get() = _binding!!

    private var salary = 0f
    private var internetBill = 0f
    private var cellPhoneBill = 0f
    private var waterBill = 0f
    private var electricityBill = 0f
    private var loan = 0f
    private var otherExpenses = 0f
    private var result = 0f


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentMainBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.calculate.setOnClickListener {
            if (binding.incomeEditText.text.isNullOrEmpty()) {
                binding.incomeEditText.error = "you must type your income"
            } else {
                SharedPreferencesServices.storeCalculation(requireActivity(), result.toString())
                findNavController().navigate(R.id.action_mainFragment_to_historyFragment)
            }
        }

        binding.resetButton.setOnClickListener {
            val views = listOf<TextInputEditText>(
                binding.incomeEditText,
                binding.internetBillEditText,
                binding.cellPhoneEditText,
                binding.waterBillEditText,
                binding.electricityBillEditText,
                binding.loanEditText,
                binding.otherEditText
            )

            views.forEach { it.text?.clear() }
        }

        binding.incomeEditText.doAfterTextChanged {
            salary = if (it?.isNotEmpty() == true) {
                it.toString().toFloat()
            } else {
                0f
            }

            refreshResultText()
        }
        binding.internetBillEditText.doAfterTextChanged {
            internetBill = if (it?.isNotEmpty() == true) {
                it.toString().toFloat()
            } else 0f
            refreshResultText()
        }
        binding.cellPhoneEditText.doAfterTextChanged {
            cellPhoneBill = if (it?.isNotEmpty() == true) {
                it.toString().toFloat()
            } else {
                0f
            }
            refreshResultText()
        }
        binding.waterBillEditText.doAfterTextChanged {
            waterBill = if (it?.isNotEmpty() == true) {
                it.toString().toFloat()
            } else 0f
            refreshResultText()
        }
        binding.electricityBillEditText.doAfterTextChanged {
            electricityBill = if (it?.isNotEmpty() == true) {
                it.toString().toFloat()
            } else 0f
            refreshResultText()
        }

        binding.loanEditText.doAfterTextChanged {
            loan = if (it?.isNotEmpty() == true) {
                it.toString().toFloat()
            } else 0f
            refreshResultText()
        }

        binding.otherEditText.doAfterTextChanged {
            otherExpenses = if (it?.isNotEmpty() == true) {
                it.toString().toFloat()
            } else 0f
            refreshResultText()
        }


    }

    private fun refreshResultText() {
        result = salary - internetBill - cellPhoneBill - waterBill - electricityBill - loan - otherExpenses
        binding.resultText.text = "The Rest is $result"
    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}