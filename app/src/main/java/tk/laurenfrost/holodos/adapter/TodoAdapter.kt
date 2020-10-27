package tk.laurenfrost.holodos.adapter

import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.shop_item.view.*
import tk.laurenfrost.holodos.R
import tk.laurenfrost.holodos.room.entity.Todo
import java.util.ArrayList

class TodoAdapter : RecyclerView.Adapter<TodoAdapter.TodoHolder>() {

    private var todos: List<Todo> = ArrayList()

    //создает ViewHolder и инициализирует views для списка
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TodoHolder {
        return TodoHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.shop_item, parent, false)
        )
    }

    //связывает views с содержимым
    override fun onBindViewHolder(viewHolder: TodoHolder, position: Int) {
        viewHolder.bind(todos[position])
    }

    override fun getItemCount() = todos.size

    //передаем данные и оповещаем адаптер о необходимости обновления списка
    fun refreshTodos(todos: List<Todo>) {
        this.todos = todos
        notifyDataSetChanged()
    }


    //внутренний класс ViewHolder описывает элементы представления списка и привязку их к RecyclerView
    class TodoHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(todo: Todo) = with(itemView) {
            todoText.text = todo.title
            todoChecked.isChecked = todo.completed
        }
    }
}
