package com.mattg.smartcivics.ui.home.adapters


import android.animation.ObjectAnimator
import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.BounceInterpolator
import androidx.core.animation.doOnEnd
import androidx.recyclerview.widget.RecyclerView
import com.mattg.smartcivics.R
import com.mattg.smartcivics.databinding.RepRecyclerItemBinding
import com.mattg.smartcivics.models.Representative
import com.mattg.smartcivics.ui.home.RecyclerClickListener
import com.mattg.smartcivics.utils.getProgressDrawable
import com.mattg.smartcivics.utils.loadImage


class RepAdapter(private val context: Context, private val officials: List<Representative>, private val clickListener: RecyclerClickListener) :
        RecyclerView.Adapter<RepViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RepViewHolder {
        return RepViewHolder.from(parent)
    }

    override fun onBindViewHolder(holder: RepViewHolder, position: Int) {
        val rep = officials[position]
        holder.bind(rep, context, clickListener)
    }

    override fun getItemCount(): Int {
        return officials.size
    }
}

class RepViewHolder private constructor(private val binding: RepRecyclerItemBinding) : RecyclerView.ViewHolder(binding.root) {
    companion object {
        fun from(parent: ViewGroup): RepViewHolder {
            val layoutInflater = LayoutInflater.from(parent.context)
            val binding = RepRecyclerItemBinding.inflate(layoutInflater, parent, false)
            return RepViewHolder(binding)
        }
    }

    fun bind(rep: Representative, context: Context, clickListener: RecyclerClickListener) {
        binding.rep = rep
        val photoUrl = rep.photo.toString()

        when (rep.party) {
            "Democratic Party" -> {
                binding.ivPartyIcon.setImageResource(R.drawable.donkey)
            }
            "Republican Party" -> {
                binding.ivPartyIcon.setImageResource(R.drawable.elephant)
            }
            "Nonpartisan" -> {
                binding.ivPartyIcon.setImageResource(R.drawable.independanticon)
            }
            "Unknown" -> {
                binding.ivPartyIcon.setImageResource(R.drawable.help)
            }
        }

        Log.i("PHOTO", photoUrl)

        if (photoUrl.isNotEmpty()) {

            binding.imageView.loadImage(photoUrl, getProgressDrawable(context))

        }


        //implement click listener
        binding.cvItem.setOnClickListener {
            val animation = ObjectAnimator.ofFloat(it, View.TRANSLATION_Y, 0f, 25f, 0f)
            animation.apply {
                duration = 750
                interpolator = BounceInterpolator()
                start()
            }.doOnEnd { clickListener.onClickItem(rep, adapterPosition) }

            binding.executePendingBindings()
        }
    }
}