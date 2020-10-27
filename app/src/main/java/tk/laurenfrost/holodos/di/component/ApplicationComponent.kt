package tk.laurenfrost.holodos.di.component

import android.content.Context
import dagger.Component
import tk.laurenfrost.holodos.MainActivity
import tk.laurenfrost.holodos.di.module.ContextModule
import tk.laurenfrost.holodos.di.module.DatabaseModule
import tk.laurenfrost.holodos.di.module.RestApiModule
import tk.laurenfrost.holodos.room.AppDatabase
import tk.laurenfrost.holodos.service.UserService
import javax.inject.Singleton

@Singleton
@Component(modules = [RestApiModule::class, ContextModule::class, DatabaseModule::class])
interface ApplicationComponent {
    val userService: UserService
    val context: Context
    val appDatabase: AppDatabase

    fun inject(act: MainActivity)
}

