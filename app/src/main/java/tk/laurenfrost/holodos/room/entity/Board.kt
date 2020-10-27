package tk.laurenfrost.holodos.room.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Board(
    @PrimaryKey
    val id: String,
    val macAddress: String,
    val quantity: Int
)
