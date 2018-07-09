package com.vagnnermartins.baseproject.domain.usecase

import com.vagnnermartins.baseproject.domain.model.UserDomain
import com.vagnnermartins.baseproject.domain.repository.UserRepository
import io.reactivex.Single
import javax.inject.Inject

class UserUseCase @Inject constructor(private val repository: UserRepository) {

    fun get() : Single<UserDomain> = repository.getUser()

    fun hasUser(): Single<Boolean> = repository.hasUser()

    fun saveUser(user: UserDomain): Single<UserDomain> = repository.saveUser(user)
}