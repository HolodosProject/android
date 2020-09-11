package tk.laurenfrost.holodos.di.module

import android.content.Context
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class ContextModule(var context: Context) {
    @Singleton
    @Provides
    fun context(): Context {
        return context.applicationContext
    }

}
