package com.example.rickandmorty.data.base

import com.example.rickandmorty.core.Either
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import retrofit2.Response

abstract class BaseRepository {

    protected fun <T> doRequest(
        request: suspend () -> Response<T>
    ) : Flow<Either<String, T>> {
        return flow {
            delay(1000)
            try {
                val response = request()
                if (response.isSuccessful && response.body() != null){
                    response.body()?.let { result ->
                        emit(Either.Right(result))
                    }
                } else {
                    emit(Either.Left(response.message()))
                }
            } catch (e: Exception) {
                emit(Either.Left(e.localizedMessage ?: "Unknown Error"))
            }
        }.flowOn(Dispatchers.IO)
    }

    protected fun <T> doRequest2(
        request: suspend () -> T
    ) : Flow<Either<String, T>> {
        return flow {
            delay(1000)
            try {
                val response = request()
//                if (response.isSuccessful && response.body() != null){
//                    response.body()?.let { result ->
                        emit(Either.Right(response))
//                    }
//                } else {
//                    emit(Either.Left(response.message()))
//                }
            } catch (e: Exception) {
                emit(Either.Left(e.localizedMessage ?: "Unknown Error"))
            }
        }.flowOn(Dispatchers.IO)
    }
}