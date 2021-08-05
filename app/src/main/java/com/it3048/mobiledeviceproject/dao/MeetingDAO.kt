package com.it3048.mobiledeviceproject.dao

import com.it3048.mobiledeviceproject.dto.Meeting
import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface MeetingDAO {

    @Query("SELECT * FROM meetings WHERE userCode LIKE :userCode")
    fun getAll(userCode: String): LiveData<List<Meeting>>

    @Query("SELECT * FROM meetings WHERE (userCode LIKE :userCode) AND (meetingDate LIKE :meetingDate)")
    fun getAllForDate(userCode: String, meetingDate: String): LiveData<List<Meeting>>

    @Query("SELECT * FROM meetings WHERE meetingCode LIKE :meetingCode")
    fun getMeetingDetails(meetingCode: String): Meeting

    @Insert
    fun save(meeting: Meeting)
}

//This is unused, as we are now using a room database