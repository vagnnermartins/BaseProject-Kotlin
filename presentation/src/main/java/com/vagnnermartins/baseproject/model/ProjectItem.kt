package com.vagnnermartins.baseproject.model

import java.io.Serializable

const val PROJECT_ITEM = "PROJECT_ITEM"

data class ProjectItem(val id: String?, val name: String?, val fullName: String?,
                       val starCount: String?, val dateCreated: String?,
                       val ownerName: String?, val ownerAvatar: String, val description: String?,
                       val lastUpdated: String?, val language: String?): Serializable
