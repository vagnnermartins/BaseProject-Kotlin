package com.vagnnermartins.baseproject.data.model

import com.squareup.moshi.Json

data class UserEntity(@Json(name = "login") val name: String)