package com.vagnnermartins.baseproject.domain.repository

import com.vagnnermartins.baseproject.domain.model.ProjectDomain
import io.reactivex.Single

interface ProjectsRepository {

    val key: String

    fun getProjects(refresh: Boolean, username: String): Single<List<ProjectDomain>>

}