package com.vagnnermartins.baseproject.user

import com.nhaarman.mockito_kotlin.mock
import com.vagnnermartins.baseproject.domain.model.UserDomain
import com.vagnnermartins.baseproject.domain.randomString
import com.vagnnermartins.baseproject.domain.usecase.UserUseCase
import com.vagnnermartins.baseproject.mapper.UserItemMapper
import io.reactivex.Single
import org.junit.Assert
import org.junit.Before
import org.junit.Test

import org.mockito.Mockito.`when` as mWhen

class UserViewModelTest {

    private lateinit var viewModel: UserViewModel

    private val mockUserUseCase = mock<UserUseCase>()

    private val mapper = UserItemMapper()

    private val user = UserDomain(randomString())

    private val throwable = Throwable()

    @Before
    fun setUp() {
        viewModel = UserViewModel(mockUserUseCase, mapper)
    }

    @Test
    fun testSaveAndGetUser(){
        mWhen(mockUserUseCase.get()).thenReturn(Single.just(user))

        viewModel.saveUser(mapper.mapToPresentation(user))

        viewModel.getUser()

        Assert.assertEquals(mapper.mapToPresentation(user), viewModel.user.value)
    }
}