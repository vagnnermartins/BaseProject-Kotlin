package com.vagnnermartins.baseproject.di

import com.vagnnermartins.baseproject.data.repository.ProjectsDataRepository
import com.vagnnermartins.baseproject.data.repository.UserDataRepository
import com.vagnnermartins.baseproject.domain.repository.ProjectsRepository
import com.vagnnermartins.baseproject.domain.repository.UserRepository
import dagger.Binds
import dagger.Module

@Module
abstract class RepositoryModule {

    @Binds
    abstract fun bindProjectsRepository(repository: ProjectsDataRepository): ProjectsRepository

    @Binds
    abstract fun bindUserRepository(repository: UserDataRepository): UserRepository
}