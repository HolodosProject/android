package tk.laurenfrost.holodos.room.dao

import androidx.room.*
import tk.laurenfrost.holodos.domain.entity.Board

@Dao
interface BoardDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertBoard(board: Board)

    @Update
    fun updateBoard(board: Board)

    @Delete
    fun deleteBoard(board: Board)

    @Query("SELECT * FROM Board")
    fun getBoards(): List<Board>
}
