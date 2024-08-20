package com.kaandradec.focustimer.data.repository

import com.kaandradec.focustimer.data.local.dao.TimerSessionDao
import com.kaandradec.focustimer.domain.model.TimerSessionModel
import com.kaandradec.focustimer.domain.model.toTimerSessionEntity
import com.kaandradec.focustimer.domain.repository.LocalStorageRepository
import javax.inject.Inject

class LocalStorageRepositoryImpl @Inject constructor(
    private val timerSessionDao: TimerSessionDao
) : LocalStorageRepository {

    override suspend fun saveTimerSession(timerSessionModel: TimerSessionModel): Boolean {
        try {
            val result =
                timerSessionDao.insertTimerSession(timerSessionModel.toTimerSessionEntity())
            // Inserts en room devuelve el id del registro insertado, si no, devuelve -1
            return result.toInt() != -1
        } catch (e: Exception) {
            throw e
        }
    }

    override suspend fun getTimerSessionByDate(date: String): TimerSessionModel {
        try {
            var timerValue: Long = 0
            var round = 0
            timerSessionDao.getTimerSessionByDate(date).map {
                timerValue += it.value ?: 0
                round += 1
            }
            return TimerSessionModel(
                date = date,
                value = timerValue,
                round = round
            )
        } catch (e: Exception) {
            throw e
        }
    }
}