package com.mattg.smartcivics.ui.home


import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.mattg.smartcivics.models.*
import com.mattg.smartcivics.models.expenses.MemberQuarterlyExpensesResponse
import com.mattg.smartcivics.models.expenses.ResultMemberQuarterlyExpense
import com.mattg.smartcivics.models.opensecrets.OpenSecretGeneralResponse
import com.mattg.smartcivics.models.bills.BillX
import com.mattg.smartcivics.models.bills.MemberBillsResponse
import com.mattg.smartcivics.models.bills.SingleBillResponse
import com.mattg.smartcivics.models.bills.SingleBillResult
import com.mattg.smartcivics.models.civicapi.RepResponse
import com.mattg.smartcivics.models.committees.CommitteeResponse
import com.mattg.smartcivics.models.committees.ResultSingleCommittee
import com.mattg.smartcivics.models.committees.SingleCommitteeResponse
import com.mattg.smartcivics.models.finance.FinanceResponse
import com.mattg.smartcivics.models.finance.ResultFinance
import com.mattg.smartcivics.models.statements.MemberStatementsResponse
import com.mattg.smartcivics.models.statements.ResultMemberStatement
import com.mattg.smartcivics.models.subcommittees.SubCommitteeResult
import com.mattg.smartcivics.models.propubmember.Committee
import com.mattg.smartcivics.models.propubmember.ProPublicaBaseResult
import com.mattg.smartcivics.models.propubmember.SingleProPublicaResponse
import com.mattg.smartcivics.models.propubmember.Subcommittee
import com.mattg.smartcivics.models.votes.MemberVoteResult
import com.mattg.smartcivics.models.votes.Vote
import com.mattg.smartcivics.network.ApiCallService
import com.mattg.smartcivics.utils.generateList
import com.mattg.smartcivics.utils.mergeResultProPublica
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

private const val TAG = "HomeViewModel"

class HomeViewModel : ViewModel() {

    private val _addressString = MutableLiveData<String>()
    val addressString: LiveData<String> = _addressString

    private val _repResponse = MutableLiveData<RepResponse>()
    val repResponse: LiveData<RepResponse> = _repResponse

    private val _singleResponse = MutableLiveData<SingleProPublicaResponse>()
    val singleResponse: LiveData<SingleProPublicaResponse>
        get() = _singleResponse

    //for committees
    val committees = MutableLiveData<List<Committee>>()
    val subCommittees = MutableLiveData<List<Subcommittee>>()
    val committeeDetails = MutableLiveData<ResultSingleCommittee>()
    val committeeMembers = MutableLiveData<List<Representative>>()
    val committeeFromUrl = MutableLiveData<CommitteeResponse>()
    val subCommitteeFromUrl = MutableLiveData<SubCommitteeResult>()
    val committeeClicked = MutableLiveData<Committee>()
    val subCommitteeClicked = MutableLiveData<Subcommittee>()

    //for member statements
    val statements = MutableLiveData<MemberStatementsResponse>()
    val statement = MutableLiveData<ResultMemberStatement>()

    //for expenses
    val expensesList = MutableLiveData<List<ResultMemberQuarterlyExpense>>()
    val expensesInfo = MutableLiveData<MemberQuarterlyExpensesResponse>()

    //for individual votes
    val voteResponse = MutableLiveData<MemberVoteResult>()

    //for vote clicked
    private val _voteClicked = MutableLiveData<Vote>()
    val voteClicked: LiveData<Vote> = _voteClicked

    //for finances from ProPublica
    val financesInfo = MutableLiveData<FinanceResponse>()
    val financeList = MutableLiveData<List<ResultFinance>>()

    //for basics from OpenSecrets
    val openSecretBasicResponse = MutableLiveData<OpenSecretGeneralResponse>()

    //for bills
    val billsMember = MutableLiveData<List<BillX>>()

    //for single bill
    val billDetail = MutableLiveData<SingleBillResult>()
    val billDetailResponse = MutableLiveData<SingleBillResponse>()

