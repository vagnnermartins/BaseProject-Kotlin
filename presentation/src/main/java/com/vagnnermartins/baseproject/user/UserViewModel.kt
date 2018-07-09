package com.vagnnermartins.baseproject.user

import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import com.vagnnermartins.baseproject.Data
import com.vagnnermartins.baseproject.DataState
import com.vagnnermartins.baseproject.domain.usecase.UserUseCase
import com.vagnnermartins.baseproject.mapper.UserItemMapper
import com.vagnnermartins.baseproject.model.UserItem
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class UserViewModel @Inject constructor(
        private val useCase: UserUseCase,
        private val mapper: UserItemMapper) : ViewModel() {

    val user = MutableLiveData<Data<UserItem>>()
    val hasUser = MutableLiveData<Data<Boolean>>()
    private val compositeDisposable: CompositeDisposable = CompositeDisposable()

    fun getUser() = compositeDisposable.add(useCase.get()
                    .doOnSubscribe { user.postValue(Data(DataState.LOADING, user.value?.data, null)) }
                    .subscribeOn(Schedulers.io())
                    .observeOn(Schedulers.io())
                    .map { mapper.mapToPresentation(it) }
                    .subscribe(
                            {user.postValue(Data(DataState.SUCCESS, it, null))},
                            {user.postValue(Data(DataState.ERROR, user.value?.data, it.message))}))

    fun checkHasUser() = compositeDisposable.add(useCase.hasUser()
                    .doOnSubscribe { hasUser.postValue(Data(DataState.LOADING,hasUser.value?.data, null)) }
                    .subscribeOn(Schedulers.io())
                    .observeOn(Schedulers.io())
                    .subscribe(
                            {hasUser.postValue(Data(DataState.SUCCESS, it, null))},
                            {hasUser.postValue(Data(DataState.ERROR, hasUser.value?.data, it.message))}))

    fun saveUser(user: UserItem) = compositeDisposable.add(useCase.saveUser(mapper.mapToDomain(user))
            .doOnSubscribe { this.user.postValue(Data(DataState.LOADING, this.user.value?.data, null)) }
            .subscribeOn(Schedulers.io())
            .observeOn(Schedulers.io())
            .map { mapper.mapToPresentation(it) }
            .subscribe(
                    {this.user.postValue(Data(DataState.SUCCESS, it, null))},
                    {this.user.postValue(Data(DataState.ERROR, this.user.value?.data, it.message))}))

    override fun onCleared() {
        compositeDisposable.dispose()
        super.onCleared()
    }
}