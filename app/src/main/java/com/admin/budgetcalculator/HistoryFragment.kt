package com.admin.budgetcalculator

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.admin.budgetcalculator.HistoryAdapter
import com.admin.budgetcalculator.databinding.FragmentHistoryBinding


class HistoryFragment : Fragment() {

    var _binding: FragmentHistoryBinding? = null
    val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHistoryBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val calculations = SharedPreferencesServices.getCalculations(requireActivity())
        val entries = calculations.entries.sortedByDescending {
            it.key.toLong()
        }

        val dates = mutableListOf<String>()
        val results = mutableListOf<String>()
        entries.forEach {
            dates.add(it.key)
            results.add(it.value.toString())
        }
        val adapter = HistoryAdapter(dates, results)
        binding.historyList.adapter = adapter

    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}