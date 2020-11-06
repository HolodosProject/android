package tk.laurenfrost.holodos.repository

import android.util.Log
import androidx.lifecycle.LiveData
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import tk.laurenfrost.holodos.domain.dto.BoardDTO
import tk.laurenfrost.holodos.domain.dto.ItemDTO
import tk.laurenfrost.holodos.room.Executor
import tk.laurenfrost.holodos.room.dao.ItemDao
import tk.laurenfrost.holodos.domain.entity.Item
import tk.laurenfrost.holodos.service.ItemService
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class ItemRepository @Inject constructor(
    private val itemDao: ItemDao,
    private val itemService: ItemService
) {
    val allItems: LiveData<List<Item>> = itemDao.getAllItems()

    fun createItem(item: Item) {
        itemService.createItem(ItemDTO(item.id, item.name, item.quantity, BoardDTO(item.boardId)))
            .enqueue(object : Callback<ItemDTO> {
                override fun onResponse(call: Call<ItemDTO>, response: Response<ItemDTO>) {
                    Log.i("BACK", response.body().toString())
                }

                // Случай ошибки опущен для краткости.
                override fun onFailure(call: Call<ItemDTO>, t: Throwable) {
                    t.printStackTrace()
                }
            })
        Executor.IOThread(Runnable { itemDao.insertItem(item) })
    }

}
