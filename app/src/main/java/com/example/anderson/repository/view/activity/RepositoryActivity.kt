package com.example.anderson.repository.view.activity

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.support.v7.app.AppCompatActivity
import android.os.Bundle

import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.View
import android.support.design.widget.NavigationView
import android.support.v4.view.GravityCompat
import android.support.v7.app.ActionBarDrawerToggle
import android.view.MenuItem
import android.widget.AbsListView
import com.example.anderson.repository.R
import com.example.anderson.repository.model.entity.Repository
import com.example.anderson.repository.view.adapter.RepositoryAdapter
import com.example.anderson.repository.viewmodel.RepositoryViewModel
import kotlinx.android.synthetic.main.activity_repository.*
import kotlinx.android.synthetic.main.toolbar.*

class RepositoryActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener {

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

        botaoLayoutDrawer()
        configurarNavigationView()

        repositoryAdapter = RepositoryAdapter(this)

        manager = LinearLayoutManager(this)

        recyclerView.layoutManager = manager
        recyclerView.adapter = repositoryAdapter


        repositoryViewModel = ViewModelProviders.of(this)
                .get(RepositoryViewModel::class.java)


        repositoryViewModel.callrequestRepository()



        repositoryViewModel.getListRespository().observe(this, Observer { listRespository ->
            repositoryAdapter.loadRepository(listRespository!!)
            isScrolling = true

        })

        repositoryViewModel.showProgress().observe(this, Observer { isShow ->
            progress_repository.progress = 100
            if (isShow!!) {
                progress_repository.visibility = View.GONE

            }
        })

        recyclerView.addOnScrollListener(object : RecyclerView.OnScrollListener() {


            override fun onScrolled(recyclerView: RecyclerView?, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)

                currentItems = manager.childCount
                totalItems = manager.itemCount
                scrollOutItems = manager.findFirstVisibleItemPosition()

               // if (dy > 0) {

                    if (isScrolling && (currentItems + scrollOutItems) >= totalItems) {


                        repositoryViewModel.requestRepository()


                       isScrolling = false


                    }

                }

          //  }


        })

       // statusRecyclerView()

    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }


    fun configurarNavigationView() {
        navView.setNavigationItemSelectedListener(this)
    }

    fun botaoLayoutDrawer() {

        var toggle = ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.open_drawer, R.string.close_drawer)
        drawerLayout.addDrawerListener(toggle)

        toggle.syncState()
    }

    override fun onBackPressed() {

        if (drawerLayout.isDrawerOpen(GravityCompat.START)) {

            drawerLayout.closeDrawer(GravityCompat.START)

        } else {
            super.onBackPressed()
        }
    }


    }
