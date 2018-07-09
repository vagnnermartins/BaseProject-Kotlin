package com.vagnnermartins.baseproject.di

import com.vagnnermartins.baseproject.projectDetail.ProjectDetailActivity
import com.vagnnermartins.baseproject.projects.ProjectsActivity
import com.vagnnermartins.baseproject.user.UserDialogFragment
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [AppModule::class,  ViewModelModule::class, NetworkModule::class, RepositoryModule::class])
interface Injector {
    fun inject(activity: ProjectsActivity)
    fun inject(activity: ProjectDetailActivity)
    fun inject(fragment: UserDialogFragment)
}
