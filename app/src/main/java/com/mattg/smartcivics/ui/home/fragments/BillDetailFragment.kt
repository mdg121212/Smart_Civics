package com.mattg.smartcivics.ui.home.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import com.mattg.smartcivics.R
import com.mattg.smartcivics.models.bills.SingleBillResult
import com.mattg.smartcivics.ui.home.HomeViewModel
import kotlinx.android.synthetic.main.fragment_bill_detail.*


class BillDetailFragment : Fragment() {
    private lateinit var viewModel: HomeViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        viewModel = ViewModelProvider(requireActivity()).get(HomeViewModel::class.java)

        return inflater.inflate(R.layout.fragment_bill_detail, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        observeViewModel()
    }

    private fun observeViewModel() {
        viewModel.billDetail.observe(viewLifecycleOwner){
            fillInBillDetails(it)
        }

    }

    private fun fillInBillDetails(result: SingleBillResult) {
        tv_billfrag_billtitle.text = result.title
        tv_billfrag_number.text = result.number
        tv_billfrag_lastaction.text = result.latestMajorAction.toString()
        if(result.summary.isNullOrBlank()){
            tv_billfrag_summary.text = getString(R.string.no_summary_text)
        } else {
            tv_billfrag_summary.text = result.summaryShort
        }
        if(result.primarySubject.isNullOrBlank()){
            tv_billfrag_subjects.text = getString(R.string.no_subject_available_text)
        }else{
            tv_billfrag_subjects.text = result.primarySubject.toString()
        }
        tv_billfrag_link.text = result.congressdotgovUrl

    }


}