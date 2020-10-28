package com.mattg.smartcivics.ui.elections.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.mattg.smartcivics.databinding.ElectionRecyclerItemBinding
import com.mattg.smartcivics.models.civicvoterinfo.appvotermodel.ElectionForDisplay
import com.mattg.smartcivics.ui.home.ElectionClickListener
import com.mattg.smartcivics.utils.TypeForMap

class ElectionAdapter(private val list: List<ElectionForDisplay>, private val clickListener: ElectionClickListener) :
RecyclerView.Adapter<ElectionViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ElectionViewHolder {
        return ElectionViewHolder.from(parent)
    }

    override fun onBindViewHolder(holder: ElectionViewHolder, position: Int) {
        holder.bind(list[position], clickListener)
    }

    override fun getItemCount() = list.size

}

class ElectionViewHolder private constructor(private val binding: ElectionRecyclerItemBinding)
    : RecyclerView.ViewHolder(binding.root) {
companion object{
    fun from(parent: ViewGroup) : ElectionViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = ElectionRecyclerItemBinding.inflate(layoutInflater)
        return ElectionViewHolder(binding)
    }
}
    fun bind(election: ElectionForDisplay, clickListener: ElectionClickListener){
        binding.response = election
        binding.btnDropOffLocations.setOnClickListener {

            clickListener.onClick(election, adapterPosition, TypeForMap.DROPOFF.toString())
        }
        binding.btnEarlyVoteSites.setOnClickListener {
            clickListener.onClick(election, adapterPosition, TypeForMap.EARLYVOTE.toString())
        }
        binding.btnElectionAdmin.setOnClickListener {
            clickListener.onClick(election, adapterPosition, TypeForMap.ADMININFO.toString())
        }
        binding.btnPolling.setOnClickListener {
            clickListener.onClick(election, adapterPosition, TypeForMap.POLLING.toString())
        }
    }
}
