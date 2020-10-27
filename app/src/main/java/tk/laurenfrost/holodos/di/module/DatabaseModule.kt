package tk.laurenfrost.holodos.di.module

import android.content.Context
import androidx.room.Room
import dagger.Module
import dagger.Provides
import tk.laurenfrost.holodos.room.AppDatabase
import tk.laurenfrost.holodos.room.dao.BoardDao
import tk.laurenfrost.holodos.room.dao.FoodDao
import javax.inject.Singleton


@Module(
    includes = [ContextModule::class]
)
class DatabaseModule {

    @Singleton
    @Provides
    fun provideDatabase(context: Context): AppDatabase {
        return Room.databaseBuilder(
            context,
            AppDatabase::class.java,
            "database"
        ).fallbackToDestructiveMigration().build()
    }


    @Singleton
    @Provides
    fun providePersonDao(db: AppDatabase): FoodDao {
        return db.foodDao()
    }

    @Singleton
    @Provides
    fun provideProjectDao(db: AppDatabase): BoardDao {
        return db.boardDao()
    }
}
