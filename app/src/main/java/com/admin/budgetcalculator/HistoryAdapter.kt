package com.admin.budgetcalculator

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.admin.budgetcalculator.databinding.ItemHistoryBinding
import java.text.SimpleDateFormat
import java.util.*

class HistoryAdapter(private val dates: List<String>, private val results: List<String>) :
    RecyclerView.Adapter<HistoryAdapter.HistoryViewHolder>() {
    val dateFormatter = SimpleDateFormat("yyyy/MM/dd - HH:mm")


    inner class HistoryViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val binding = ItemHistoryBinding.bind(view)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HistoryViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_history, parent, false)
        return HistoryViewHolder(view)
    }

    override fun onBindViewHolder(holder: HistoryViewHolder, position: Int) {
        val date = Date(dates[position].toLong())
        holder.binding.date.text = dateFormatter.format(date)
        holder.binding.result.text = holder.itemView.context.getString(
            R.string.remaining_amount_of_salary,
            results[position]
        )
    }

    override fun getItemCount(): Int {
        return dates.size
    }
}