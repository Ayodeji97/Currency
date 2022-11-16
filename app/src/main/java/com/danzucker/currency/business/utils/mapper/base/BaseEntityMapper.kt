package com.danzucker.currency.business.utils.mapper.base

interface BaseEntityMapper<T, D> {
    fun transformToEntity (type : T) : D
}