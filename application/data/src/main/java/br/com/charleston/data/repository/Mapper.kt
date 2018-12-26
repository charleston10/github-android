package br.com.charleston.data.repository

interface Mapper<E, T> {
    fun transform(entity: E): T
}