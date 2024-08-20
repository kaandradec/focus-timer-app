package com.kaandradec.focustimer.domain.usecase

import com.kaandradec.focustimer.domain.model.Resource
import com.kaandradec.focustimer.domain.model.TimerSessionModel
import com.kaandradec.focustimer.domain.repository.LocalStorageRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class GetTimerSessionByDateUsecase @Inject constructor(
    private val repository: LocalStorageRepository
) {
    operator fun invoke(date: String): Flow<Resource<TimerSessionModel>> = flow {
        try {
            emit(Resource.Loading())
            emit(
                Resource.Success(
                    data = repository.getTimerSessionByDate(date)
                )
            )
        } catch (e: Exception) {
            emit(Resource.Error(e.message ?: "Unknown error"))
        }
    }
}