package com.app.hiltsample.module

import android.content.Context
import androidx.room.Room
import com.app.hiltsample.database.AppDatabase
import com.app.hiltsample.database.JokesDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
class RoomModule {

    @Provides
    fun provideRoomDatabase(@ApplicationContext context: Context): AppDatabase {
        return Room.databaseBuilder(context, AppDatabase::class.java, "userdatabase")
            .fallbackToDestructiveMigration()
            .build()
    }

    @Provides
    fun provideDao(database: AppDatabase): JokesDao {
        return database.jokesDao()
    }

}