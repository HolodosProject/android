package tk.laurenfrost.holodos.room.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import tk.laurenfrost.holodos.domain.entity.Item

@Dao
interface ItemDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertItem(item: Item)

    @Update
    fun updateItem(item: Item)

    @Delete
    fun deleteItem(item: Item)

    @Query("SELECT * FROM Item WHERE boardId == :boardId")
    fun getItemsByBoardId(boardId: String): LiveData<List<Item>>

    @Query("SELECT * FROM Item")
    fun getAllItems(): LiveData<List<Item>>
}
