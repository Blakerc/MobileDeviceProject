package com.it3048.mobiledeviceproject

import android.content.DialogInterface
import android.os.Bundle
import android.view.ContextThemeWrapper
import android.view.LayoutInflater
import android.widget.*
import android.view.Menu
import android.view.MenuItem
import android.widget.EditText
import android.widget.Spinner
import android.widget.LinearLayout
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.activity_main.*
import java.util.*
import kotlin.collections.ArrayList


class MainActivity : AppCompatActivity() {

    private var addMeetingForm = AddMeetingForm()
    lateinit var recyclerView: RecyclerView
    lateinit var meetingList:ArrayList<MeetingDAO>
    lateinit var meetingAdapter: MeetingAdapter
    val inflater = LayoutInflater.from(this)
    val v = inflater.inflate(R.layout.meeting_card, null)
    val meetingTitle = v.findViewById<TextView>(R.id.meetingNameTxt)
    val meetingDate = v.findViewById<TextView>(R.id.meetingDateTxt)
    val meetingDescription = v.findViewById<TextView>(R.id.meetingDescriptionTxt)
    val meetingLink = v.findViewById<TextView>(R.id.meetingUrlTxt)


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
    //Gesture Listener (when you swipe in a direction on the phone screen)
    private lateinit var  detector: GestureDetectorCompat

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_activity)
        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.container, MainFragment.newInstance())
                .commitNow()
        }
        detector = GestureDetectorCompat(this, DiaryGestureListener())
    }

    override fun onTouchEvent(event: MotionEvent?): Boolean {
        return if (detector.onTouchEvent(event)) {
            true
        } else {
            super.onTouchEvent(event)
        }
    }
    inner class DiaryGestureListener: GestureDetector.SimpleOnGestureListener(){
        private val SWIPE_THRESHOLD = 100
        private val SWIPE_VELOCITY_THRESHOLD = 100

        override fun onFling(
            downEvent: MotionEvent?,
            moveEvent: MotionEvent?,
            velocityX: Float,
            velocityY: Float
        ): Boolean {
            var diffX = moveEvent?.x?.minus(downEvent!!.x) ?:0.0F
            var diffY = moveEvent?.x?.minus(downEvent!!.y) ?:0.0F

            return if (Math.abs(diffX) > Math.abs(diffY)){
                //this is a left or right swipe
                if (Math.abs(diffX) > SWIPE_THRESHOLD && Math.abs(velocityX) > SWIPE_VELOCITY_THRESHOLD){
                    if(diffX> 0){
                        //this is a right swipe
                        this@MainActivity.onSwipeRight()
                    }else {
                        //left swipe.
                        this@MainActivity.onLeftSwipe()
                    }
                    true
                }else{
                    super.onFling(downEvent, moveEvent, velocityX, velocityY)
                }
            }else{
                //this is a bottom or a top swipe.
                if (Math.abs(diffY) > SWIPE_THRESHOLD && Math.abs(velocityY) > SWIPE_VELOCITY_THRESHOLD){
                    if(diffY > 0){
                        this@MainActivity.onSwipeTop()
                    }else{
                        this@MainActivity.onSwipeBottom()

                    }
                    true
                }else{
                    super.onFling(downEvent, moveEvent, velocityX, velocityY)
                }
            }
        }
    }

    private fun onSwipeBottom() {
        Toast.makeText(this, "Bottom Swipe", Toast.LENGTH_LONG).show()
    }

    private fun onSwipeTop() {
        Toast.makeText(this, "Top Swipe", Toast.LENGTH_LONG).show()
    }

    private fun onLeftSwipe() {
        Toast.makeText(this, "Left Swipe", Toast.LENGTH_LONG).show)

    }

    private fun onSwipeRight() {
        Toast.makeText(this, "Right Swipe", Toast.LENGTH_LONG).show()
    }


}