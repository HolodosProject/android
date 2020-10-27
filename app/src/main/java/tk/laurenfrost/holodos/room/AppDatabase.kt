package tk.laurenfrost.holodos.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import tk.laurenfrost.holodos.room.dao.BoardDao
import tk.laurenfrost.holodos.room.dao.FoodDao
import tk.laurenfrost.holodos.room.entity.Board
import tk.laurenfrost.holodos.room.entity.Food

@Database(entities = [Food::class, Board::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun foodDao(): FoodDao
    abstract fun boardDao(): BoardDao

}
