package com.vagnnermartins.baseproject.data.teste

import com.vagnnermartins.baseproject.data.model.OwnerEntity
import com.vagnnermartins.baseproject.data.model.ProjectEntity
import com.vagnnermartins.baseproject.domain.model.ProjectDomain
import com.vagnnermartins.baseproject.domain.randomInt
import com.vagnnermartins.baseproject.domain.randomString

object ProjectDataFactory{

    fun makeDomainProject(): ProjectDomain {
        return ProjectDomain(randomString(), randomString(), randomString(), randomInt().toString(),
                randomString(), randomString(), randomString(), randomString(), randomString(), randomString())
    }

    fun makeDomainProjectList(count: Int) : List<ProjectDomain> {
        val projects = mutableListOf<ProjectDomain>()
        repeat(count) {
            projects.add(makeDomainProject())
        }
        return projects
    }

    fun makeProjectEntity() : ProjectEntity{
        return ProjectEntity(randomString(), randomString(), randomString(),
                randomInt(), randomString(), OwnerEntity(randomString(), randomString()), randomString(),
                randomString(), randomString())
    }

    fun makeProjectEntityList(count: Int): List<ProjectEntity> {
        val projects = mutableListOf<ProjectEntity>()
        repeat(count){
            projects.add(makeProjectEntity())
        }
        return projects
    }
}