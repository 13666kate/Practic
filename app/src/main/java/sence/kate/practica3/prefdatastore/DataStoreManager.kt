package sence.kate.practica3.prefdatastore

import android.content.Context
import android.util.Log

import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import sence.kate.practica3.DataClasses.User
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.preferencesDataStore

private val TABLE_DATA_STORE_KEY = stringPreferencesKey("user")


class DataStoreManager(val context: Context) {
    private val Context.dataStore: DataStore<Preferences> by preferencesDataStore("Data")


    companion object {
        val Name = stringPreferencesKey("Name")
        val Login = stringPreferencesKey("Login")
        val Password = stringPreferencesKey("Password")
    }

    suspend fun saveUserName(name: String) {
        context.dataStore.edit { preferences ->
            preferences[Name] = name
        }
    }

    fun getUserName(): Flow<String> {
        return context.dataStore.data.map { pref ->
            pref[Name] ?: ""
        }
    }

    suspend fun clearData() {
        context.dataStore.edit { preferences ->
            preferences.clear() // Очищаем все данные в хранилище
        }
    }

    suspend fun saveLogin(login: String) {
        context.dataStore.edit { preferences ->
            preferences[Login] = login
        }
    }

    fun getLogin(): Flow<String> {
        return context.dataStore.data.map { pref ->
            pref[Login] ?: ""
        }
    }

    suspend fun savePassword(login: String) {
        context.dataStore.edit { preferences ->
            preferences[Login] = login
        }
    }

    fun getPassword(): Flow<String> {
        return context.dataStore.data.map { pref ->
            pref[Password] ?: ""
        }
    }

    fun getSettings(key: Preferences.Key<String>): Flow<String?> {
        return context.dataStore.data.map { pref ->
            pref[key]
        }
    }

    suspend fun saveData(settings: User) {
        context.dataStore.edit { pref ->
            pref[Name] = settings.name
            pref[Login] = settings.login
            pref[Password] = settings.password
        }
        Log.d("MyTag", "Data loaded: $settings")
    }

    fun getSettings() = context.dataStore.data.map { pref ->

        return@map User(
            pref[Name] ?: "",
            pref[Login] ?: "",
            pref[Password] ?: "",
        )

    }

    val Name = stringPreferencesKey("Name")
    fun getAllValuesForName(): Flow<String?> {
        return context.dataStore.data.map { preferences ->
            preferences[Name]
        }
    }
}