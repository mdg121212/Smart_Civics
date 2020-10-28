package com.mattg.smartcivics.network

import com.mattg.smartcivics.models.expenses.MemberQuarterlyExpensesResponse
import com.mattg.smartcivics.models.bills.MemberBillsResponse
import com.mattg.smartcivics.models.bills.SingleBillResponse
import com.mattg.smartcivics.models.committees.CommitteeHearingsResult
import com.mattg.smartcivics.models.committees.CommitteeResponse
import com.mattg.smartcivics.models.committees.SingleCommitteeResponse
import com.mattg.smartcivics.models.propubmember.ProPublicaBaseResult
import com.mattg.smartcivics.models.statements.MemberStatementsResponse
import com.mattg.smartcivics.models.statements.ResultMemberStatement
import com.mattg.smartcivics.models.subcommittees.SubCommitteeResult
import com.mattg.smartcivics.models.votes.MemberVoteResult
import com.mattg.smartcivics.utils.Constants
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Path
import retrofit2.http.Url

interface ProPublicaCall {

    //introduced, updated, active, passed, enacted or vetoed
    @Headers("X-API-Key: ${Constants.congressKey}")
    @GET("members/{member-id}/bills/{type}.json")
    fun getMemberBills(@Path("member-id")memberId: String,
                       @Path("type")type: String): Call<MemberBillsResponse>

    @Headers("X-API-Key: ${Constants.congressKey}")
    @GET("members/{member-id}/votes.json")
    fun getIndividualVotes(@Path("member-id")memberId: String) : Call<MemberVoteResult>

    @GET
    @Headers("X-API-Key: ${Constants.congressKey}")
    fun getCommitteeFromUrl(@Url url: String) : Call<CommitteeResponse>

    @GET
    @Headers("X-API-Key: ${Constants.congressKey}")
    fun getBillFromUrl(@Url url: String) : Call<SingleBillResponse>

    @GET
    @Headers("X-API-Key: ${Constants.congressKey}")
    fun getSubCommitteeFromUrl(@Url url: String) : Call<SubCommitteeResult>

    @GET
    @Headers("X-API-Key: ${Constants.congressKey}")
    fun getStatementFromUrl(@Url url: String) : Call<ResultMemberStatement>

    @Headers("X-API-Key: ${Constants.congressKey}")
    @GET("members/{member-id}/statements/{congress}.json")
    fun getMemberStatements(@Path("member-id")id: String,
                            @Path("congress")number: Int): Call<MemberStatementsResponse>

    @Headers("X-API-Key: ${Constants.congressKey}")
    @GET("{congress}/{chamber}/members.json") //current 116 soon 117
    fun getAllMembers(@Path("congress")number: Int,
                      @Path("chamber")type: String) : Call<ProPublicaBaseResult>

    @Headers("X-API-Key: ${Constants.congressKey}")
    @GET("{congress}/{chamber}/committees/{committee-id}/hearings.json")
    fun getHearingsForCommittee(@Path("congress") number: Int,
                                @Path("chamber")type: String,
                                @Path("committee-id")id: String) : Call<CommitteeHearingsResult>

    @Headers("X-API-Key: ${Constants.congressKey}")
    @GET("{congress}/{chamber}/committees/{committee-id}.json")
    fun getCommitteeDetails(@Path("congress")number: Int,
                            @Path("chamber")type: String,
                            @Path("committee-id")id: String) : Call<SingleCommitteeResponse>

    @Headers("X-API-Key: ${Constants.congressKey}")
    @GET("{congress}/{chamber}/committees/{committee-id}/subcommittees/{subcommittee-id}.json")
    fun getSubCommittee(@Path("congress")number: Int,  @Path("chamber")type: String,
                        @Path("committee-id")id: String,
                        @Path("subcommittee-id")subid: String) : Call<SubCommitteeResult>

    @Headers("X-API-Key: ${Constants.congressKey}")
    @GET("members/{member-id}/office_expenses/{year}/{quarter}.json")
    fun getMemberExpenses(@Path("member-id")id: String,
                          @Path("year")year: Int,
                          @Path("quarter")quarter: Int) : Call<MemberQuarterlyExpensesResponse>
}