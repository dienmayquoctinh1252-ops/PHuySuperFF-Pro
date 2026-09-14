package com.phuy.superff.data

import javax.inject.Inject

class LogRepository @Inject constructor(private val dao: LogDao) {
    fun flow() = dao.flow()
    suspend fun add(id: String, old: String, new: String, result: String) {
        dao.insert(LogEntity(tweakId = id, old = old, new = new, result = result))
    }
    suspend fun clear() = dao.clear()
}
