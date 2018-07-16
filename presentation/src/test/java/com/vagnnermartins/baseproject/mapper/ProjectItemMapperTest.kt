package com.vagnnermartins.baseproject.mapper

import com.vagnnermartins.baseproject.data.test.ProjectDataFactory
import com.vagnnermartins.baseproject.domain.model.ProjectDomain
import com.vagnnermartins.baseproject.model.ProjectItem
import com.vagnnermartins.baseproject.test.ProjectsPresentationFactory
import org.junit.Before
import org.junit.Test

class ProjectItemMapperTest{

    val LIST_TEST = 3

    private lateinit var mapper: ProjectItemMapper

    @Before
    fun setUp() {
        mapper = ProjectItemMapper()
    }

    @Test
    fun testTransformDomainToItem(){
        val domain: ProjectDomain = ProjectDataFactory.makeDomainProject()
        val item: ProjectItem = mapper.mapToPresentation(domain)
        testItem(domain, item)
    }

    @Test
    fun testTranformItemToDomain(){
        val item: ProjectItem = ProjectsPresentationFactory.makeProject()
        val domain: ProjectDomain = mapper.mapToDomain(item)
        testItem(domain, item)
    }

    @Test
    fun testTransformDomainToItems(){
        val domains: List<ProjectDomain> = ProjectDataFactory.makeDomainProjectList(LIST_TEST)
        val items: List<ProjectItem> = mapper.mapToPresentation(domains)
        repeat(LIST_TEST){
            testItem(domains[it], items[it])
        }
    }

    @Test
    fun testTransformItemsToDomains(){
        val items: List<ProjectItem> = ProjectsPresentationFactory.makeProjectList(LIST_TEST)
        val domains: List<ProjectDomain> = mapper.mapToDomain(items)
        repeat(LIST_TEST){
            testItem(domains[it], items[it])
        }
    }

    private fun testItem(domain: ProjectDomain, item: ProjectItem) {
        assert(domain.id == item.id)
        assert(domain.dateCreated == item.dateCreated)
        assert(domain.fullName == item.fullName)
        assert(domain.name == item.name)
        assert(domain.ownerAvatar == item.ownerAvatar)
        assert(domain.ownerName == item.ownerName)
        assert(domain.starCount == item.starCount.toString())
        assert(domain.description == item.description)
        assert(domain.lastUpdated == item.lastUpdated)
        assert(domain.language == item.language)
    }
}