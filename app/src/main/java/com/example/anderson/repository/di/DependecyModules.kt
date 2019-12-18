package com.example.anderson.repository.di

import com.example.anderson.repository.model.repository.RepoPullRequestImpl
import com.example.anderson.repository.model.repository.RepoRepositoryImpl
import com.example.anderson.repository.viewmodel.PullRequestViewModel
import com.example.anderson.repository.viewmodel.RepositoryViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

object DependecyModules {

    val appModule = module{

        single {
            RepoRepositoryImpl()
        }

        single {
            RepoPullRequestImpl()
        }

        viewModel {
            RepositoryViewModel(get())
        }

        viewModel {
            PullRequestViewModel(get())
        }
    }
}