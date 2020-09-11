package tk.laurenfrost.holodos.service

import retrofit2.Call
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path
import tk.laurenfrost.holodos.entity.User

interface UserService {
    /**
     * @GET declares an HTTP GET request
     * @Path("user") annotation on the userId parameter marks it as a
     * replacement for the {user} placeholder in the @GET path
     */
    @GET("/users/{user}")
    fun getUser(@Path("user") userId: String): Call<User>


}
