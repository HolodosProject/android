package tk.laurenfrost.holodos.domain.entity

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey

//foreignKeys = [ForeignKey(
//entity = Board::class,
//parentColumns = ["id"],
//childColumns = ["boardId"]
//)]
@Entity
data class Item(
    @PrimaryKey
    val id: String,
    val name: String,
    val quantity: Int,
    val boardId: String
)
