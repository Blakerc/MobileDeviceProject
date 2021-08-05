package com.it3048.mobiledeviceproject

import android.content.DialogInterface
import android.os.Bundle
import android.view.ContextThemeWrapper
import android.widget.*
import android.view.Menu
import android.view.MenuItem
import android.widget.EditText
import android.widget.Spinner
import android.widget.LinearLayout
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.activity_main.*
import java.util.*


class MainActivity : AppCompatActivity() {

    //This didn't work because it is trying to initiate stuff before the onCreate, however it works now for some reason


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(findViewById(R.id.toolbar))

        var addMeetingForm = AddMeetingForm()
        val inflater = LayoutInflater.from(this)
        val v = inflater.inflate(R.layout.meeting_card, null)
        val meetingTitle = v.findViewById<TextView>(R.id.meetingNameTxt)
        val meetingDate = v.findViewById<TextView>(R.id.meetingDateTxt)
        val meetingDescription = v.findViewById<TextView>(R.id.meetingDescriptionTxt)
        val meetingLink = v.findViewById<TextView>(R.id.meetingUrlTxt)

        /** Set up list */
        var meetingList:ArrayList<MeetingDAO> = ArrayList()
        var recyclerView: RecyclerView = findViewById(R.id.eventsList)
        var meetingAdapter: MeetingAdapter = MeetingAdapter(this, meetingList)
        recyclerView.adapter = meetingAdapter
        val title = meetingTitle.text.toString()
        val date = meetingDate.text.toString()
        val description = meetingDescription.text.toString()
        val link = meetingLink.text.toString()
        meetingList.add(MeetingDAO("$title", "$date", "$description", "Location: $link"))

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