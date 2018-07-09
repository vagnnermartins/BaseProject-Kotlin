package com.vagnnermartins.baseproject.mapper

import com.vagnnermartins.baseproject.domain.model.UserDomain
import com.vagnnermartins.baseproject.model.UserItem
import javax.inject.Inject

class UserItemMapper @Inject constructor(): ItemMapper<UserDomain, UserItem> {

    override fun mapToPresentation(domain: UserDomain): UserItem = UserItem(domain.name)

    override fun mapToPresentation(domains: List<UserDomain>): List<UserItem> =
            domains.map { mapToPresentation(it) }

    override fun mapToDomain(presentation: UserItem): UserDomain = UserDomain(presentation.name)

    override fun mapToDomain(presentations: List<UserItem>): List<UserDomain> =
            presentations.map { mapToDomain(it) }
}