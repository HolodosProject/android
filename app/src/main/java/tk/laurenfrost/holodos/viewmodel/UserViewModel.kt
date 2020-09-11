package tk.laurenfrost.holodos.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import tk.laurenfrost.holodos.entity.User
import tk.laurenfrost.holodos.repository.UserRepository
import tk.laurenfrost.holodos.stub.UserData
import javax.inject.Inject

class UserViewModel @Inject constructor(
    private val userRepository: UserRepository
) : ViewModel() {
    val user: LiveData<User> = userRepository.getUser("1")
}
