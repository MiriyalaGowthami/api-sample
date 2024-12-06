package com.example.apisample
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

class LoginViewModel(private val repository: LoginRepository) : ViewModel() {

    // LiveData or State to expose login status
    val loginStatus = MutableLiveData<Boolean>()
    val errorMessage = MutableLiveData<String>()

    // Function to perform login
    fun login(email: String, password: String) {
        // Launch a coroutine in ViewModel
        viewModelScope.launch {
            try {
                val result = repository.login(email, password)
                result.onSuccess {
                    loginStatus.postValue(it)  // Update LiveData with login status
                }.onFailure { exception ->
                    errorMessage.postValue(exception.message)
                }
            } catch (e: Exception) {
                errorMessage.postValue("Error: ${e.message}")
            }
        }
    }
}
