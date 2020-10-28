package com.mattg.smartcivics.ui.home.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.mattg.smartcivics.R
import com.mattg.smartcivics.models.expenses.ResultMemberQuarterlyExpense
import com.mattg.smartcivics.models.bills.BillX
import com.mattg.smartcivics.models.finance.FinanceResponse
import com.mattg.smartcivics.models.finance.ResultFinance
import com.mattg.smartcivics.models.statements.ResultMemberStatement
import com.mattg.smartcivics.models.propubmember.Committee
import com.mattg.smartcivics.models.propubmember.SingleProPublicaResponse
import com.mattg.smartcivics.models.propubmember.Subcommittee
import com.mattg.smartcivics.models.votes.Vote
import com.mattg.smartcivics.ui.home.*
import com.mattg.smartcivics.ui.home.adapters.*
import com.mattg.smartcivics.utils.startCustomTab
import kotlinx.android.synthetic.main.fragment_congress_record_detail.*


class CongressRecordDetail : Fragment() {

    private lateinit var viewModel : HomeViewModel
    private lateinit var comClickListener: CommitteeClickListener
    private lateinit var subComClickListener: SubCommitteeClickListener
    private lateinit var statementClickListener: StatementClickListener
    private lateinit var billClickListener: BillClickListener
    private lateinit var voteClickListener: VoteClickListener

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {

        viewModel = ViewModelProvider(requireActivity()).get(HomeViewModel::class.java)
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_congress_record_detail, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        observeViewModel()
    }

    private fun observeViewModel() {
        viewModel.singleResponse.observe(viewLifecycleOwner) {
            fillInDetails(it)
        }
        viewModel.voteResponse.observe(viewLifecycleOwner){
            if(it!=null){
                val list = it.results?.get(0)?.votes
                if (list != null) {
                    initVoteRecycler(list)
                }
            }
        }
        viewModel.billsMember.observe(viewLifecycleOwner){
            if(it!=null){
                initBillRecycler(it)
            }
        }
        viewModel.expensesList.observe(viewLifecycleOwner){
           if(!it.isNullOrEmpty()){
               initExpenseRecycler(it)
           } else{
               rv_expenses.visibility = View.GONE
               tv_expenses_title.visibility = View.GONE
           }

        }

        viewModel.financesInfo.observe(viewLifecycleOwner) {
            fillInFinanceDetails(it)
        }
        viewModel.financeList.observe(viewLifecycleOwner) {
            setFinanceTextViews(it)

        }
        viewModel.statements.observe(viewLifecycleOwner) { response ->
            response.results?.let {initStatementRecycler(it) }

        }
        viewModel.clickedRepRating.observe(viewLifecycleOwner) {
            tv_against_rating.text = it
        }
    }

    private fun setFinanceTextViews(it: List<ResultFinance>?) {
        if (it != null) {
            tv_total_cont.text = it[0].totalContributions?.toBigDecimal().toString()
            tv_cont_individ.text = it[0].totalFromIndividuals?.toBigDecimal().toString()
            tv_debts.text = it[0].debtsOwed?.toBigDecimal().toString()
            tv_unitem.text = it[0].totalFromIndividualsUnitemized?.toBigDecimal().toString()
            tv_cont_pac.text = it[0].totalFromPacs?.toBigDecimal().toString()
        } else {
            tv_total_cont.text = getString(R.string.not_available_text)
            tv_cont_individ.text = getString(R.string.not_available_text)
            tv_debts.text = getString(R.string.not_available_text)
            tv_unitem.text = getString(R.string.not_available_text)
            tv_cont_pac.text = getString(R.string.not_available_text)
        }
    }

    private fun initBillRecycler(list: List<BillX>) {
        billClickListener = BillClickListener { bill, _ ->
            bill.billUri?.let { viewModel.getBillUrl(it)}
            findNavController().navigate(R.id.action_congressRecordDetail_to_billDetailFragment)
        }
        val billAdapter = BillAdapter(list, billClickListener)
        val billLayoutManager = LinearLayoutManager(requireContext(), RecyclerView.HORIZONTAL, false)
        rv_bills.apply {
            adapter = billAdapter
            layoutManager = billLayoutManager
        }
    }

