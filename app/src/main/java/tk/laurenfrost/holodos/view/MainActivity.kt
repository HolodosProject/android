package tk.laurenfrost.holodos.view

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_main.*
import tk.laurenfrost.holodos.HolodosApplication
import tk.laurenfrost.holodos.R
import tk.laurenfrost.holodos.interfaces.OnItemClickListener
import tk.laurenfrost.holodos.adapter.ItemsAdapter
import tk.laurenfrost.holodos.domain.entity.Board
import tk.laurenfrost.holodos.domain.entity.Item
import tk.laurenfrost.holodos.repository.ItemRepository
import tk.laurenfrost.holodos.room.AppDatabase
import tk.laurenfrost.holodos.viewmodel.ItemsViewModel
import javax.inject.Inject


class MainActivity : AppCompatActivity(), OnItemClickListener {

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

        get.setOnClickListener {
            itemsViewModel.refreshItems()
        }

        create.setOnClickListener {
            itemRepository.createItem(Item("", "foo", 1, "8561b959-0efb-4ba7-a7da-f6a6eade8610"))
        }


        val adapter = ItemsAdapter(this)
        todoList.layoutManager = LinearLayoutManager(this)
        todoList.adapter = adapter

        itemsViewModel.getBoardItems(Board("8561b959-0efb-4ba7-a7da-f6a6eade8610"))
            .observe(this, Observer { list ->
                list?.let {
                    adapter.refreshItems(it)
                }
            })

//        itemsViewModel.changeBoard(Board("8561b959-0efb-4ba7-a7da-f6a6eade8610"))
    }

    override fun onItemEditClicked(item: Item) {
        TODO("Not yet implemented")
    }

    override fun onItemDeleteClicked(item: Item) {
        TODO("Not yet implemented")
    }
}