    private val _officials = MutableLiveData<ArrayList<Representative>>()

    val officials: MutableLiveData<ArrayList<Representative>> get() = _officials

    private val _clickedRepRating = MutableLiveData<String>()
    val clickedRepRating: LiveData<String> = _clickedRepRating

    private val _repToView = MutableLiveData<Representative>()
    val repToView: LiveData<Representative> = _repToView

    var repList = ArrayList<Representative>()

    var progressShowing = MutableLiveData<Boolean>()

    var recyclerMainShowing = MutableLiveData<Boolean>()


    /**
     * CREATE API CALL, FROM REPO, AVAILABLE HERE, TO GET A SPECIFIC CONGRESSIONAL MEMBERS PRO PUBLICA DATA SEE DOCUMENTATION
     * WILL USE THE ADDED ID: FIELD
     */

    fun getVotes(id: String) {
        val callVotes = ApiCallService.getProApi()
        callVotes.getIndividualVotes(id).enqueue(object : Callback<MemberVoteResult> {
            override fun onResponse(
                call: Call<MemberVoteResult>,
                response: Response<MemberVoteResult>,
            ) {
                val result = response.body()
                voteResponse.value = result

            }

            override fun onFailure(call: Call<MemberVoteResult>, t: Throwable) {
                Log.i(TAG, "Error: ${t.localizedMessage}")
            }
        })
    }

    fun getStatements(id: String, congress: Int) {
        val callStatements = ApiCallService.getProApi()
        callStatements.getMemberStatements(id, congress)
            .enqueue(object : Callback<MemberStatementsResponse> {
                override fun onResponse(
                    call: Call<MemberStatementsResponse>,
                    response: Response<MemberStatementsResponse>,
                ) {
                    val result = response.body()
                    statements.value = result
                }

                override fun onFailure(call: Call<MemberStatementsResponse>, t: Throwable) {
                    Log.i(TAG, "Error: ${t.localizedMessage}")
                }
            })
    }

    fun getStatementUrl(url: String) {
        val callStatementUrl = ApiCallService.getProApi()
        callStatementUrl.getStatementFromUrl(url).enqueue(object : Callback<ResultMemberStatement> {
            override fun onResponse(
                call: Call<ResultMemberStatement>,
                response: Response<ResultMemberStatement>,
            ) {
                val result = response.body()
                statement.value = result

            }

            override fun onFailure(call: Call<ResultMemberStatement>, t: Throwable) {
                Log.i(TAG, "Error: ${t.localizedMessage}")
            }
        })
    }

    fun getBillUrl(url: String) {
        val callBillUrl = ApiCallService.getBillFromUrl()
        callBillUrl.getBillFromUrl(url).enqueue(object : Callback<SingleBillResponse> {
            override fun onResponse(
                call: Call<SingleBillResponse>,
                response: Response<SingleBillResponse>,
            ) {
                val result = response.body()
                Log.i(TAG, "$result")
                billDetailResponse.value = result
                val bill = result?.results?.get(0)
                billDetail.value = bill
            }

            override fun onFailure(call: Call<SingleBillResponse>, t: Throwable) {
                Log.i(TAG, "Error: ${t.localizedMessage}")
            }
        })
    }

    fun getSubCommitteeFromUrl(url: String) {
        setLoadingActive()
        val callSubComUrl = ApiCallService.getSubCommitteeWithUrl()
        callSubComUrl.getSubCommitteeFromUrl(url).enqueue(object : Callback<SubCommitteeResult> {
            override fun onResponse(
                call: Call<SubCommitteeResult>,
                response: Response<SubCommitteeResult>,
            ) {
                val result = response.body()
                subCommitteeFromUrl.value = result
                setLoadingFinished()
            }

            override fun onFailure(call: Call<SubCommitteeResult>, t: Throwable) {
                Log.i(TAG, "Error: ${t.localizedMessage}")
            }
        })
    }

