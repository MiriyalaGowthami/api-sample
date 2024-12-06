import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import com.example.apisample.ApiClient
import com.example.apisample.LoginRepository
import com.example.apisample.LoginViewModel
import com.example.apisample.LoginViewModelFactory
import com.example.apisample.R

class LoginActivity : AppCompatActivity() {

    // Get the ViewModel using the viewModels delegate
    private val loginViewModel: LoginViewModel by viewModels {
        LoginViewModelFactory(LoginRepository(ApiClient.apiService))  // Replace with actual factory
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        // Initialize UI components
        val emailEditText = findViewById<EditText>(R.id.editTextEmail)
        val passwordEditText = findViewById<EditText>(R.id.editTextPassword)
        val loginButton = findViewById<Button>(R.id.buttonLogin)

        // Set up login button click listener
        loginButton.setOnClickListener {
            val email = emailEditText.text.toString().trim()
            val password = passwordEditText.text.toString().trim()

            if (email.isNotEmpty() && password.isNotEmpty()) {
                // Call the login function in ViewModel
                loginViewModel.login(email, password)
            } else {
                Toast.makeText(this, "Please enter both email and password", Toast.LENGTH_SHORT).show()
            }
        }

        // Observe the login status from ViewModel
        loginViewModel.loginStatus.observe(this, Observer { isSuccess ->
            if (isSuccess) {
                Toast.makeText(this, "Login Successful!", Toast.LENGTH_SHORT).show()
                // Proceed to next screen or activity
            } else {
                Toast.makeText(this, "Login Failed", Toast.LENGTH_SHORT).show()
            }
        })

        // Observe any error messages from ViewModel
        loginViewModel.errorMessage.observe(this, Observer { errorMsg ->
            errorMsg?.let {
                Toast.makeText(this, it, Toast.LENGTH_SHORT).show()
            }
        })
    }
}
