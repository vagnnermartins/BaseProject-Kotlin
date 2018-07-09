package com.vagnnermartins.baseproject.projects

import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import com.vagnnermartins.baseproject.Data
import com.vagnnermartins.baseproject.DataState
import com.vagnnermartins.baseproject.domain.usecase.ProjectsUseCase
import com.vagnnermartins.baseproject.mapper.ProjectItemMapper
import com.vagnnermartins.baseproject.model.ProjectItem
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class ProjectsViewModel @Inject constructor(
        private val useCase: ProjectsUseCase,
        private val mapper : ProjectItemMapper) : ViewModel() {

    val projects = MutableLiveData<Data<List<ProjectItem>>>()
    private val compositeDisposable: CompositeDisposable = CompositeDisposable()

    fun get(refresh: Boolean, username: String?) =
            compositeDisposable.add(useCase.get(refresh, username!!)
                    .doOnSubscribe { projects.postValue(Data( DataState.LOADING,  null,  null)) }
                    .subscribeOn(Schedulers.io())
                    .observeOn(Schedulers.io())
                    .map { mapper.mapToPresentation(it) }
                    .subscribe(
                            { projects.postValue(Data(DataState.SUCCESS, it, null)) },
                            { projects.postValue(Data(DataState.ERROR, projects.value?.data, it.message))}))

    override fun onCleared() {
        compositeDisposable.dispose()
        super.onCleared()
    }
}