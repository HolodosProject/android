package tk.laurenfrost.holodos.di.module

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import tk.laurenfrost.holodos.service.UserService
import javax.inject.Singleton


@Module
class RestApiModule {
    @Singleton
    @Provides
    fun userService(retrofit: Retrofit): UserService {
        return retrofit.create<UserService>(UserService::class.java)
    }

    @Singleton
    @Provides
    fun retrofit(
        gsonConverterFactory: GsonConverterFactory
    ): Retrofit {
        return Retrofit.Builder()
            .baseUrl("https://jsonplaceholder.typicode.com/")
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
