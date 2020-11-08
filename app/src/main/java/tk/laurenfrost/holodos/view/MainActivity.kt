package tk.laurenfrost.holodos.view

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.google.gson.Gson
import kotlinx.android.synthetic.main.activity_main.*
import tk.laurenfrost.holodos.HolodosApplication
import tk.laurenfrost.holodos.R
import tk.laurenfrost.holodos.repository.ItemRepository
import tk.laurenfrost.holodos.room.AppDatabase
import tk.laurenfrost.holodos.viewmodel.ItemsViewModel
import javax.inject.Inject


class MainActivity : AppCompatActivity() {

    @Inject
    lateinit var itemsViewModel: ItemsViewModel;

    @Inject
    lateinit var appDatabase: AppDatabase;

    @Inject
    lateinit var itemRepository: ItemRepository;

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        (application as HolodosApplication).applicationComponent.inject(this);

        button.setOnClickListener {
            val intent = Intent(this, BoardListActivity::class.java)
            intent.putExtra("boardId", "8561b959-0efb-4ba7-a7da-f6a6eade8610")
            // start your next activity
            startActivity(intent)
        }
    }
}
