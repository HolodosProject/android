package tk.laurenfrost.holodos.room.entity

data class Todo(
    var userId: Int,
    var id: Int,
    var title: String,
    var completed: Boolean

)