    fun getCommitteeFromUrl(url: String) {
        setLoadingActive()
        val callComUrl = ApiCallService.getCommitteeWithUrl()
        callComUrl.getCommitteeFromUrl(url).enqueue(object : Callback<CommitteeResponse> {
            override fun onResponse(
                call: Call<CommitteeResponse>,
                response: Response<CommitteeResponse>,
            ) {
                val result = response.body()
                committeeFromUrl.value = result
                setLoadingFinished()
            }

            override fun onFailure(call: Call<CommitteeResponse>, t: Throwable) {
                Log.i(TAG, "Error: ${t.localizedMessage}")
            }
        })
    }

    fun getMemberFinancials(year: Int, id: String) {
        val callFinance = ApiCallService.getMemberFinances()
        callFinance.getMemberFinancials(year, id).enqueue(object : Callback<FinanceResponse> {
            override fun onResponse(
                call: Call<FinanceResponse>,
                response: Response<FinanceResponse>,
            ) {
                val result = response.body()
                val list = result?.results
                financesInfo.value = result
                financeList.value = list
            }

            override fun onFailure(call: Call<FinanceResponse>, t: Throwable) {
                Log.i(TAG, "Error: ${t.localizedMessage}, ${t.stackTrace}")
            }
        })
    }

    fun getQuarterlyExpenses(id: String, quarter: Int) {
        val callPro = ApiCallService.getProApi()
        callPro.getMemberExpenses(id, 2019, quarter)
            .enqueue(object : Callback<MemberQuarterlyExpensesResponse> {
                override fun onResponse(
                    call: Call<MemberQuarterlyExpensesResponse>,
                    response: Response<MemberQuarterlyExpensesResponse>,
                ) {
                    val result = response.body()
                    val expenses = result?.results
                    expensesList.value = expenses
                    expensesInfo.value = result
                }

                override fun onFailure(call: Call<MemberQuarterlyExpensesResponse>, t: Throwable) {
                    Log.i(TAG, "Error: ${t.localizedMessage}, ${t.stackTrace}")
                }
            })
    }

    fun getMemberBills(id: String, type: String) {
        val callBills = ApiCallService.getProApi()
        callBills.getMemberBills(id, type).enqueue(object : Callback<MemberBillsResponse> {
            override fun onResponse(
                call: Call<MemberBillsResponse>,
                response: Response<MemberBillsResponse>,
            ) {
                val result = response.body()
                val bills = result?.results
                val list = bills?.get(0)?.bills
                billsMember.value = list
            }

            override fun onFailure(call: Call<MemberBillsResponse>, t: Throwable) {
                Log.i(TAG, "Error: ${t.message} ${t.printStackTrace()}")
            }
        })
    }

    fun callCommittee(congress: Int, chamber: String, id: String) {
        val callCommittee = ApiCallService.getCommitteeDetail()
        callCommittee.getCommitteeDetails(congress, chamber, id)
            .enqueue(object : Callback<SingleCommitteeResponse> {
                override fun onResponse(
                    call: Call<SingleCommitteeResponse>,
                    response: Response<SingleCommitteeResponse>,
                ) {
                    val result = response.body()
                    val details = result?.results?.get(0) //the first item is the relevant item
                    committeeDetails.value = details
                    val members = details?.currentMembers
                    val comparableList = officials.value
                    val newList = ArrayList<Representative>()
                    if (members != null) {
                        for (member in members) {
                            if (comparableList != null) {
                                for (rep in comparableList) {
                                    if (member.id == rep.id) {
                                        newList.add(rep)
                                    }
                                }

                            }
                        }
                        committeeMembers.value = newList
                    }

                }

                override fun onFailure(call: Call<SingleCommitteeResponse>, t: Throwable) {
                    Log.i(TAG, "Error: ${t.message} ${t.printStackTrace()}")
                }
            })

    }

