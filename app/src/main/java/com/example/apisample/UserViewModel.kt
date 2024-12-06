package com.example.apisample


import User
import UserRepository
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

class UserViewModel(private val repository: UserRepository) : ViewModel() {

    // LiveData that the activity will observe
    private val _users = MutableLiveData<List<User>>()
    val users: LiveData<List<User>> get() = _users

    // Fetch users when the ViewModel is initialized
    init {
        getUsersFromRepository()
    }

    // Function to fetch users from the repository using coroutines
    private fun getUsersFromRepository() {
        viewModelScope.launch {
            try {
                // Fetch users from the repository and post value to LiveData
                val userList = repository.fetchUsers()  // Assuming this is a suspend function
                _users.postValue(userList.getOrDefault(emptyList()))
            } catch (e: Exception) {
                // Handle error
                e.printStackTrace()
            }
        }
    }
}
