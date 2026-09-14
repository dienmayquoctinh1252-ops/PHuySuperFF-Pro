package com.phuy.superff.data

import android.content.Context
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.map
import org.json.JSONObject

val Context.ds by preferencesDataStore("profiles")

class ProfileRepo(val ctx: Context) {
    suspend fun save(map: Map<String, String>) {
        ctx.ds.edit { p ->
            map.forEach { (k, v) -> p[stringPreferencesKey(k)] = v }
        }
    }

    suspend fun load(): Map<String, String> {
        val data = ctx.ds.data.map { it.asMap() }.first()
        return data.mapKeys {
            (it.key as androidx.datastore.preferences.core.Preferences.Key<String>).name
        }.mapValues { it.value.toString() }
    }

    suspend fun exportJson(): String = JSONObject(load()).toString()

    suspend fun importJson(s: String) {
        val o = JSONObject(s)
        val m = mutableMapOf<String, String>()
        o.keys().forEach { m[it] = o.getString(it) }
        save(m)
    }
}
