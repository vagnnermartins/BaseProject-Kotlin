package com.vagnnermartins.baseproject.projects

import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import android.content.Context
import com.vagnnermartins.baseproject.Data
import com.vagnnermartins.baseproject.DataState
import com.vagnnermartins.baseproject.R
import com.vagnnermartins.baseproject.domain.usecase.ProjectsUseCase
import com.vagnnermartins.baseproject.mapper.ProjectItemMapper
import com.vagnnermartins.baseproject.model.ProjectItem
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import retrofit2.HttpException
import javax.inject.Inject
import android.support.test.espresso.idling.CountingIdlingResource



class ProjectsViewModel @Inject constructor(
        private val useCase: ProjectsUseCase,
        private val mapper : ProjectItemMapper) : ViewModel() {

    val projects = MutableLiveData<Data<List<ProjectItem>>>()
    private val compositeDisposable: CompositeDisposable = CompositeDisposable()

    fun get(activity: ProjectsActivity, refresh: Boolean, username: String?) =
            compositeDisposable.add(useCase.get(refresh, username!!)
                    .doOnSubscribe {
                        activity.espressoTestIdlingResource.increment()
                        projects.postValue(Data( DataState.LOADING,  null,  null))
                    }
                    .subscribeOn(Schedulers.io())
                    .observeOn(Schedulers.io())
                    .map { mapper.mapToPresentation(it) }
                    .subscribe(
                            {
                                projects.postValue(Data(DataState.SUCCESS, it, null))
                                activity.espressoTestIdlingResource.decrement()
                            },
                            {
                                if(it is HttpException && it.code() == 404){
                                    projects.postValue(Data(DataState.SUCCESS, listOf(), activity.getString(R.string.no_projects)))
                                }else{
                                    projects.postValue(Data(DataState.ERROR, projects.value?.data, it.message))
                                }
                                activity.espressoTestIdlingResource.decrement()
                            }
                    ))

    override fun onCleared() {
        compositeDisposable.dispose()
        super.onCleared()
    }
}