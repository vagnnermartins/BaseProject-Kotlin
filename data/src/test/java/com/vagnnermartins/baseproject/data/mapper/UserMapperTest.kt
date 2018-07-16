package com.vagnnermartins.baseproject.data.mapper

import com.vagnnermartins.baseproject.data.model.UserEntity
import com.vagnnermartins.baseproject.data.test.UserDataFactory
import com.vagnnermartins.baseproject.domain.model.UserDomain
import org.junit.Before
import org.junit.Test

class UserMapperTest {

    private lateinit var mapper: UserMapper

    @Before
    fun setUp() {
        mapper = UserMapper()
    }

    @Test
    fun testTransformEntityToDomain(){
        val domain: UserDomain = UserDataFactory.makeUserDomain()
        val entity: UserEntity = mapper.mapFromDomain(domain)
        assert(domain.name == entity.name)

    }

    @Test
    fun transformDomainToEntity(){
        val entity: UserEntity = UserDataFactory.makeUserEntity()
        val domain: UserDomain = mapper.mapFromEntity(entity)
        assert(domain.name == entity.name)
    }
}