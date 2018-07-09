package com.vagnnermartins.baseproject.data.repository

import com.vagnnermartins.baseproject.data.cache.Cache
import com.vagnnermartins.baseproject.data.mapper.ProjectMapper
import com.vagnnermartins.baseproject.data.model.ProjectEntity
import com.vagnnermartins.baseproject.data.remote.ProjectsRemote
import com.vagnnermartins.baseproject.domain.model.ProjectDomain
import com.vagnnermartins.baseproject.domain.repository.ProjectsRepository
import io.reactivex.Single
import javax.inject.Inject

class ProjectsDataRepository @Inject constructor(private val api: ProjectsRemote,
                                                 private val cache: Cache<List<ProjectEntity>>,
                                                 private val mapper: ProjectMapper) : ProjectsRepository {
    override val key = "ProjectsList"

    override fun getProjects(refresh: Boolean, username: String): Single<List<ProjectDomain>> = when(refresh) {
        true -> api.getProjects(username).flatMap { set(it) }.map { mapper.mapFromEntity(it) }
        false -> cache.load(key).map { mapper.mapFromEntity(it) }
    }

    private fun set(list: List<ProjectEntity>) = cache.save(key, list)
}