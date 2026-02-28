package com.whitespace.sleepcycle.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.whitespace.sleepcycle.data.entity.AlarmEntity
import com.whitespace.sleepcycle.data.dao.AlarmDao

@Database(entities = [AlarmEntity::class], version = 1)
abstract class AlarmDatabase : RoomDatabase() {
    abstract fun alarmDao(): AlarmDao
}