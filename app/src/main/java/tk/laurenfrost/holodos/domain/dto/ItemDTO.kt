package tk.laurenfrost.holodos.domain.dto

data class ItemDTO(
    val id: String,
    val name: String,
    val quantity: Int,
    val board: BoardDTO
)
