package tk.laurenfrost.holodos.di.module

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import tk.laurenfrost.holodos.service.BoardService
import tk.laurenfrost.holodos.service.ItemService
import javax.inject.Singleton


@Module
class RestApiModule {
    @Singleton
    @Provides
    fun itemService(retrofit: Retrofit): ItemService {
        return retrofit.create<ItemService>(ItemService::class.java)
    }

    @Singleton
    @Provides
    fun boardService(retrofit: Retrofit): BoardService {
        return retrofit.create<BoardService>(BoardService::class.java)
    }

    @Singleton
    @Provides
    fun retrofit(
        gsonConverterFactory: GsonConverterFactory
    ): Retrofit {
        return Retrofit.Builder()
            .baseUrl("http://192.168.0.111:8081/api/v1/")
            .addConverterFactory(gsonConverterFactory)
            .build()
    }

    @Singleton
    @Provides
    fun gson(): Gson {
        val gsonBuilder = GsonBuilder()
        return gsonBuilder.create()
    }

    @Singleton
    @Provides
    fun gsonConverterFactory(gson: Gson): GsonConverterFactory {
        return GsonConverterFactory.create(gson)
    }
}
