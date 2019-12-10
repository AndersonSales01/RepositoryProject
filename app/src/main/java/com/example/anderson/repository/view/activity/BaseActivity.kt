package com.example.anderson.repository.view.activity

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.example.anderson.repository.R

abstract class BaseActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_base)
    }

  abstract fun initialize()
  abstract fun initViews()
  abstract fun Observables()
}
