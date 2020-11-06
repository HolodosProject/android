package tk.laurenfrost.holodos.di.module

import android.content.Context
import androidx.room.Room
import dagger.Module
import dagger.Provides
import tk.laurenfrost.holodos.room.AppDatabase
import tk.laurenfrost.holodos.room.dao.BoardDao
import tk.laurenfrost.holodos.room.dao.ItemDao
import javax.inject.Singleton


@Module
class DatabaseModule(private val context: Context) {

    @Singleton
    @Provides
    fun provideDatabase(): AppDatabase {
        return Room.databaseBuilder(
            context,
            AppDatabase::class.java,
            "database"
        ).fallbackToDestructiveMigration().build()
    }


    @Singleton
    @Provides
    fun providePersonDao(db: AppDatabase): ItemDao {
        return db.itemDao()
    }

    @Singleton
    @Provides
    fun provideProjectDao(db: AppDatabase): BoardDao {
        return db.boardDao()
    }
}
