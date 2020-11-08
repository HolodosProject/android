package tk.laurenfrost.holodos.view

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.gson.Gson
import kotlinx.android.synthetic.main.board_list_activity.*
import tk.laurenfrost.holodos.HolodosApplication
import tk.laurenfrost.holodos.R
import tk.laurenfrost.holodos.adapter.ItemsAdapter
import tk.laurenfrost.holodos.domain.entity.Board
import tk.laurenfrost.holodos.domain.entity.Item
import tk.laurenfrost.holodos.interfaces.OnItemClickListener
import tk.laurenfrost.holodos.interfaces.OnItemFormSubmitListener
import tk.laurenfrost.holodos.repository.ItemRepository
import tk.laurenfrost.holodos.room.AppDatabase
import tk.laurenfrost.holodos.view.fragment.ItemFormDialogFragment
import tk.laurenfrost.holodos.viewmodel.ItemsViewModel
import javax.inject.Inject

class BoardListActivity : AppCompatActivity(), OnItemClickListener, OnItemFormSubmitListener {


    @Inject
    lateinit var itemsViewModel: ItemsViewModel;

    @Inject
    lateinit var appDatabase: AppDatabase;

    @Inject
    lateinit var itemRepository: ItemRepository;

    lateinit var boardId: String
    val gson = Gson()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.board_list_activity)
        (application as HolodosApplication).applicationComponent.inject(this);

        boardId = intent.getStringExtra("boardId")!!


        refreshList.setOnClickListener {
            itemsViewModel.refreshItems()
        }

        addNewItem.setOnClickListener {
            val dialog = ItemFormDialogFragment()
            val args = Bundle()
            args.putString("boardId", boardId)
            dialog.arguments = args
            dialog.show(supportFragmentManager, "custom")
        }


        val adapter = ItemsAdapter(this)
        todoList.layoutManager = LinearLayoutManager(this)
        todoList.adapter = adapter

        itemsViewModel.getBoardItems(Board(boardId))
            .observe(this, Observer { list ->
                list?.let {
                    adapter.refreshItems(it)
                }
            })
    }

    override fun onItemEditClicked(item: Item) {
        val dialog = ItemFormDialogFragment()
        val args = Bundle()
        args.putString("item", gson.toJson(item))
        args.putString("boardId", boardId)
        dialog.arguments = args
        dialog.show(supportFragmentManager, "custom")
    }

    override fun onItemDeleteClicked(item: Item) {
        itemRepository.deleteItem(item)
    }

    override fun onItemFormSubmit(item: Item) {
        if (item.id == "") {
            itemRepository.createItem(item)
        } else {
            itemRepository.updateItem(item)
        }
    }

}
