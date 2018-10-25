package com.vagnnermartins.baseproject.user

import com.nhaarman.mockito_kotlin.mock
import com.vagnnermartins.baseproject.Data
import com.vagnnermartins.baseproject.DataState
import com.vagnnermartins.baseproject.domain.model.UserDomain
import com.vagnnermartins.baseproject.domain.randomString
import com.vagnnermartins.baseproject.domain.usecase.UserUseCase
import com.vagnnermartins.baseproject.mapper.UserItemMapper
import io.reactivex.Single
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import org.mockito.Mockito.`when` as mWhen
import io.reactivex.plugins.RxJavaPlugins
import io.reactivex.internal.schedulers.ExecutorScheduler
import io.reactivex.Scheduler.Worker
import io.reactivex.disposables.Disposable
import io.reactivex.Scheduler
import java.util.concurrent.Executor


class UserViewModelTest {

    private lateinit var viewModel: UserViewModel

    private val mockUserUseCase = mock<UserUseCase>()

    private val mapper = UserItemMapper()

    private val user = UserDomain(randomString())

    private val throwable = Throwable()

    @Before
    fun setUp() {
        val immediate = object : Scheduler() {

            override fun createWorker(): Worker {
                return ExecutorScheduler.ExecutorWorker(Executor { it.run() })
            }
        }

        RxJavaPlugins.setInitIoSchedulerHandler { scheduler -> immediate }
        RxJavaPlugins.setInitComputationSchedulerHandler { scheduler -> immediate }
        RxJavaPlugins.setInitNewThreadSchedulerHandler { scheduler -> immediate }
        RxJavaPlugins.setInitSingleSchedulerHandler { scheduler -> immediate }
    }

    @Test
    fun testGetUser(){
        mWhen(mockUserUseCase.get()).thenReturn(Single.just(user))

        viewModel = UserViewModel(mockUserUseCase, mapper)
        viewModel.getUser()

        Assert.assertEquals(Data(dataState = DataState.SUCCESS, data = mapper.mapToPresentation(user), message = null), viewModel.user.value)
    }
}