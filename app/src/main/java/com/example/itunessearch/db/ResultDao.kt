package com.example.itunessearch.db

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.itunessearch.models.Result

@Dao
interface ResultDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun upsert(result: Result): Long

    @Query("SELECT * FROM results")
    fun getAllResults(): LiveData<List<Result>>

    @Delete
    suspend fun deleteResult(result: Result)
}