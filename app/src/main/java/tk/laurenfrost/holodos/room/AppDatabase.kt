package tk.laurenfrost.holodos.room

import androidx.room.Database
import androidx.room.RoomDatabase
import tk.laurenfrost.holodos.room.dao.BoardDao
import tk.laurenfrost.holodos.room.dao.ItemDao
import tk.laurenfrost.holodos.domain.entity.Board
import tk.laurenfrost.holodos.domain.entity.Item

@Database(entities = [Item::class, Board::class], version = 4)
abstract class AppDatabase : RoomDatabase() {
    abstract fun itemDao(): ItemDao
    abstract fun boardDao(): BoardDao

}
