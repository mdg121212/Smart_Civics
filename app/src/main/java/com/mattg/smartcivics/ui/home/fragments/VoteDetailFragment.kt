package com.mattg.smartcivics.ui.home.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import com.mattg.smartcivics.R
import com.mattg.smartcivics.models.votes.Vote
import com.mattg.smartcivics.ui.home.HomeViewModel
import kotlinx.android.synthetic.main.fragment_vote_detail.*


class VoteDetailFragment : Fragment() {

    lateinit var viewModel: HomeViewModel


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {

        viewModel = ViewModelProvider(requireActivity()).get(HomeViewModel::class.java)

        return inflater.inflate(R.layout.fragment_vote_detail, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        observeViewModel()
    }

    private fun observeViewModel(){
        viewModel.apply {
            voteClicked.observe(viewLifecycleOwner){
                if(it != null){
                    setUpView(it)
                }

            }
        }
    }

    private fun setUpView(it: Vote) {
        tv_vote_detail_date.text = it.date
        tv_vote_detail_name.text = it.bill?.title
        tv_vote_detail_result.text = it.result
        tv_vote_detail_present.text = it.total?.present.toString()
        tv_vote_detail_question.text = it.question
        tv_vote_detail_position.text = it.position
        tv_vote_detail_no.text = it.total?.no.toString()
        tv_vote_detail_yes.text = it.total?.yes.toString()
        tv_vote_detail_not_voting.text = it.total?.notVoting.toString()
        tv_vote_detail_roll_call.text = it.rollCall
    }

}