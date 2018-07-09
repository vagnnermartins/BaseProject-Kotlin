package com.vagnnermartins.baseproject.data.mapper

interface EntityMapper<E, D> {

    fun mapFromEntity(entity: E): D

    fun mapFromEntity(entities: List<E>): List<D>

    fun mapFromDomain(domain: D): E

    fun mapFromDomain(domains: List<D>): List<E>

}