    private fun initExpenseRecycler(list: List<ResultMemberQuarterlyExpense>?) {
        val expenseAdapter = list?.let { ExpenseItemAdapter(it) }
        val expenseLayoutManager = LinearLayoutManager(requireContext(), RecyclerView.VERTICAL, false)
        rv_expenses.apply {
            adapter = expenseAdapter
            layoutManager = expenseLayoutManager
        }
    }

    private fun fillInFinanceDetails(it: FinanceResponse?) {
        val finances = it?.results
        if(finances != null){
            val instance = finances[0]
            tv_total_cont.text = instance.totalContributions.toString()
            tv_cont_individ.text = instance.totalFromIndividuals.toString()
            tv_debts.text = instance.debtsOwed.toString()
            tv_unitem.text = instance.totalFromIndividualsUnitemized.toString()
            tv_cont_pac.text = instance.totalFromPacs.toString()
        }
    }

    private fun fillInDetails(it: SingleProPublicaResponse?) {
        val basicInfo = it?.results?.get(0)
        if (basicInfo != null) {
            val basics = basicInfo.roles?.get(0)
            val committees = basics?.committees
            val subCommittees = basics?.subcommittees
            tv_totalvotes.text = basics?.totalVotes.toString()
            tv_missedvotes.text = basics?.missedVotes.toString()
            tv_voteagainstparty.text = "${basics?.votesAgainstPartyPct} %"
            tv_votewithparty.text = "${basics?.votesWithPartyPct} %"

            if (subCommittees != null) {
                if (committees != null) {
                    initRecyclers(committees, subCommittees)
                }
            }
        }


    }
    private fun initStatementRecycler( statementList: List<ResultMemberStatement>){
        statementClickListener = StatementClickListener{statement, _ ->
            openStatementUrl(statement.url)
        }
        val statementAdapter = StatementAdapter(statementList, statementClickListener)
        rv_statements.apply {
            adapter = statementAdapter
            layoutManager = LinearLayoutManager(requireContext(), RecyclerView.HORIZONTAL, false)
        }
    }

    private fun initVoteRecycler(voteList: List<Vote>){
        voteClickListener = VoteClickListener {vote, position ->
            viewModel.setVoteClicked(vote)
            findNavController().navigate(R.id.action_congressRecordDetail_to_voteDetailFragment)
        }
        val voteAdapter = VoteDetailsAdapter(voteList, voteClickListener)
        rv_vote_details.apply {
            adapter = voteAdapter
            layoutManager = LinearLayoutManager(requireContext(), RecyclerView.HORIZONTAL, false)
        }
    }

    private fun initRecyclers(comList: List<Committee>, subComList: List<Subcommittee>) {
        comClickListener = CommitteeClickListener{committee, _ ->
            openCommitteeDetails(committee)
            viewModel.committeeClicked.value = committee
        }
        subComClickListener = SubCommitteeClickListener{subCommittee, _ ->
            openSubCommitteeDetails(subCommittee)
            viewModel.subCommitteeClicked.value = subCommittee
        }
        val comAdapter = CommitteeAdapter(comList, comClickListener)
        val subComAdapter = SubCommitteeAdapter(subComList, subComClickListener)

        rv_committees.apply {
            adapter = comAdapter
            layoutManager = LinearLayoutManager(requireContext(), RecyclerView.HORIZONTAL, false)
        }
        rv_subcommittee.apply {
            adapter = subComAdapter
            layoutManager = LinearLayoutManager(requireContext(), RecyclerView.HORIZONTAL, false)
        }

    }

    private fun openStatementUrl(url: String?) {
        if (url != null) {
            startCustomTab(url, requireContext())
        }
    }

    private fun openSubCommitteeDetails(subCommittee: Subcommittee) {
        viewModel.subCommitteeClicked.value = subCommittee
        subCommittee.apiUri?.let{viewModel.getSubCommitteeFromUrl(it)}
        findNavController().navigate(CongressRecordDetailDirections.actionCongressRecordDetailToCommitteeDetailFragment(
            false))
    }

    private fun openCommitteeDetails(committee: Committee) {
        viewModel.committeeClicked.value = committee
        committee.apiUri?.let { viewModel.getCommitteeFromUrl(it) }
        findNavController().navigate(CongressRecordDetailDirections.actionCongressRecordDetailToCommitteeDetailFragment(
            true))
    }
}