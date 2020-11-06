package tk.laurenfrost.holodos.service

import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.PATCH
import retrofit2.http.POST
import retrofit2.http.Path
import tk.laurenfrost.holodos.domain.dto.ItemDTO
import tk.laurenfrost.holodos.domain.entity.Item

interface ItemService {

    @GET("board/list/{boardId}")
    fun getBoardItems(@Path("boardId") boardId: String): Call<List<ItemDTO>>

    @POST("item")
    fun createItem(@Body item: ItemDTO): Call<ItemDTO>

    @PATCH("item/{itemId}")
    fun updateItem(@Path("itemId") itemId: String, @Body item: ItemDTO)
}
