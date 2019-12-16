package com.example.anderson.repository.view.activity

import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import android.view.View
import com.google.android.material.navigation.NavigationView
import androidx.core.view.GravityCompat
import androidx.appcompat.app.ActionBarDrawerToggle
import android.view.MenuItem
import android.widget.AbsListView
import com.example.anderson.repository.R
import com.example.anderson.repository.model.entity.Repository
import com.example.anderson.repository.view.adapter.RepositoryAdapter
import com.example.anderson.repository.viewmodel.RepositoryViewModel
import kotlinx.android.synthetic.main.activity_repository.*
import kotlinx.android.synthetic.main.toolbar.*

class RepositoryActivity : BaseActivity() {

    private lateinit var repositoryViewModel: RepositoryViewModel
    private lateinit var repositoryAdapter: RepositoryAdapter
    private var isScrolling: Boolean = true
    private lateinit var manager: LinearLayoutManager


    var currentItems: Int = 0
    var totalItems: Int = 0
    var scrollOutItems: Int = 0


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_repository)

        initialize()

    }

    override fun initialize() {

        repositoryViewModel = ViewModelProviders.of(this)
                .get(RepositoryViewModel::class.java)

        initViews()

        observables()

        repositoryViewModel.callrequestRepository()
    }

    override fun initViews() {

        repositoryAdapter = RepositoryAdapter(this)

        manager = LinearLayoutManager(this)

        recyclerView.layoutManager = manager
        recyclerView.adapter = repositoryAdapter

        scrollInfinite()
    }

    override fun observables() {

        repositoryViewModel.getListRespository().observe(this, Observer { listRespository ->
            repositoryAdapter.loadRepository(listRespository!!)
            isScrolling = true

        })

        repositoryViewModel.showProgress().observe(this, Observer { isShow ->

            if (isShow!!) {
                progress_repository.visibility = View.VISIBLE

            } else {
                progress_repository.visibility = View.GONE
            }
        })
    }

    fun scrollInfinite() {

        recyclerView.addOnScrollListener(object : RecyclerView.OnScrollListener() {

            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)

                repositoryViewModel.getMoreItems(manager.childCount, manager.findFirstVisibleItemPosition(), manager.itemCount)

            }

        })
    }


}
