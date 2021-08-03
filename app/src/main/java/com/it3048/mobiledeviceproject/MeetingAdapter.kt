package com.it3048.mobiledeviceproject

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.it3048.mobiledeviceproject.dto.Meeting

class MeetingAdapter(val c:Context, val meetingList: ArrayList<Meeting>):RecyclerView.Adapter<MeetingAdapter.MeetingViewHolder>() {
    inner class MeetingViewHolder(v:View):RecyclerView.ViewHolder(v){
        val title: TextView = v.findViewById<TextView>(R.id.meetingNameTxt)
        val date: TextView = v.findViewById<TextView>(R.id.meetingDateTxt)
        val description: TextView = v.findViewById<TextView>(R.id.meetingDescriptionTxt)
        val link: TextView = v.findViewById<TextView>(R.id.meetingUrlTxt)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MeetingViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val v = inflater.inflate(R.layout.meeting_card, parent, false)
        return MeetingViewHolder(v)
    }

    override fun onBindViewHolder(holder: MeetingViewHolder, position: Int) {
        val newList = meetingList[position]
        holder.title.text = newList.meetingTitle
        holder.date.text = newList.meetingDate
        holder.description.text = newList.meetingDescription
        holder.link.text = newList.meetingLocLink
    }

    override fun getItemCount(): Int {
        return meetingList.size
    }
}