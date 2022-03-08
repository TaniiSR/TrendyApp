package com.task.trendy.data.local.db

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.task.trendy.data.dtos.responsedtos.Profile
import com.task.trendy.data.local.converter.DataConverter
import com.task.trendy.data.local.localservice.GitLocalDao

@Database(entities = [Profile::class], version = 1, exportSchema = false)
@TypeConverters(DataConverter::class)
abstract class TrendyAppDB : RoomDatabase() {
    abstract fun GitLocalDao(): GitLocalDao
}