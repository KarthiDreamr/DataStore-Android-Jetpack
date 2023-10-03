package com.example.datastore

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class DetailStorage(private val context: Context) {

    companion object {
        private val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "settings")
        val NAME = stringPreferencesKey("user_name")
        val AGE = stringPreferencesKey("user_age")
    }

    fun getInfo(): Flow<StudentDetail> {
        return context.dataStore.data
            .map {
                StudentDetail(
                    name = it[NAME] ?: "",
                    age = it[AGE] ?: "",
                )
            }
    }

    suspend fun saveInfo(name: String, age: String) {
        context.dataStore.edit { preferences ->
            preferences[NAME] = name
            preferences[AGE] = age
        }
    }

}

data class StudentDetail(
    val name: String = "",
    val age: String = "",
)