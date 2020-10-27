package tk.laurenfrost.holodos.room.dao

import androidx.room.*
import tk.laurenfrost.holodos.room.entity.Food

@Dao
interface FoodDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertFood(food: Food)

    @Update
    fun updateFood(food: Food)

    @Delete
    fun deleteFood(food: Food)

    @Query("SELECT * FROM Food WHERE boardId == :boardId")
    fun getFoodByBoardId(boardId: String): List<Food>

    @Query("SELECT * FROM Food")
    fun getFood(): List<Food>
}
