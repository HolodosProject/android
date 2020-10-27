package tk.laurenfrost.holodos.repository

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import tk.laurenfrost.holodos.room.entity.Todo
import tk.laurenfrost.holodos.room.entity.User
import tk.laurenfrost.holodos.service.UserService
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class UserRepository @Inject constructor(
    private val userService: UserService
) {

    // ...
    fun getUser(userId: String): LiveData<User> {
        // Это не оптимальная реализация. Мы исправим это позже.
        val data = MutableLiveData<User>()
        userService.getUser(userId).enqueue(object : Callback<User> {
            override fun onResponse(call: Call<User>, response: Response<User>) {
                data.value = response.body()
            }

            // Случай ошибки опущен для краткости.
            override fun onFailure(call: Call<User>, t: Throwable) {
                t.printStackTrace()
            }
        })
        return data
    }

    fun getTodos(): MutableLiveData<List<Todo>> {
        val data = MutableLiveData<List<Todo>>()
        userService.getTodos().enqueue(object : Callback<List<Todo>> {
            override fun onResponse(call: Call<List<Todo>>, response: Response<List<Todo>>) {
                Log.i("HAHA", response.toString())
                data.value = response.body()
            }

            // Случай ошибки опущен для краткости.
            override fun onFailure(call: Call<List<Todo>>, t: Throwable) {
                t.printStackTrace()
            }
        })
        return data
    }
}
