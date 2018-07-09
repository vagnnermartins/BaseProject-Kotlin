package com.vagnnermartins.baseproject.domain.test

import com.vagnnermartins.baseproject.domain.model.UserDomain
import com.vagnnermartins.baseproject.domain.randomString

object UserDomainFactory{

    fun makeUser(): UserDomain {
        return UserDomain(randomString())
    }
}