package com.mattg.smartcivics.ui.home.fragments

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.navArgs
import com.mattg.smartcivics.R
import com.mattg.smartcivics.models.committees.ResultSingleCommittee
import com.mattg.smartcivics.models.subcommittees.ResultSubCommittee
import com.mattg.smartcivics.ui.home.HomeViewModel
import kotlinx.android.synthetic.main.fragment_committee_detail.*


class CommitteeDetailFragment : Fragment() {

    private lateinit var viewModel: HomeViewModel
    private val args = navArgs<CommitteeDetailFragmentArgs>()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        //get shared viewModel
        viewModel = ViewModelProvider(requireActivity()).get(HomeViewModel::class.java)

        return inflater.inflate(R.layout.fragment_committee_detail, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
       // observeViewModel()

        when(args.value.isCommittee){
            true -> {

                observeViewModelCommittee()
            }
            false -> {
                observeViewModelSubCommittee()
            }
        }
    }

    private fun observeViewModelCommittee() {
        viewModel.committeeClicked.observe(viewLifecycleOwner){
            tv_com_title.text = it.name

        }

       viewModel.committeeFromUrl.observe(viewLifecycleOwner){
           val details = it.results?.get(0)
           formatCommitteeMembers(details)
           val link = details?.url
           tv_com_link.text = link
       }
    }



    private fun observeViewModelSubCommittee(){
        viewModel.subCommitteeClicked.observe(viewLifecycleOwner){
            tv_com_title.text = it.name
        }
        viewModel.subCommitteeFromUrl.observe(viewLifecycleOwner){
            val details = it.results?.get(0)
            tv_com_chair.text = details?.chair
            formatCommitteeMembers(details)
            val link = details?.committeeUrl
            tv_com_link.text = link
        }
    }

    @SuppressLint("SetTextI18n")
    private fun formatCommitteeMembers(details: ResultSubCommittee?) {
        var republicans = 0
        var democrats = 0
        var independents = 0
        var memberCount = 0
        var string = ""
        val members = details?.currentMembers
        if (members != null) {
            for (member in members) {
                memberCount++
                if (member.party == "D") {
                    democrats++
                }
                if (member.party == "R") {
                    republicans++
                }
                if (member.party == "I") {
                    independents++
                }
                string += "${member.name} : Party:  ${member.party},  Rank in party:  ${member.rankInParty} \n\n"
            }

        }
        tv_com_members.text = string
        tv_com_membership_percentages.text =
            "Democrats: $democrats\nRepublicans: $republicans\nIndependents: $independents"
    }

    @SuppressLint("SetTextI18n")
    private fun formatCommitteeMembers(details: ResultSingleCommittee?) {
        tv_com_chair.text = details?.chair
        var republicans = 0
        var democrats = 0
        var independents = 0
        var memberCount = 0
        var string = ""
        val members = details?.currentMembers
        if (members != null) {
            for (member in members) {
                memberCount++
                if (member.party == "D") {
                    democrats++
                }
                if (member.party == "R") {
                    republicans++
                }
                if (member.party == "I") {
                    independents++
                }
                string += "${member.name}: Party:  ${member.party}, Rank in party:  ${member.rankInParty} \n\n"
            }

        }
        tv_com_members.text = string
        tv_com_membership_percentages.text =
            "Democrats: $democrats\nRepublicans: $republicans\nIndependents: $independents"
    }
}