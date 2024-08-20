package com.kaandradec.focustimer.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.kaandradec.focustimer.data.local.dao.TimerSessionDao
import com.kaandradec.focustimer.data.local.entity.TimerSessionEntity

@Database(entities = [TimerSessionEntity::class], version = 1, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {
    abstract fun timerSessionDao(): TimerSessionDao
}