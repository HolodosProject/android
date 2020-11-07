package tk.laurenfrost.holodos.interfaces

import tk.laurenfrost.holodos.domain.entity.Item

interface OnItemClickListener {
    fun onItemEditClicked(item: Item)
    fun onItemDeleteClicked(item: Item)
}
