package tk.laurenfrost.holodos

import android.app.Application
import tk.laurenfrost.holodos.di.component.ApplicationComponent
import tk.laurenfrost.holodos.di.component.DaggerApplicationComponent
import tk.laurenfrost.holodos.di.module.ContextModule
import tk.laurenfrost.holodos.di.module.DatabaseModule
import tk.laurenfrost.holodos.di.module.RestApiModule


class HolodosApplication : Application() {
    lateinit var applicationComponent: ApplicationComponent

    override fun onCreate() {
        applicationComponent = DaggerApplicationComponent
            .builder()
            .contextModule(ContextModule(this))
            .databaseModule(DatabaseModule(this))
            .restApiModule(RestApiModule())
            .build()

        super.onCreate()
        applicationComponent.inject(this)
    }

}
