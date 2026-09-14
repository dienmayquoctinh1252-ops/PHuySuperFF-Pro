package com.phuy.superff.data

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface LogDao {
    @Insert
    suspend fun insert(e: LogEntity)

    @Query("SELECT * FROM LogEntity ORDER BY ts DESC")
    fun flow(): Flow<List<LogEntity>>

    @Query("DELETE FROM LogEntity")
    suspend fun clear()
}
