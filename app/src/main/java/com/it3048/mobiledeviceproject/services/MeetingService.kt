package com.it3048.mobiledeviceproject.services

import android.app.Application
import android.content.ContentValues
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.room.Room
import com.it3048.mobiledeviceproject.RetrofitInstance
import com.it3048.mobiledeviceproject.dao.MeetingDAO
import com.it3048.mobiledeviceproject.dao.MeetingDatabase
import com.it3048.mobiledeviceproject.dto.Meeting
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.withContext

class MeetingService(application: Application) {
    private val application = application

    internal fun save(meeting: Meeting) {
        getLocalMeetingDAO().save(meeting)
    }

    internal suspend fun getAllUsersMeetings(userCode: String) {
        withContext(Dispatchers.IO) {
            val service = RetrofitInstance.retrofitInstance?.create(MeetingDAO::class.java)
            val meetings = async {service?.getAll(userCode = "PLACEHOLDER")}
            /**TODO: pass userCode^*/

            updateALLMeetings(meetings.await())
        }
    }

    private suspend fun updateALLMeetings(meetings: LiveData<List<Meeting>>?) {
        try {
            var localMeetingDAO = getLocalMeetingDAO()
            localMeetingDAO.getAll(userCode = "PLACEHOLDER")
            /**TODO: pass userCode^*/
        }catch (e: Exception) {
            e.message?.let { Log.e(ContentValues.TAG, it) }
        }

    }

    internal suspend fun getMeetingDetails(userCode: String) {
        withContext(Dispatchers.IO) {
            val service = RetrofitInstance.retrofitInstance?.create(MeetingDAO::class.java)
            val meeting = async {service?.getMeetingDetails(meetingCode = "PLACEHOLDER")}
            /**TODO: pass meetingCode^*/

            updateMeetingDetails(meeting.await())
        }
    }

    private suspend fun updateMeetingDetails(meeting: Meeting?) {
        try {
            var localMeetingDAO = getLocalMeetingDAO()
            localMeetingDAO.getMeetingDetails(meetingCode = "PLACEHOLDER")
        }catch (e: Exception) {
            e.message?.let { Log.e(ContentValues.TAG, it) }
        }

    }

    internal fun getLocalMeetingDAO() : MeetingDAO {
        val db = Room.databaseBuilder(application, MeetingDatabase::class.java, "MobileMeetings").build()
        val localMeetingDAO = db.localMeetingDAO()
        return localMeetingDAO
    }
}
