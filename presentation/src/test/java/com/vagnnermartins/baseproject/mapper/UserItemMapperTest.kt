package com.vagnnermartins.baseproject.mapper

import com.vagnnermartins.baseproject.data.test.UserDataFactory
import com.vagnnermartins.baseproject.domain.model.UserDomain
import com.vagnnermartins.baseproject.model.UserItem
import com.vagnnermartins.baseproject.test.UserPresentationFactory
import org.junit.Before
import org.junit.Test

class UserItemMapperTest {

    private lateinit var mapper: UserItemMapper

    @Before
    fun setUp() {
        mapper = UserItemMapper()
    }

    @Test
    fun testTransformDomainToItem(){
        val domain: UserDomain = UserDataFactory.makeUserDomain()
        val item: UserItem = mapper.mapToPresentation(domain)
        testItem(domain, item)
    }

    @Test
    fun testTranformItemToDomain(){
        val item: UserItem = UserPresentationFactory.makeUser()
        val domain: UserDomain = mapper.mapToDomain(item)
        testItem(domain, item)
    }

    private fun testItem(domain: UserDomain, item: UserItem) {
        assert(domain.name == item.name)
    }
}