package com.vagnnermartins.baseproject

import android.app.Application
import com.pacoworks.rxpaper2.RxPaperBook
import android.util.Log
import com.vagnnermartins.baseproject.di.AppModule
import com.vagnnermartins.baseproject.di.DaggerInjector
import com.vagnnermartins.baseproject.di.Injector
import io.reactivex.plugins.RxJavaPlugins

class App : Application() {

    lateinit var injector: Injector private set

    override fun onCreate() {
        super.onCreate()
        init()
    }

    private fun init() {
        initDagger()
        initRxPaper()
        initRxJavaPluginsErrorHandler()
    }

    private fun initDagger() {
        injector = DaggerInjector
                .builder()
                .appModule(AppModule(this))
                .build()
    }

    private fun initRxPaper() = RxPaperBook.init(this)

    private fun initRxJavaPluginsErrorHandler() = RxJavaPlugins.setErrorHandler { Log.d("ERROR", it.localizedMessage) }
}