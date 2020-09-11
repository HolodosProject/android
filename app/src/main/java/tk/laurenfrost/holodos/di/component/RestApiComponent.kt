package tk.laurenfrost.holodos.di.component

import dagger.Component
import tk.laurenfrost.holodos.MainActivity
import tk.laurenfrost.holodos.di.module.RestApiModule
import tk.laurenfrost.holodos.service.UserService
import javax.inject.Singleton

@Singleton
@Component(modules = [RestApiModule::class])
interface RestApiComponent {
    val userService: UserService

    fun inject(act: MainActivity)
}
