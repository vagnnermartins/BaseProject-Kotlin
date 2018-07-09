package com.vagnnermartins.baseproject.domain.usecase

import com.nhaarman.mockito_kotlin.whenever
import com.vagnnermartins.baseproject.domain.model.UserDomain
import com.vagnnermartins.baseproject.domain.repository.UserRepository
import com.vagnnermartins.baseproject.domain.test.UserDomainFactory
import io.reactivex.Single
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.MockitoAnnotations

class UserUseCaseTest {

    private lateinit var usecase: UserUseCase
    @Mock
    lateinit var userRepository: UserRepository

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        usecase = UserUseCase(userRepository)
    }

    @Test
    fun testGetUser(){
        stubUserRepositoryGetUser(
                Single.just(UserDomainFactory.makeUser()))
        val testObserver = usecase.get().test()
        testObserver.assertComplete()
    }

    @Test
    fun getProjectsReturnsData() {
        val user = UserDomainFactory.makeUser()
        stubUserRepositoryGetUser(Single.just(user))
        val testObserver = usecase.get().test()
        testObserver.assertValue(user)
    }

    private fun stubUserRepositoryGetUser(observable: Single<UserDomain>) {
        whenever(userRepository.getUser())
                .thenReturn(observable)
    }
}