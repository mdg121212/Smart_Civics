package com.mattg.smartcivics.ui.home.adapters


import android.animation.ObjectAnimator
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.BounceInterpolator
import androidx.core.animation.doOnEnd
import androidx.recyclerview.widget.RecyclerView
import com.mattg.smartcivics.databinding.MemberVotedetailItemBinding
import com.mattg.smartcivics.models.votes.Vote
import com.mattg.smartcivics.ui.home.VoteClickListener

class VoteDetailsAdapter(private val votes: List<Vote>, private val clickListener: VoteClickListener) : RecyclerView.Adapter<VoteDetailViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VoteDetailViewHolder {
        return VoteDetailViewHolder.from(parent)
    }

    override fun onBindViewHolder(holder: VoteDetailViewHolder, position: Int) {
        holder.bind(votes[position], clickListener)
    }

    override fun getItemCount(): Int {
        return votes.size
    }
}

class VoteDetailViewHolder private constructor(private val binding: MemberVotedetailItemBinding)
    : RecyclerView.ViewHolder(binding.root) {
    companion object {
        fun from(parent: ViewGroup): VoteDetailViewHolder {
            val layoutInflater = LayoutInflater.from(parent.context)
            val binding = MemberVotedetailItemBinding.inflate(layoutInflater, parent, false)
            return VoteDetailViewHolder(binding)
        }
    }

    fun bind(voteItem: Vote, clickListener: VoteClickListener) {
        binding.vote = voteItem
        binding.cvVoteDetail.setOnClickListener {
            val animation = ObjectAnimator.ofFloat(it, View.TRANSLATION_Y, 0f, 25f, 0f)
            animation.apply {
                duration = 350
                interpolator = BounceInterpolator()
                start()
            }.doOnEnd {
                clickListener.onClick(voteItem, adapterPosition)
            }
        }

    }

}