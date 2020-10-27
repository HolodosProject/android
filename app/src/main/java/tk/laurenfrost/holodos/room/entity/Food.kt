package tk.laurenfrost.holodos.room.entity

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey

@Entity(
    foreignKeys = [ForeignKey(
        entity = Board::class,
        parentColumns = ["id"],
        childColumns = ["boardId"]
    )]
)
data class Food(
    @PrimaryKey
    val id: String,
    val name: String,
    val quantity: Int,
    val boardId: String
)
