package tk.laurenfrost.holodos

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_main.*
import tk.laurenfrost.holodos.adapter.TodoAdapter
import tk.laurenfrost.holodos.di.component.ApplicationComponent
import tk.laurenfrost.holodos.di.component.DaggerApplicationComponent
import tk.laurenfrost.holodos.di.module.ContextModule
import tk.laurenfrost.holodos.di.module.DatabaseModule
import tk.laurenfrost.holodos.di.module.RestApiModule
import tk.laurenfrost.holodos.room.AppDatabase
import tk.laurenfrost.holodos.viewmodel.UserViewModel
import javax.inject.Inject


class MainActivity : AppCompatActivity() {

    @Inject
    lateinit var userViewModel: UserViewModel;

    @Inject
    lateinit var appDatabase: AppDatabase;

    private lateinit var applicationComponent: ApplicationComponent


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        applicationComponent = DaggerApplicationComponent
            .builder()
            .contextModule(ContextModule(this))
            .databaseModule(DatabaseModule())
            .restApiModule(RestApiModule())
            .build()

        applicationComponent.inject(this)

        Log.i("HAHA Database:", appDatabase.toString())


        val adapter = TodoAdapter()
        todoList.layoutManager = LinearLayoutManager(this)
        todoList.adapter = adapter
        userViewModel.user.observe(this, Observer {
            Log.i("HAHA", it.name + " " + it.website)
        })

        userViewModel.todos.observe(this, Observer { list ->
            list?.let {
                adapter.refreshTodos(it)
            }
            list?.forEach { Log.i("HAHA", it.title) }
        })
    }
}
