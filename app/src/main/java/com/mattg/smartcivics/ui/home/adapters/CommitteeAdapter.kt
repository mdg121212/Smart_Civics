package com.mattg.smartcivics.ui.home.adapters

import android.animation.AnimatorSet
import android.animation.ObjectAnimator
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.LinearInterpolator
import androidx.core.animation.doOnEnd
import androidx.recyclerview.widget.RecyclerView
import com.mattg.smartcivics.databinding.CommitteeItemBinding
import com.mattg.smartcivics.models.propubmember.Committee
import com.mattg.smartcivics.ui.home.CommitteeClickListener

class CommitteeAdapter(private val committees: List<Committee>, private val clickListener: CommitteeClickListener)
    : RecyclerView.Adapter<CommitteeViewHolder>() {

    override fun onCreateViewHolder(
            parent: ViewGroup,
            viewType: Int,
    ): CommitteeViewHolder {
        return CommitteeViewHolder.from(parent)
    }

    override fun onBindViewHolder(holder: CommitteeViewHolder, position: Int) {
        val committee = committees[position]
        holder.bind(committee, clickListener)
    }

    override fun getItemCount(): Int {
        return committees.size
    }
}

class CommitteeViewHolder private constructor(private val binding: CommitteeItemBinding) : RecyclerView.ViewHolder(binding.root) {
    companion object {
        fun from(parent: ViewGroup): CommitteeViewHolder {
            val layoutInflater = LayoutInflater.from(parent.context)
            val binding = CommitteeItemBinding.inflate(layoutInflater, parent, false)
            return CommitteeViewHolder(binding)
        }
    }

    fun bind(com: Committee, clickListener: CommitteeClickListener) {
        binding.committee = com
        binding.clCommitteeItem.setOnClickListener {
            val animationX = ObjectAnimator.ofFloat(it, View.SCALE_X, 0f, 10f)
            val animationY = ObjectAnimator.ofFloat(it, View.SCALE_Y, 0f, 10f)
            val animations = AnimatorSet()
            animations.apply {
                playTogether(animationX, animationY)
                duration = 500
                interpolator = LinearInterpolator()
                start()
            }.doOnEnd {
                clickListener.onClickItem(com, adapterPosition)
            }
        }
    }
}