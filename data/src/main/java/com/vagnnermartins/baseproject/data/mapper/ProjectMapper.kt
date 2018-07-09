package com.vagnnermartins.baseproject.data.mapper

import com.vagnnermartins.baseproject.data.model.OwnerEntity
import com.vagnnermartins.baseproject.data.model.ProjectEntity
import com.vagnnermartins.baseproject.domain.model.ProjectDomain
import javax.inject.Inject

open class ProjectMapper @Inject constructor() : EntityMapper<ProjectEntity, ProjectDomain> {
    override fun mapFromEntity(entities: List<ProjectEntity>): List<ProjectDomain> = entities.map { mapFromEntity(it) }

    override fun mapFromEntity(entity: ProjectEntity): ProjectDomain {
        return ProjectDomain(entity.id, entity.name, entity.fullName, entity.starCount.toString(),
                entity.dateCreated, entity.owner.name, entity.owner.avatar, entity.description,
                entity.lastUpdated, entity.language)
    }

    override fun mapFromDomain(domain: ProjectDomain): ProjectEntity {
        return ProjectEntity(domain.id, domain.name, domain.fullName, domain.starCount!!.toInt(),
                domain.dateCreated, OwnerEntity(domain.ownerName!!, domain.ownerAvatar!!),
                domain.description, domain.lastUpdated, domain.language)
    }

    override fun mapFromDomain(domains: List<ProjectDomain>): List<ProjectEntity> = domains.map { mapFromDomain(it) }
}