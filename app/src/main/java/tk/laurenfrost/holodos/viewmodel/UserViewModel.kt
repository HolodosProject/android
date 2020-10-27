package tk.laurenfrost.holodos.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import tk.laurenfrost.holodos.room.entity.Todo
import tk.laurenfrost.holodos.room.entity.User
import tk.laurenfrost.holodos.repository.UserRepository
import javax.inject.Inject

class UserViewModel @Inject constructor(
    private val userRepository: UserRepository
) : ViewModel() {
    val user: LiveData<User> = userRepository.getUser("1")
    val todos: LiveData<List<Todo>> = userRepository.getTodos()
}
