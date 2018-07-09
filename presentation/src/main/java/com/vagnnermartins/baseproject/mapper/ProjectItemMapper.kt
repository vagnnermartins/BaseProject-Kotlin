package com.vagnnermartins.baseproject.mapper

import com.vagnnermartins.baseproject.domain.model.ProjectDomain
import com.vagnnermartins.baseproject.model.ProjectItem
import javax.inject.Inject

class ProjectItemMapper @Inject constructor(): ItemMapper<ProjectDomain, ProjectItem> {

    override fun mapToPresentation(domain: ProjectDomain): ProjectItem = ProjectItem(domain.id,
            domain.name, domain.fullName, domain.starCount, domain.dateCreated, domain.ownerName!!,
            domain.ownerAvatar!!, domain.description, domain.lastUpdated, domain.language)

    override fun mapToPresentation(domains: List<ProjectDomain>): List<ProjectItem> =
            domains.map { mapToPresentation(it) }

    override fun mapToDomain(presentation: ProjectItem): ProjectDomain = ProjectDomain(presentation.id,
            presentation.name, presentation.fullName, presentation.starCount, presentation.dateCreated,
            presentation.ownerName, presentation.ownerAvatar, presentation.description,
            presentation.lastUpdated, presentation.language)

    override fun mapToDomain(presentations: List<ProjectItem>): List<ProjectDomain> =
            presentations.map { mapToDomain(it) }
}