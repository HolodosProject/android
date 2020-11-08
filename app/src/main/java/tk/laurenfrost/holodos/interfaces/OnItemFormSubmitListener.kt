package tk.laurenfrost.holodos.interfaces

import tk.laurenfrost.holodos.domain.entity.Item

interface OnItemFormSubmitListener {
    fun onItemFormSubmit(item: Item)
}