    fun callSingleProApi(id: String) {
        val callSingle = ApiCallService.getProSingle()
        callSingle.getMember(id).enqueue(object : Callback<SingleProPublicaResponse> {
            override fun onResponse(
                call: Call<SingleProPublicaResponse>,
                response: Response<SingleProPublicaResponse>,
            ) {
                val result = response.body()
                _singleResponse.value = result

                //getting the list of committees for the first position MOST RECENT

                val list = result?.results?.get(0)
                val sublist = list?.roles?.get(0)
              //  val billsSponsored = sublist?.billsSponsored
              //  val billsCosponsored = sublist?.billsCosponsored
                //this is now the most current role, where committees can now be fetched
                val comList = sublist?.committees
                committees.postValue(comList)
                val subComList = sublist?.subcommittees
                subCommittees.postValue(subComList)
            }

            override fun onFailure(call: Call<SingleProPublicaResponse>, t: Throwable) {
                Log.i(TAG, "Error: ${t.message} ${t.printStackTrace()}")
            }
        })
    }


    fun callApi(address: String, key: String) {
        setLoadingActive()
        val callGet = ApiCallService.get()
        callGet.getRepresentatives(address, key).enqueue(object : Callback<RepResponse> {
            override fun onResponse(call: Call<RepResponse>, response: Response<RepResponse>) {
                //  Log.i(TAG, "${response.body()}")
                _repResponse.value = response.body()
                val result = response.body()
                //logic function located in UtilityFunctions
                generateList(result, repList)
                setLoadingFinished()
                _officials.value = repList
            }

            override fun onFailure(call: Call<RepResponse>, t: Throwable) {
                Log.i(TAG, "Call failed: ${t.localizedMessage}, ${t.printStackTrace()}")
            }

        })
    }

    fun callOpenSecretsForBasicInfo(cid: String, cycle: String, key: String) {
        val callOpen = ApiCallService.getOpenApi()
        callOpen.getCandidateBasic(cid, cycle, key)
            .enqueue(object : Callback<OpenSecretGeneralResponse> {
                override fun onResponse(
                    call: Call<OpenSecretGeneralResponse>,
                    response: Response<OpenSecretGeneralResponse>,
                ) {
                    val result = response.body()
                    Log.i("NEWCALL", result?.response?.summary?.attributes?.cashOnHand.toString())
                    openSecretBasicResponse.value = result
                }

                override fun onFailure(call: Call<OpenSecretGeneralResponse>, t: Throwable) {
                    Log.i(TAG, "Call failed: ${t.localizedMessage}, ${t.printStackTrace()}")
                }
            })
    }

    fun callProPublicaAllMembers(congressNumber: Int, type: String) {
        setLoadingActive()
        val callProAllMembers = ApiCallService.getProAll()
        callProAllMembers.getAllMembers(congressNumber, type)
            .enqueue(object : Callback<ProPublicaBaseResult> {
                override fun onResponse(
                    call: Call<ProPublicaBaseResult>,
                    response: Response<ProPublicaBaseResult>,
                ) {
                    //get response as specified type
                    val result = response.body()
                    //logic function located in UtilityFunctions
                    mergeResultProPublica(result, repList)
                    setLoadingFinished()
                }

                override fun onFailure(call: Call<ProPublicaBaseResult>, t: Throwable) {
                    Log.i(TAG, "Call failed: ${t.localizedMessage}, ${t.printStackTrace()}")
                }
            })

    }

    private fun setLoadingFinished() {
        progressShowing.value = false
        recyclerMainShowing.value = true
    }

    private fun setLoadingActive() {
        progressShowing.value = true
        recyclerMainShowing.value = false
    }

    fun setRepVoteAgainstRating(rating: String) {
        _clickedRepRating.value = rating
    }

    fun setRepToView(rep: Representative) {
        _repToView.value = rep
    }

    fun clearList() {
        _officials.postValue(null)
        repList.clear()
    }

    fun setVoteClicked(vote: Vote){
        _voteClicked.value = vote
    }

    fun setAddress(address: String){
        _addressString.value = address
    }
    fun clearAddress(){
        _addressString.value = null
    }


}