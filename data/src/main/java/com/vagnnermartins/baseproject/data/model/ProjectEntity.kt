package com.vagnnermartins.baseproject.data.model

import com.squareup.moshi.Json

data class ProjectEntity(@Json(name = "id") val id: String?,
                         @Json(name = "name") val name: String?,
                         @Json(name = "full_name") val fullName: String?,
                         @Json(name = "stargazers_count") val starCount: Int,
                         @Json(name = "created_at") val dateCreated: String?,
                         val owner: OwnerEntity,
                         @Json(name = "description") val description: String?,
                         @Json(name = "updated_at") val lastUpdated: String?,
                         @Json(name = "language") val language: String?)

data class OwnerEntity(@Json(name = "login") val name: String, @Json(name = "avatar_url") val avatar: String)