package com.vagnnermartins.baseproject.test

import com.vagnnermartins.baseproject.domain.randomString
import com.vagnnermartins.baseproject.model.UserItem

object UserPresentationFactory {

    fun makeUser(): UserItem = UserItem(randomString())

    fun makeUserList(count: Int): List<UserItem>{
        val projects = mutableListOf<UserItem>()
        repeat(count){
            projects.add(makeUser())
        }
        return projects
    }
}