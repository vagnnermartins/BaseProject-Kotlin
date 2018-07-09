package com.vagnnermartins.baseproject.data.repository

import com.vagnnermartins.baseproject.data.cache.Cache
import com.vagnnermartins.baseproject.data.mapper.UserMapper
import com.vagnnermartins.baseproject.data.model.UserEntity
import com.vagnnermartins.baseproject.domain.model.UserDomain
import com.vagnnermartins.baseproject.domain.repository.UserRepository
import io.reactivex.Single
import javax.inject.Inject

class UserDataRepository @Inject constructor(private val cache: Cache<UserEntity>,
                                             private val mapper: UserMapper) : UserRepository {
    override val key = "User"

    override fun getUser(): Single<UserDomain> = cache.load(key).map { mapper.mapFromEntity(it) }

    override fun saveUser(user: UserDomain): Single<UserDomain> = cache.save(key, mapper.mapFromDomain(user)).map { mapper.mapFromEntity(it) }

    override fun hasUser(): Single<Boolean> = cache.contains(key)
}