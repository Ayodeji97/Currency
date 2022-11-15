
package com.danzucker.currency.business.utils.mapper.base

interface BaseDtoMapper<T, E> {
    fun transformToDomain (type : T) : E
}