package tk.laurenfrost.holodos.di.component

import android.content.Context
import dagger.Component
import tk.laurenfrost.holodos.HolodosApplication
import tk.laurenfrost.holodos.view.MainActivity
import tk.laurenfrost.holodos.di.module.ContextModule
import tk.laurenfrost.holodos.di.module.DatabaseModule
import tk.laurenfrost.holodos.di.module.RestApiModule
import tk.laurenfrost.holodos.room.AppDatabase
import tk.laurenfrost.holodos.service.BoardService
import tk.laurenfrost.holodos.service.ItemService
import javax.inject.Singleton

@Singleton
@Component(modules = [RestApiModule::class, ContextModule::class, DatabaseModule::class])
interface ApplicationComponent {
    val itemService: ItemService
    val boardService: BoardService
    val context: Context
    val appDatabase: AppDatabase

    fun inject(activity: MainActivity)
    fun inject(application: HolodosApplication)
}

