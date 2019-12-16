package com.example.anderson.repository.view.activity

import androidx.lifecycle.ViewModelProviders
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.anderson.repository.R
import com.example.anderson.repository.constants.Constants
import com.example.anderson.repository.view.adapter.PullRequestAdapter
import com.example.anderson.repository.viewmodel.PullRequestViewModel
import kotlinx.android.synthetic.main.activity_pull_request.*
import androidx.lifecycle.Observer
import android.view.View
import kotlinx.coroutines.*


class PullRequestActivity : BaseActivity(), CoroutineScope {

    private lateinit var pullRequestViewModel: PullRequestViewModel
    private lateinit var pullRequestAdapter: PullRequestAdapter
    private  lateinit var nameRepository: String
    private  lateinit var nameOwner: String

    // Um job representa uma ou conjunto de tarefas em backgroud.
    private val job = Job()

    override val coroutineContext
        get() = Dispatchers.Main + job


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_pull_request)

        initialize()
    }

    override fun initialize(){

        pullRequestViewModel = ViewModelProviders.of(this)
                .get(PullRequestViewModel::class.java)

        initViews()

        observables()

        launch {

            withContext(Dispatchers.IO){
                pullRequestViewModel.callRequestPullResquest(nameOwner, nameRepository)
            }

        }



    }



    override fun initViews() {

        pullRequestAdapter = PullRequestAdapter(this)

        recyclerView_pull_request.layoutManager = LinearLayoutManager(this)
        recyclerView_pull_request.adapter = pullRequestAdapter



        val intent = intent
        nameRepository = intent.getStringExtra(Constants.NAME_REPOSITORY)
        nameOwner = intent.getStringExtra(Constants.NAME_OWNER)

        val actionbar = supportActionBar

        if (nameRepository != "") {

            if (actionbar != null) {
                actionbar.title = nameRepository
            }
        }

    }

    override fun observables() {

        pullRequestViewModel.getListPullRequests().observe(this, Observer { listPullRquests ->
            pullRequestAdapter.loadPullRequest(listPullRquests!!)

        })

        pullRequestViewModel.showProgress().observe(this, Observer { isShow ->
            if (isShow!!) {
                progress_pull_request.visibility = View.GONE

            }
        })
    }

}
