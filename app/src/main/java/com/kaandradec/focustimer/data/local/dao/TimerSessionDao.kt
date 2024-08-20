package com.kaandradec.focustimer.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.kaandradec.focustimer.data.local.entity.TimerSessionEntity

@Dao
interface TimerSessionDao {
    @Query("SELECT * FROM timer_session WHERE date LIKE ''||:date||'%'")
    suspend fun getTimerSessionByDate(date: String): MutableList<TimerSessionEntity>

    // Inserts en room devuelve el id del registro insertado, si no, devuelve -1
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertTimerSession(timerSessionEntity: TimerSessionEntity): Long
}