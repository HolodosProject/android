package tk.laurenfrost.holodos.repository

import androidx.lifecycle.LiveData
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import tk.laurenfrost.holodos.domain.dto.BoardDTO
import tk.laurenfrost.holodos.domain.dto.ItemDTO
import tk.laurenfrost.holodos.domain.entity.Board
import tk.laurenfrost.holodos.domain.entity.Item
import tk.laurenfrost.holodos.room.Executor
import tk.laurenfrost.holodos.room.dao.ItemDao
import tk.laurenfrost.holodos.service.ItemService
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class ItemRepository @Inject constructor(
    private val itemDao: ItemDao,
    private val itemService: ItemService
) {
    fun getBoardItems(board: Board): LiveData<List<Item>> {
        refreshBoardItems(board)
        return itemDao.getItemsByBoardId(board.id)
    }

    fun refreshBoardItems(board: Board) {
        itemService.getBoardItems(board.id).enqueue(object : Callback<List<ItemDTO>> {
            override fun onResponse(call: Call<List<ItemDTO>>, response: Response<List<ItemDTO>>) {
                val items: List<Item> = response.body()?.map { itemDTO ->
                    Item(
                        itemDTO.id,
                        itemDTO.name,
                        itemDTO.quantity,
                        itemDTO.board.id
                    )
                } ?: ArrayList()

                Executor.IOThread(Runnable {
                    itemDao.deleteItemsByBoardId(board.id)
                    itemDao.insertItems(items)
                })
            }

            override fun onFailure(call: Call<List<ItemDTO>>, t: Throwable) {
                t.printStackTrace();
            }
        })
    }

    fun createItem(item: Item) {
        itemService.createItem(ItemDTO(item.id, item.name, item.quantity, BoardDTO(item.boardId)))
            .enqueue(object : Callback<ItemDTO> {
                override fun onResponse(call: Call<ItemDTO>, response: Response<ItemDTO>) {
                    val body: ItemDTO = response.body()!!
                    val item = Item(body.id, body.name, body.quantity, body.board.id)
                    Executor.IOThread(Runnable { itemDao.insertItem(item) })
                }

                // Мы не ошибаемся
                override fun onFailure(call: Call<ItemDTO>, t: Throwable) {
                    t.printStackTrace()
                }
            })
    }

    fun updateItem(item: Item) {
        itemService.updateItem(
            item.id,
            ItemDTO(item.id, item.name, item.quantity, BoardDTO(item.boardId))
        ).enqueue(object : Callback<ItemDTO> {
            override fun onResponse(call: Call<ItemDTO>, response: Response<ItemDTO>) {
                val body: ItemDTO = response.body()!!
                val item = Item(body.id, body.name, body.quantity, body.board.id)
                Executor.IOThread(Runnable { itemDao.updateItem(item) })
            }

            // Мы не ошибаемся
            override fun onFailure(call: Call<ItemDTO>, t: Throwable) {
                t.printStackTrace()
            }
        })
    }

    fun deleteItem(item: Item) {
        itemService.deleteItem(item.id).enqueue(object : Callback<Void> {
            override fun onResponse(call: Call<Void>, response: Response<Void>) {
                Executor.IOThread(Runnable { itemDao.deleteItem(item) })
            }

            override fun onFailure(call: Call<Void>, t: Throwable) {
                t.printStackTrace()
            }

        })
    }

}
