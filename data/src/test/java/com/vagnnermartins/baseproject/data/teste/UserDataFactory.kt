package com.vagnnermartins.baseproject.data.teste

import com.vagnnermartins.baseproject.data.model.UserEntity
import com.vagnnermartins.baseproject.domain.model.UserDomain
import com.vagnnermartins.baseproject.domain.randomString

object UserDataFactory {

    fun makeUserDomain(): UserDomain = UserDomain(randomString())

    fun makeUserEntity(): UserEntity = UserEntity(randomString())
}