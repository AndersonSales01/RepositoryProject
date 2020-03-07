package com.example.anderson.repository


import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.example.anderson.repository.model.repository.RepoPullRequestImpl
import com.example.anderson.repository.viewmodel.PullRequestViewModel
import junit.framework.Assert.*
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import androidx.test.runner.AndroidJUnit4
import org.junit.Rule

@RunWith(AndroidJUnit4::class)
//@RunWith(MockitoJUnitRunner::class)
class PullRequestTest {

    private lateinit var pullRequestViewModel: PullRequestViewModel

    // Responsavel por permitir pegar resultado  através do observer
    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()


    @Before
    fun setUp() {
//        MockitoAnnotations.initMocks(this)
        pullRequestViewModel = PullRequestViewModel(RepoPullRequestImpl())
    }


    @Test
    fun checkIfValueOfSomIsValid() {
        var resultCalculate = 0
        pullRequestViewModel.Soma(2, 3)
        pullRequestViewModel.getResultCalculate().observeForever { result ->
            resultCalculate = result
        }

        assertEquals(5, resultCalculate)

    }


    @Test
    fun checkIfValueOfDuminuicaoIsValid() {
        var resultCalculate = 0
        pullRequestViewModel.diminuicao(3, 2)
        pullRequestViewModel.getResultCalculate().observeForever { result ->
            resultCalculate = result
        }

        assertEquals(1, resultCalculate)

    }


    @Test
    fun checkIfValueOfDuminuicaoIsTrue() {
        var resultCalculate = false
        pullRequestViewModel.diminuicao(3, 2)
        pullRequestViewModel.getResultCalculate().observeForever { result ->
            resultCalculate = result == 1
        }

        assertTrue(resultCalculate)

    }

    @Test
    fun checkIfValueOfDuminuicaoIsFalse() {
        var resultCalculate = false
        pullRequestViewModel.diminuicao(4, 2)
        pullRequestViewModel.getResultCalculate().observeForever { result ->
            resultCalculate = result == 1
        }

        assertFalse(resultCalculate)
    }

    @Test
    fun checkIfValueOfSomIsTrue() {
        var resultCalculate = false
        pullRequestViewModel.Soma(3, 2)
        pullRequestViewModel.getResultCalculate().observeForever { result ->
            resultCalculate = result == 5
        }

        assertTrue(resultCalculate)

    }

    @Test
    fun checkIfValueOfSomIsFalse() {
        var resultCalculate = false
        pullRequestViewModel.Soma(3, 1)
        pullRequestViewModel.getResultCalculate().observeForever { result ->
            resultCalculate = result == 5
        }

        assertFalse(resultCalculate)

    }

}