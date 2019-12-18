package com.example.anderson.repository

import android.app.Application
import com.example.anderson.repository.di.DependecyModules.appModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

class MyApplication : Application() {

    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidLogger()
            androidContext(this@MyApplication)


            modules(
                    /**
                     * Lista de modulos onde são declaradas as dependências
                     * para novos modulos, criá-los em `modules/` e registrá-los abaixo
                     */
                    listOf(
                            appModule
                    )
            )

        }


    }

}