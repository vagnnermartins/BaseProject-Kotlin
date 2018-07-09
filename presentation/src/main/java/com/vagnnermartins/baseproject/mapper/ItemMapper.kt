package com.vagnnermartins.baseproject.mapper

interface ItemMapper<E, D> {

    fun mapToPresentation(domain: E): D

    fun mapToPresentation(domains: List<E>): List<D>

    fun mapToDomain(presentation: D): E

    fun mapToDomain(presentations: List<D>): List<E>

}