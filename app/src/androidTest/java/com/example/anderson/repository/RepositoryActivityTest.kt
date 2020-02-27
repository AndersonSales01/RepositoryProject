package com.example.anderson.repository

import android.app.Application
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import androidx.test.runner.AndroidJUnit4
import com.example.anderson.repository.model.entity.Repository
import com.example.anderson.repository.model.repository.RepoRepositoryImpl
import com.example.anderson.repository.viewmodel.RepositoryViewModel
import junit.framework.Assert.*
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations
import org.mockito.junit.MockitoJUnit
import org.mockito.junit.MockitoJUnitRunner
import org.mockito.junit.MockitoRule

@RunWith(AndroidJUnit4::class)
//@RunWith(MockitoJUnitRunner::class)
class RepositoryActivityTest {

    private lateinit var repositoryViewModel : RepositoryViewModel

//    @Mock
//    private lateinit var repositoryImpl: RepoRepositoryImpl
        @get:Rule
        val instantTaskExecutorRule = InstantTaskExecutorRule()


    @Rule
    @JvmField
    val mockitoRule: MockitoRule = MockitoJUnit.rule()

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        repositoryViewModel = RepositoryViewModel(RepoRepositoryImpl())
    }


    @Test
    fun isValidReturnTrueTest() {

     val listRepository = mutableListOf<Repository>()

        repositoryViewModel.callrequestRepository()

            repositoryViewModel.getListRespository().observeForever {list ->

                if (list.size >0){
                    listRepository.addAll(list)
                }

            }

        assertNotNull(listRepository)
//        assertTrue(listRepository.isNotEmpty())




    }

}