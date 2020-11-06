package tk.laurenfrost.holodos.adapter

import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.shop_item.view.*
import tk.laurenfrost.holodos.R
import tk.laurenfrost.holodos.domain.entity.Item
import java.util.ArrayList

class FoodAdapter : RecyclerView.Adapter<FoodAdapter.TodoHolder>() {

    private var item: List<Item> = ArrayList()

    //создает ViewHolder и инициализирует views для списка
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TodoHolder {
        return TodoHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.shop_item, parent, false)
        )
    }

    //связывает views с содержимым
    override fun onBindViewHolder(viewHolder: TodoHolder, position: Int) {
        viewHolder.bind(item[position])
    }

    override fun getItemCount() = item.size

    //передаем данные и оповещаем адаптер о необходимости обновления списка
    fun refreshTodos(item: List<Item>) {
        this.item = item
        notifyDataSetChanged()
    }


    //внутренний класс ViewHolder описывает элементы представления списка и привязку их к RecyclerView
    class TodoHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(item: Item) = with(itemView) {
            todoText.text = item.name
            todoChecked.isChecked = item.quantity > 0;
        }
    }
}
