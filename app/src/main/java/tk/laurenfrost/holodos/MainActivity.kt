package tk.laurenfrost.holodos

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import tk.laurenfrost.holodos.di.component.DaggerRestApiComponent
import tk.laurenfrost.holodos.repository.UserRepository
import tk.laurenfrost.holodos.service.UserService
import tk.laurenfrost.holodos.viewmodel.UserViewModel
import javax.inject.Inject

class MainActivity : AppCompatActivity() {

    @Inject
    lateinit var userViewModel: UserViewModel;


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        DaggerRestApiComponent.create().inject(this)
        userViewModel.user.observe(this, Observer {
            Log.i("HAHA", it.name + " " + it.website)
        })
    }
}
