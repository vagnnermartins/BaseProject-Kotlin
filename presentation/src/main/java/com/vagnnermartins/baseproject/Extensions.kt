package com.vagnnermartins.baseproject

import android.app.Activity
import android.arch.lifecycle.*
import android.arch.lifecycle.Observer
import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkInfo
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentActivity
import android.support.v4.widget.SwipeRefreshLayout
import android.support.v7.app.AppCompatActivity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import com.squareup.picasso.Picasso
import com.vagnnermartins.baseproject.di.Injector
import org.jetbrains.anko.toast
import java.text.SimpleDateFormat
import java.util.*

val Activity.app: App get() = application as App
val Fragment.app: App get() = activity?.application as App

fun App.hasInternet(): Boolean {
    val connectivityManager = getSystemService(Context.CONNECTIVITY_SERVICE)
    val hasInternet = if (connectivityManager is ConnectivityManager) {
        val networkInfo: NetworkInfo? = connectivityManager.activeNetworkInfo
        networkInfo?.isConnected ?: false
    } else false
    if (!hasInternet) toast(R.string.no_internet)
    return hasInternet
}

fun AppCompatActivity.getAppInjector(): Injector = (app).injector

fun Fragment.getAppInjector(): Injector = (app).injector

fun ViewGroup.inflate(layoutId: Int, attachToRoot: Boolean = false): View = LayoutInflater.from(context).inflate(layoutId, this, attachToRoot)

fun View.visible() {
    visibility = View.VISIBLE
}

fun View.gone() {
    visibility = View.GONE
}

fun SwipeRefreshLayout.startRefreshing() {
    isRefreshing = true
}

fun SwipeRefreshLayout.stopRefreshing() {
    isRefreshing = false
}

inline fun <reified T : ViewModel> FragmentActivity.getViewModel(viewModelFactory: ViewModelProvider.Factory): T {
    return ViewModelProviders.of(this, viewModelFactory)[T::class.java]
}

inline fun <reified T : ViewModel> Fragment.getViewModel(viewModelFactory: ViewModelProvider.Factory): T {
    return ViewModelProviders.of(this, viewModelFactory)[T::class.java]
}

inline fun <reified T : ViewModel> FragmentActivity.withViewModel(viewModelFactory: ViewModelProvider.Factory, body: T.() -> Unit): T {
    val vm = getViewModel<T>(viewModelFactory)
    vm.body()
    return vm
}

inline fun <reified T : ViewModel> Fragment.withViewModel(viewModelFactory: ViewModelProvider.Factory, body: T.() -> Unit): T {
    val vm = getViewModel<T>(viewModelFactory)
    vm.body()
    return vm
}

fun <T : Any, L : LiveData<T>> LifecycleOwner.observe(liveData: L, body: (T?) -> Unit) {
    liveData.observe(this, Observer(body))
}

fun ImageView.load(url: String?) = Picasso.get().load(url).into(this)

fun String.dateFormat(format: String) : String {
    return SimpleDateFormat(format).format(this.toDate())
}

fun String.toDate(): Date{
    return SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'").parse(this)
}