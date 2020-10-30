package com.mattg.smartcivics.ui.home.adapters

import android.animation.AnimatorSet
import android.animation.ObjectAnimator
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.LinearInterpolator
import androidx.core.animation.doOnEnd
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
            val animationX = ObjectAnimator.ofFloat(it, View.SCALE_X, 0f, 10f)
            val animationY = ObjectAnimator.ofFloat(it, View.SCALE_Y, 0f, 10f)
            val animations = AnimatorSet()
            animations.apply {
                playTogether(animationX, animationY)
                duration = 500
                interpolator = LinearInterpolator()
                start()
            }.doOnEnd {
                clickListener.onClickItem(subCom, adapterPosition)
            }
        }
    }
}
