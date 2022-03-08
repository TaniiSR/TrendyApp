package com.task.trendy.di

import android.content.Context
import androidx.room.Room
import com.task.trendy.data.local.db.TrendyAppDB
import com.task.trendy.data.local.localservice.GitLocalDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class LocalDbModule {
    @Provides
    fun provideTrendyDao(appDatabase: TrendyAppDB): GitLocalDao {
        return appDatabase.GitLocalDao()
    }

    @Provides
    @Singleton
    fun provideAppDB(@ApplicationContext appContext: Context): TrendyAppDB {
        return Room.databaseBuilder(
            appContext,
            TrendyAppDB::class.java,
            "TrendyAppDB"
        ).build()
    }

}