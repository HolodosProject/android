package tk.laurenfrost.holodos.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import tk.laurenfrost.holodos.domain.entity.Board
import tk.laurenfrost.holodos.repository.ItemRepository
import tk.laurenfrost.holodos.domain.entity.Item
import tk.laurenfrost.holodos.room.Executor
import javax.inject.Inject

class ItemsViewModel @Inject constructor(
    private val itemRepository: ItemRepository
) : ViewModel() {

    lateinit var currentBoard: Board
    lateinit var items: LiveData<List<Item>>

    fun getBoardItems(board: Board): LiveData<List<Item>> {
        currentBoard = board
        items = itemRepository.getBoardItems(board)
        return items
    }

    fun refreshItems() {
        itemRepository.refreshBoardItems(currentBoard)
    }
}
