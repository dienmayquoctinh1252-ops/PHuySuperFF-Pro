package com.phuy.superff.data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class LogEntity(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val ts: Long = System.currentTimeMillis(),
    val tweakId: String,
    val old: String,
    val new: String,
    val result: String
)
