package com.mattg.smartcivics.ui.home.fragments


import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.bumptech.glide.Glide
import com.mattg.smartcivics.R
import com.mattg.smartcivics.models.Representative
import com.mattg.smartcivics.ui.home.HomeViewModel
import com.mattg.smartcivics.utils.Constants
import com.mattg.smartcivics.utils.addressText
import com.mattg.smartcivics.utils.startCustomTab
import kotlinx.android.synthetic.main.fragment_rep_detail.*


class RepDetailFragment : Fragment() {

    private lateinit var viewModel: HomeViewModel


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        // Inflate the layout for this fragment
        viewModel = ViewModelProvider(requireActivity()).get(HomeViewModel::class.java)

        return inflater.inflate(R.layout.fragment_rep_detail, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        observeViewModel()

        val rep = viewModel.repToView.value

        if (rep != null) {
            setInfo(rep)
        } else {
            Toast.makeText(requireContext(), "Not found", Toast.LENGTH_SHORT).show()
        }

        if (rep?.id != null && rep.fecId != null) {
            btn_rep_more_detail.apply {
                visibility = View.VISIBLE
                setOnClickListener {
                    moreDetailClicked(rep.id!!, rep.fecId!!, rep.memberId!!)
                }
            }
        }
    }

    private fun moreDetailClicked(id: String, fecId: String, memberId: String) {
        //MOVE TO MORE DETAILED SCREEN FOR FEDERAL REPRESENTATIVES
        viewModel.apply {
            getQuarterlyExpenses(id, 2)
            getVotes(id)
            callSingleProApi(id)
            getMemberFinancials(2020, fecId)
            getStatements(id, 116)
            getMemberBills(id, "introduced")
            callOpenSecretsForBasicInfo(memberId, "2020", Constants.openSecretsKey)
        }

        findNavController().navigate(R.id.action_repDetailFragment_to_congressRecordDetail)

    }

    @SuppressLint("SetTextI18n")
    private fun setInfo(rep: Representative?) {
        tv_detail_name.text = rep?.name
        tv_detail_party.text = rep?.party
        tv_detail_office.text = rep?.title
        tv_detail_phone.text = rep?.phone
        tv_detail_website.text = rep?.url
        tv_detail_address.text = rep?.let { addressText(it) }
        if (rep?.nextElection != null) {
            tv_election_details.text = "Next Election: " + rep.nextElection
        }


        if (rep?.channels != null) {
            for (channel in rep.channels!!) {
                when (channel.type) {
                    "Facebook" -> {
                        if (channel.id != null) {
                            val string = "https://www.facebook.com/${channel.id}/"
                            ib_facebook.visibility = View.VISIBLE
                            ib_facebook.setOnClickListener {
                                startCustomTab(string, requireContext())
                            }
                        } else {
                            ib_facebook.visibility = View.GONE
                        }

                    }
                    "Twitter" -> {
                        if (channel.id != null) {
                            val string = "https://www.twitter.com/${channel.id}/"
                            ib_twitter.visibility = View.VISIBLE
                            ib_twitter.setOnClickListener {
                                startCustomTab(string, requireContext())
                            }
                        } else {
                            ib_twitter.visibility = View.GONE
                        }

                    }
                    "YouTube" -> {
                        if (!channel.id.isNullOrEmpty()) {
                            ib_youtube.visibility = View.VISIBLE
                            val string = "https://www.youtube.com/${channel.id}/"
                            ib_youtube.setOnClickListener {
                                startCustomTab(string, requireContext())
                            }
                        } else {
                            ib_youtube.visibility = View.GONE

                        }

                    }
                }

            }
        }


        Glide.with(requireContext())
            .load(rep?.photo)
            .into(iv_detail_photo)
    }

    private fun observeViewModel() {
        viewModel.repToView.observe(this.viewLifecycleOwner) {
            setInfo(it)
        }
    }

}