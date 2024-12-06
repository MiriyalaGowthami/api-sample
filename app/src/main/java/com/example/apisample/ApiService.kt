import retrofit2.Response
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
import retrofit2.http.POST

interface ApiService {

    // Login endpoint
    @FormUrlEncoded
    @POST("login")  // Replace with your actual login API endpoint
    suspend fun login(
        @Field("email") email: String,
        @Field("password") password: String
    ): Response<Void>  // Adjust the response type as per your API response


    // Get Users endpoint (this was initially provided)
    @GET("users")  // Replace with your actual users endpoint
    suspend fun getUsers(): Response<List<User>>  // Adjust response type according to your API response
}
