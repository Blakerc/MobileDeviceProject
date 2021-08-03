package com.it3048.mobiledeviceproject

import android.os.Bundle
import android.view.*
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.it3048.mobiledeviceproject.dto.Meeting
import kotlin.collections.ArrayList


class MainActivity : AppCompatActivity() {

    private var addMeetingForm = AddMeetingForm()
    private lateinit var recyclerView: RecyclerView
    private lateinit var meetingList: ArrayList<Meeting>
    private lateinit var meetingAdapter: MeetingAdapter
    private val inflater = LayoutInflater.from(this)
    private val v: View = inflater.inflate(R.layout.meeting_card, null)
    private val meetingTitle: TextView = v.findViewById<TextView>(R.id.meetingNameTxt)
    private val meetingDate: TextView = v.findViewById<TextView>(R.id.meetingDateTxt)
    private val meetingDescription: TextView = v.findViewById<TextView>(R.id.meetingDescriptionTxt)
    private val meetingLink: TextView = v.findViewById<TextView>(R.id.meetingUrlTxt)


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(findViewById(R.id.toolbar))

        /** Set up list */
        meetingList = ArrayList()
        recyclerView = findViewById(R.id.eventsList)
        meetingAdapter = MeetingAdapter(this, meetingList)
        recyclerView.adapter = meetingAdapter
        val title = meetingTitle.text.toString()
        val date = meetingDate.text.toString()
        val description = meetingDescription.text.toString()
        val link = meetingLink.text.toString()
        meetingList.add(Meeting(0, title, date, description, "Location: $link"))

        findViewById<FloatingActionButton>(R.id.fab).setOnClickListener { addMeetingForm.show(supportFragmentManager, "addForm") }

    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        /**
        * Handle action bar item clicks here. The action bar will
        * automatically handle clicks on the Home/Up button, so long
        * as you specify a parent activity in AndroidManifest.xml.
        */
        return when (item.itemId) {
            R.id.action_settings -> true
            else -> super.onOptionsItemSelected(item)
        }
    }

}
