package com.vagnnermartins.baseproject.domain.usecase

import com.nhaarman.mockito_kotlin.whenever
import com.vagnnermartins.baseproject.domain.model.ProjectDomain
import com.vagnnermartins.baseproject.domain.repository.ProjectsRepository
import com.vagnnermartins.baseproject.domain.test.ProjectDomainFactory
import com.vagnnermartins.baseproject.domain.test.USER
import io.reactivex.Single
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.MockitoAnnotations

class ProjectsUseCaseTest{

    private lateinit var usecase: ProjectsUseCase
    @Mock lateinit var projectsRepository: ProjectsRepository

    @Before
    fun setup() {
        MockitoAnnotations.initMocks(this)
        usecase = ProjectsUseCase(projectsRepository)
    }

    @Test
    fun getProjects() {
        stubProjectsRepositoryGetProjects(
                Single.just(ProjectDomainFactory.makeProjectList(2)))
        val testObserver = usecase.get(true, USER).test()
        testObserver.assertComplete()
    }

    @Test
    fun getProjectsReturnsData() {
        val projects = ProjectDomainFactory.makeProjectList(2)
        stubProjectsRepositoryGetProjects(Single.just(projects))
        val testObserver = usecase.get(true, USER).test()
        testObserver.assertValue(projects)
    }

    private fun stubProjectsRepositoryGetProjects(observable: Single<List<ProjectDomain>>) {
        whenever(projectsRepository.getProjects(true, USER))
                .thenReturn(observable)
    }

}