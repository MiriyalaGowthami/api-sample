package com.example.apisample

import ApiService
import UserRepository
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MainActivity : AppCompatActivity() {

    private lateinit var userViewModel: UserViewModel
    private lateinit var recyclerView: RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Initialize RecyclerView
        recyclerView = findViewById(R.id.recyclerView)
        recyclerView.layoutManager = LinearLayoutManager(this)
5
        // Initialize ViewModel
        val apiService = Retrofit.Builder()
            .baseUrl("https://jsonplaceholder.typicode.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(ApiService::class.java)

        val repository = UserRepository(apiService)
        userViewModel = ViewModelProvider(this, UserViewModelFactory(repository)).get(UserViewModel::class.java)

        // Observe LiveData from ViewModel
        userViewModel.users.observe(this, Observer { userList ->
            if (userList != null) {
                recyclerView.adapter = UserAdapter(this, userList)
            } else {
                Log.e("ERROR", "Failed to load users")
            }
        })
    }
}
