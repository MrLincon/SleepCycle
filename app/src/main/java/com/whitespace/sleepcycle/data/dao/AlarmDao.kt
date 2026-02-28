package com.whitespace.sleepcycle.data.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.whitespace.sleepcycle.data.entity.AlarmEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface AlarmDao {
    @Insert
    suspend fun insert(alarm: AlarmEntity): Long

    @Query("DELETE FROM alarms WHERE id = :id")
    suspend fun deleteById(id: Int)

    @Query("SELECT * FROM alarms WHERE triggerTimeMillis > :now ORDER BY triggerTimeMillis ASC")
    fun getActiveAlarms(now: Long): Flow<List<AlarmEntity>>

    @Query("DELETE FROM alarms WHERE triggerTimeMillis <= :now")
    suspend fun clearExpired(now: Long = System.currentTimeMillis())
}