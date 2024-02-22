package sence.kate.practica3.MVVM

import android.util.Log
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch
import sence.kate.practica3.DataClasses.Friend
import sence.kate.practica3.DataClasses.User
import sence.kate.practica3.DataClasses.UserAppType
import sence.kate.practica3.R
import sence.kate.practica3.RandomI.RandomImage
import sence.kate.practica3.prefdatastore.DataStoreManager

class DataStoreViewModel : ViewModel() {

    private val friends = ArrayList<Friend>()

    private val coroutineScope = CoroutineScope(Dispatchers.IO)

    private val randomImage = RandomImage().randomImageResId()

    var password = mutableStateOf("")
    var name = mutableStateOf("")
    var login = mutableStateOf("")

    fun listUsers(dataStoreManager: DataStoreManager, image: Int): List<Friend> {
        return getUsersByType(userAppType = UserAppType.PAID, dataStoreManager = dataStoreManager)
    }

    private fun getUsersByType(
        userAppType: UserAppType,
        dataStoreManager: DataStoreManager,
    ): List<Friend> {
        when (userAppType) {
            UserAppType.FREE -> {
                friends.add(Friend("Лилит", R.drawable.cat2))
                friends.add(Friend("Себастьян", R.drawable.cat3))
                friends.add(Friend("Перонетта ", R.drawable.cat4))
                friends.add(Friend("Мария", R.drawable.cat5))
            }

            UserAppType.PAID -> {
                coroutineScope.launch {
                    val userName = dataStoreManager.getUserName().first()
                    val user = Friend(userName, randomImage)
                    friends.add(user)
                }
            }
        }
        return friends
    }

    suspend fun saveInDataStoreManager(dataStoreManager: DataStoreManager) {
        dataStoreManager.saveData(
            User(
                name = name.value,
                login = login.value,
                password = password.value,
            )
        )
    }

    fun saveUserNameToDataStore(name: String, dataStoreManager: DataStoreManager) {
        coroutineScope.launch {
            dataStoreManager.saveUserName(name)

        }
    }

    fun observeUserNameFromDataStore(dataStoreManager: DataStoreManager) {
        coroutineScope.launch {
            dataStoreManager.getUserName().collect { userName ->
                // Здесь вы можете выполнить действия с полученным именем
                Log.d("MyTag", "Имя пользователя в DataStore: $userName")
            }
        }
    }

    fun observeLoginFromDataStore(dataStoreManager: DataStoreManager) {
        coroutineScope.launch {
            dataStoreManager.getLogin().collect { login ->
                // Здесь вы можете выполнить действия с полученным именем
                Log.d("MyTag", "Логин пользователя в DataStore: $login")
            }
        }
    }

    fun observePasswordFromDataStore(dataStoreManager: DataStoreManager) {
        coroutineScope.launch {
            dataStoreManager.getPassword().collect { password ->
                // Здесь вы можете выполнить действия с полученным именем
                Log.d("MyTag", "Пароль пользователя в DataStore: $password")
            }
        }
    }

}

