package com.vagnnermartins.baseproject.test

import com.vagnnermartins.baseproject.domain.randomString
import com.vagnnermartins.baseproject.model.ProjectItem

object ProjectsPresentationFactory {

    fun makeProject(): ProjectItem = ProjectItem(randomString(), randomString(), randomString(),
            randomString(), randomString(), randomString(), randomString(), randomString(),
            randomString(), randomString())

    fun makeProjectList(count: Int): List<ProjectItem>{
        val projects = mutableListOf<ProjectItem>()
        repeat(count){
            projects.add(makeProject())
        }
        return projects
    }
}