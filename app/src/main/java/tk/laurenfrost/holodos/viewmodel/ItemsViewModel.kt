package tk.laurenfrost.holodos.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import tk.laurenfrost.holodos.repository.ItemRepository
import tk.laurenfrost.holodos.domain.entity.Item
import javax.inject.Inject

class ItemsViewModel @Inject constructor(
    itemRepository: ItemRepository
) : ViewModel() {
    val items: LiveData<List<Item>> = itemRepository.allItems
}
