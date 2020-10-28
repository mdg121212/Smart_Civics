package com.mattg.smartcivics.ui.home.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.mattg.smartcivics.databinding.QuarterlyExpenseItemBinding
import com.mattg.smartcivics.models.expenses.ResultMemberQuarterlyExpense

class ExpenseItemAdapter (private val list: List<ResultMemberQuarterlyExpense>) : RecyclerView.Adapter<ExpenseViewHolder>(){
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ExpenseViewHolder {
       return ExpenseViewHolder.from(parent)
    }

    override fun onBindViewHolder(holder: ExpenseViewHolder, position: Int) {
        holder.bind(list[position])
    }

    override fun getItemCount(): Int {
        return list.size
    }
}
class ExpenseViewHolder private constructor(val binding: QuarterlyExpenseItemBinding) : RecyclerView.ViewHolder(binding.root) {
    companion object {
        fun from(parent: ViewGroup): ExpenseViewHolder {
            val layoutInflater = LayoutInflater.from(parent.context)
            val binding = QuarterlyExpenseItemBinding.inflate(layoutInflater, parent, false)
            return ExpenseViewHolder(binding)
        }
    }
    fun bind(expenseItem: ResultMemberQuarterlyExpense){
        binding.expense = expenseItem
    }
}