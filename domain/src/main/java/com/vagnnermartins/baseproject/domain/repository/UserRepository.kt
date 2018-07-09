package com.vagnnermartins.baseproject.domain.repository

import com.vagnnermartins.baseproject.domain.model.UserDomain
import io.reactivex.Single

interface UserRepository {

    val key: String

    fun getUser(): Single<UserDomain>

    fun saveUser(user: UserDomain): Single<UserDomain>

    fun hasUser(): Single<Boolean>

}