import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class UserRepository(private val apiService: ApiService) {

    // Function to fetch users
    suspend fun fetchUsers(): Result<List<User>> {
        return try {
            // Call the API inside a coroutine
            val response = withContext(Dispatchers.IO) {
                apiService.getUsers()  // Suspend function call
            }

            if (response.isSuccessful) {
                // Return the list of users if the response is successful
                Result.success(response.body() ?: emptyList())
            } else {
                // Return failure if the response is not successful
                Result.failure(Exception("Error fetching users: ${response.errorBody()?.string()}"))
            }
        } catch (e: Exception) {
            // Return failure in case of an exception
            Result.failure(e)
        }
    }

    fun getUsers() {

    }
}
