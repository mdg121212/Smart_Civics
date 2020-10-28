package com.mattg.smartcivics.ui.home.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.mattg.smartcivics.databinding.SubcommitteeItemBinding
import com.mattg.smartcivics.models.propubmember.Subcommittee
import com.mattg.smartcivics.ui.home.SubCommitteeClickListener

class SubCommitteeAdapter (private val subCommittees: List<Subcommittee>, private val clickListener: SubCommitteeClickListener) :
        RecyclerView.Adapter<SubCommitteeViewHolder>(){
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SubCommitteeViewHolder {
        return SubCommitteeViewHolder.from(parent)
    }

    override fun onBindViewHolder(holder: SubCommitteeViewHolder, position: Int) {
        val subCom = subCommittees[position]
        holder.bind(subCom, clickListener)
    }

    override fun getItemCount(): Int {
        return subCommittees.size
    }
}

class SubCommitteeViewHolder(private val binding: SubcommitteeItemBinding) : RecyclerView.ViewHolder(binding.root) {
    companion object {
        fun from(parent: ViewGroup) : SubCommitteeViewHolder {
            val layoutInflater = LayoutInflater.from(parent.context)
            val binding = SubcommitteeItemBinding.inflate(layoutInflater, parent, false)
            return SubCommitteeViewHolder(binding)
        }
    }

    fun bind(subCom: Subcommittee, clickListener: SubCommitteeClickListener){
        binding.subcom = subCom
        binding.clSubcommitteeItem.setOnClickListener {
            clickListener.onClickItem(subCom, adapterPosition)
        }
    }
}
