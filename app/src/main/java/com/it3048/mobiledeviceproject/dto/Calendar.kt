package com.it3048.mobiledeviceproject.dto

import com.google.gson.annotations.SerializedName

class Calendar {
    data class Calendar var Year : Int = 0, var Day : Int = 0, var Month : Int = 0, var hasMeeting : Boolean,
    (@SerializedName("id") var meetingId : Int = 0, (@SerializedName("id") var userId : Int = 0){
        override fun toString() : String {
            return Year
            return Day
        }
    }
}