package com.vagnnermartins.baseproject.data.mapper

import com.vagnnermartins.baseproject.data.model.ProjectEntity
import com.vagnnermartins.baseproject.data.teste.ProjectDataFactory
import com.vagnnermartins.baseproject.domain.model.ProjectDomain
import org.junit.Before
import org.junit.Test

class ProjectMapperTest{

    val LIST_TEST = 3

    private lateinit var mapper: ProjectMapper

    @Before
    fun setUp() {
        mapper = ProjectMapper()
    }

    @Test
    fun testTransformEntityToDomain(){
        val domain: ProjectDomain = ProjectDataFactory.makeDomainProject()
        val entity: ProjectEntity = mapper.mapFromDomain(domain)
        testeItem(entity, domain)
    }

    @Test
    fun transformDomainToEntity(){
        val entity: ProjectEntity = ProjectDataFactory.makeProjectEntity()
        val domain: ProjectDomain = mapper.mapFromEntity(entity)
        testeItem(entity, domain)
    }

    @Test
    fun transformListEntityToListDomain(){
        val entities = ProjectDataFactory.makeProjectEntityList(LIST_TEST)
        val domains = mapper.mapFromEntity(entities)
        repeat(LIST_TEST){
            testeItem(entities[it], domains[it])
        }
    }

    @Test
    fun transformListDomainToListEntity(){
        val domains = ProjectDataFactory.makeDomainProjectList(LIST_TEST)
        val entities = mapper.mapFromDomain(domains)
        repeat(LIST_TEST){
            testeItem(entities[it], domains[it])
        }
    }

    private fun testeItem(entity: ProjectEntity, domain: ProjectDomain) {
        assert(entity.id == domain.id)
        assert(entity.dateCreated == domain.dateCreated)
        assert(entity.fullName == domain.fullName)
        assert(entity.name == domain.name)
        assert(entity.owner.avatar == domain.ownerAvatar)
        assert(entity.owner.name == domain.ownerName)
        assert(entity.starCount.toString() == domain.starCount)
        assert(entity.description == domain.description)
        assert(entity.lastUpdated == domain.lastUpdated)
        assert(entity.language == domain.language)
    }
}