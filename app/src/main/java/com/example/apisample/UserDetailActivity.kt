package com.example.apisample

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import android.widget.TextView

class UserDetailActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_user_detail)

        val textViewName: TextView = findViewById(R.id.textViewDetailName)
        val textViewEmail: TextView = findViewById(R.id.textViewDetailEmail)
        val textViewPhone: TextView = findViewById(R.id.textViewDetailPhone)
        val textViewWebsite: TextView = findViewById(R.id.textViewDetailWebsite)

        // Get data from intent
        val name = intent.getStringExtra("name")
        val email = intent.getStringExtra("email")
        val phone = intent.getStringExtra("phone")
        val website = intent.getStringExtra("website")

        // Set data to TextViews
        textViewName.text = name
        textViewEmail.text = email
        textViewPhone.text = phone
        textViewWebsite.text = website
    }
}
