package com.mattg.smartcivics.ui.home.adapters

import android.animation.ObjectAnimator
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.BounceInterpolator
import androidx.core.animation.doOnEnd
import androidx.recyclerview.widget.RecyclerView
import com.mattg.smartcivics.databinding.BillItemBinding
import com.mattg.smartcivics.models.bills.BillX
import com.mattg.smartcivics.ui.home.BillClickListener

class BillAdapter(private val bills: List<BillX>, private val clickListener: BillClickListener) : RecyclerView.Adapter<BillViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BillViewHolder {
        return BillViewHolder.from(parent)
    }

    override fun onBindViewHolder(holder: BillViewHolder, position: Int) {
        holder.bind(bills[position], clickListener)
    }

    override fun getItemCount(): Int {
        return bills.size
    }
}

class BillViewHolder private constructor(private val binding: BillItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
    companion object {
        fun from(parent: ViewGroup): BillViewHolder {
            val billLayoutInflater = LayoutInflater.from(parent.context)
            val binding = BillItemBinding.inflate(billLayoutInflater, parent, false)
            return BillViewHolder(binding)
        }
    }

    fun bind(billItem: BillX, clickListener: BillClickListener) {
        binding.bill = billItem
        binding.cvBillItem.setOnClickListener {
            val animation = ObjectAnimator.ofFloat(it, View.TRANSLATION_Y, 0f, 25f, 0f)
            animation.apply {
                duration = 350
                start()
            }.doOnEnd {
                clickListener.onClickItem(billItem, adapterPosition)
            }

        }
    }
}