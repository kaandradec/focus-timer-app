package com.kaandradec.focustimer.domain.repository

import com.kaandradec.focustimer.domain.model.TimerSessionModel

interface LocalStorageRepository {
    suspend fun saveTimerSession(timerSessionModel: TimerSessionModel): Boolean
    suspend fun getTimerSessionByDate(date: String): TimerSessionModel
}
