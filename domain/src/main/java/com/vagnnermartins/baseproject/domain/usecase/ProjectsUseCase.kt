package com.vagnnermartins.baseproject.domain.usecase

import com.vagnnermartins.baseproject.domain.model.ProjectDomain
import com.vagnnermartins.baseproject.domain.repository.ProjectsRepository
import io.reactivex.Single
import javax.inject.Inject

class ProjectsUseCase @Inject constructor(private val repository: ProjectsRepository) {

    fun get(refresh: Boolean, username: String) : Single<List<ProjectDomain>> = repository.getProjects(refresh, username)
}