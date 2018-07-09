package com.vagnnermartins.baseproject.data.mapper

import com.vagnnermartins.baseproject.data.model.UserEntity
import com.vagnnermartins.baseproject.domain.model.UserDomain
import javax.inject.Inject

open class UserMapper @Inject constructor() : EntityMapper<UserEntity, UserDomain> {
    override fun mapFromEntity(entities: List<UserEntity>): List<UserDomain> =
        throw NotImplementedError("This method is not used for this class")

    override fun mapFromEntity(entity: UserEntity): UserDomain {
        return UserDomain(entity.name)
    }

    override fun mapFromDomain(domain: UserDomain): UserEntity {
        return UserEntity(domain.name)
    }

    override fun mapFromDomain(domains: List<UserDomain>): List<UserEntity> =
            throw NotImplementedError("This method is not used for this class")
}