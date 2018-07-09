package com.vagnnermartins.baseproject.domain.test

import com.vagnnermartins.baseproject.domain.model.ProjectDomain
import com.vagnnermartins.baseproject.domain.randomString

val USER = "vagnnermartins"

object ProjectDomainFactory{

    fun makeProject(): ProjectDomain {
        return ProjectDomain(randomString(), randomString(), randomString(), randomString(),
                randomString(), randomString(), randomString(), randomString(), randomString(), randomString())
    }

    fun makeProjectList(count: Int) : List<ProjectDomain> {
        val projects = mutableListOf<ProjectDomain>()
        repeat(count) {
            projects.add(makeProject())
        }
        return projects
    }
}