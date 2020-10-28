package com.mattg.smartcivics.ui.home.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.mattg.smartcivics.databinding.StatementsItemBinding
import com.mattg.smartcivics.models.statements.ResultMemberStatement
import com.mattg.smartcivics.ui.home.StatementClickListener

class StatementAdapter (private val list: List<ResultMemberStatement>, private val clickListener: StatementClickListener) :
        RecyclerView.Adapter<StatementViewHolder>(){
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): StatementViewHolder {
        return StatementViewHolder.from(parent)
    }

    override fun onBindViewHolder(holder: StatementViewHolder, position: Int) {
        holder.bind(list[position], clickListener)
    }

    override fun getItemCount(): Int {
       return list.size
    }
}
class StatementViewHolder private constructor(private val binding: StatementsItemBinding ) : RecyclerView.ViewHolder(binding.root){
    companion object {
        fun from(parent: ViewGroup): StatementViewHolder {
            val layoutInflater = LayoutInflater.from(parent.context)
            val binding = StatementsItemBinding.inflate(layoutInflater, parent, false)
            return StatementViewHolder(binding)
        }
    }
        fun bind(statement: ResultMemberStatement, clickListener: StatementClickListener){
            binding.statement = statement
            binding.clStatementItem.setOnClickListener {
                clickListener.onClickItem(statement, adapterPosition)
            }
        }
    }
