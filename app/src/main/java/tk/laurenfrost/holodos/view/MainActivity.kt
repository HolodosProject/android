package tk.laurenfrost.holodos.view

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_main.*
import tk.laurenfrost.holodos.HolodosApplication
import tk.laurenfrost.holodos.R
import tk.laurenfrost.holodos.adapter.FoodAdapter
import tk.laurenfrost.holodos.domain.entity.Item
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

        Log.i("BACK", "Pre create")
        itemRepository.createItem(Item("", "foo", 1, "8561b959-0efb-4ba7-a7da-f6a6eade8610"))
        Log.i("BACK", "Post create")

//        Log.i("HAHA Database:", appDatabase.toString())
//        val foodDao: FoodDao = appDatabase.foodDao();
//        val food = Food(UUID.randomUUID().toString(), "Soup", 3, UUID.randomUUID().toString())
//        Log.i("FOOD", food.toString())
//
//        Executor.IOThread(Runnable { foodDao.insertFood(food) })

        val adapter = FoodAdapter()
        todoList.layoutManager = LinearLayoutManager(this)
        todoList.adapter = adapter
    }
}
