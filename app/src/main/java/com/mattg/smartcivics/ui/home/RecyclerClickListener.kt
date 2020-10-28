package com.mattg.smartcivics.ui.home

import com.mattg.smartcivics.models.*
import com.mattg.smartcivics.models.bills.BillX
import com.mattg.smartcivics.models.civicvoterinfo.appvotermodel.ElectionForDisplay
import com.mattg.smartcivics.models.news.Article
import com.mattg.smartcivics.models.statements.ResultMemberStatement
import com.mattg.smartcivics.models.propubmember.Committee
import com.mattg.smartcivics.models.propubmember.Subcommittee
import com.mattg.smartcivics.models.votes.Vote

class RecyclerClickListener(val clickListener: (representative: Representative, position: Int) -> Unit) {
    fun onClickItem(representative: Representative, position: Int) = clickListener(representative, position)
}
class CommitteeClickListener(val clickListener: (committee: Committee, position: Int) -> Unit) {
    fun onClickItem(committee: Committee, position: Int) = clickListener(committee, position)
}
class SubCommitteeClickListener(val clickListener: (subCommittee: Subcommittee, position: Int) -> Unit) {
    fun onClickItem(subCommittee: Subcommittee, position: Int) = clickListener(subCommittee, position)
}
class StatementClickListener(val clickListener:(statement: ResultMemberStatement, position: Int) -> Unit) {
    fun onClickItem(statement: ResultMemberStatement, position: Int) = clickListener(statement, position)
}
class BillClickListener(val clickListener: (bill: BillX, position: Int) -> Unit) {
    fun onClickItem(bill: BillX, position: Int) = clickListener(bill, position)
}
class ElectionClickListener(val clickListener: (election: ElectionForDisplay, position: Int, type: String) -> Unit) {
    fun onClick(election: ElectionForDisplay, position: Int, type: String) = clickListener(election, position, type)
}
class VoteClickListener(val clickListener: (vote: Vote, position: Int) -> Unit) {
    fun onClick(vote: Vote, position: Int) = clickListener(vote, position)
}
class NewsClickListener(val clickListener: (article: Article, position: Int) -> Unit) {
    fun onClick(article: Article, position: Int) = clickListener(article, position)
}
