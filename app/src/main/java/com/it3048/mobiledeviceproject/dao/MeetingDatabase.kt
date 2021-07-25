package com.it3048.mobiledeviceproject.dao

import androidx.room.Database
import androidx.room.RoomDatabase
import com.it3048.mobiledeviceproject.dto.Meeting

@Database(entities = [Meeting::class], version = 1)
abstract class MeetingDatabase : RoomDatabase() {
    abstract fun localMeetingDAO() : MeetingDAO
}
