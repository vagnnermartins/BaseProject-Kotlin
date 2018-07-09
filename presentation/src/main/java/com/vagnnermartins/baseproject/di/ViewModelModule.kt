package com.vagnnermartins.baseproject.di

import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProvider
import com.vagnnermartins.baseproject.ViewModelFactory
import com.vagnnermartins.baseproject.ViewModelKey
import com.vagnnermartins.baseproject.projectDetail.ProjectDetailViewModel
import com.vagnnermartins.baseproject.projects.ProjectsViewModel
import com.vagnnermartins.baseproject.user.UserViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class ViewModelModule {

    @Binds
    internal abstract fun bindViewModelFactory(factory: ViewModelFactory): ViewModelProvider.Factory

    @Binds
    @IntoMap
    @ViewModelKey(ProjectsViewModel::class)
    internal abstract fun projectViewModel(viewModel: ProjectsViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(UserViewModel::class)
    internal abstract fun userViewModel(viewModel: UserViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(ProjectDetailViewModel::class)
    internal abstract fun projectDetailViewModel(viewModel: ProjectDetailViewModel): ViewModel

}
