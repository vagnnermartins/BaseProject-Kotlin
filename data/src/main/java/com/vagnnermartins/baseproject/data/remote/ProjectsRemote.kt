package com.vagnnermartins.baseproject.data.remote

import com.vagnnermartins.baseproject.data.model.ProjectEntity
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Path

interface ProjectsRemote {

    @GET("/users/{username}/repos")
    fun getProjects(
            @Path("username")
            username: String): Single<List<ProjectEntity>>
}