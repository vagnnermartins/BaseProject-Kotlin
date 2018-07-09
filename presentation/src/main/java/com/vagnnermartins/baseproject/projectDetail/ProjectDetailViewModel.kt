package com.vagnnermartins.baseproject.projectDetail

import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import com.vagnnermartins.baseproject.Data
import com.vagnnermartins.baseproject.DataState
import com.vagnnermartins.baseproject.model.ProjectItem
import io.reactivex.Single
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class ProjectDetailViewModel @Inject constructor() : ViewModel() {

    val project = MutableLiveData<Data<ProjectItem>>()
    private val compositeDisposable: CompositeDisposable = CompositeDisposable()

    fun get(item: ProjectItem) =
            compositeDisposable.add(Single.just(item)
                    .subscribeOn(Schedulers.io())
                    .observeOn(Schedulers.io())
                    .subscribe(
                            { project.postValue(Data(DataState.SUCCESS, it, null)) },
                            { project.postValue(Data(DataState.ERROR, project.value?.data, it.message))}))

    override fun onCleared() {
        compositeDisposable.dispose()
        super.onCleared()
    }
}