package tk.laurenfrost.holodos.adapter

import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.shop_item.view.*
import tk.laurenfrost.holodos.R
import tk.laurenfrost.holodos.interfaces.OnItemClickListener
import tk.laurenfrost.holodos.domain.entity.Item
import java.util.ArrayList

class ItemsAdapter(private val itemClickListener: OnItemClickListener) :
    RecyclerView.Adapter<ItemsAdapter.ItemHolder>() {

    private var item: List<Item> = ArrayList()

    //создает ViewHolder и инициализирует views для списка
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemHolder {
        return ItemHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.shop_item, parent, false)
        )
    }

    //связывает views с содержимым
    override fun onBindViewHolder(viewHolder: ItemHolder, position: Int) {
        viewHolder.bind(item[position], itemClickListener)
    }

    override fun getItemCount() = item.size

    //передаем данные и оповещаем адаптер о необходимости обновления списка
    fun refreshItems(item: List<Item>) {
        this.item = item
        notifyDataSetChanged()
    }


    //внутренний класс ViewHolder описывает элементы представления списка и привязку их к RecyclerView
    class ItemHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(item: Item, clickListener: OnItemClickListener) = with(itemView) {
            itemName.text = item.name
            itemChecked.isChecked = item.quantity > 0;

            editItemButton.setOnClickListener {
                clickListener.onItemEditClicked(item)
            }

            deleteItemButton.setOnClickListener {
                clickListener.onItemDeleteClicked(item)
            }
        }
    }
}
