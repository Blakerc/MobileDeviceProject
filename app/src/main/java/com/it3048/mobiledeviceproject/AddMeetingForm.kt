package com.it3048.mobiledeviceproject

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.DialogFragment
import kotlinx.android.synthetic.main.add_meeting_form.view.*

import com.it3048.mobiledeviceproject.dto.Meeting

class AddMeetingForm: DialogFragment() {

    private var meeting = Meeting()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
       var rootView: View = inflater.inflate(R.layout.add_meeting_form, container, false)

        rootView.cancelButton.setOnClickListener{
            dismiss()
            clearFields(rootView)
        }



        rootView.submitButton.setOnClickListener{
            submitMeeting(rootView);
            dismiss()
            clearFields(rootView)
        }



        return rootView
    }
    // clear the fields in the new meeting form
    private fun clearFields(rootView: View) {
        rootView.editTextMeetingTitle.setText("")
        rootView.meetingColor.selectedItem.toString()
        rootView.meetingDate.setText("")
        rootView.editTextDescripton.setText("")
        rootView.editTextLocLink.setText("")
    }

    private fun submitMeeting(rootView: View) {
        meeting.apply {
            meetingTitle = rootView.editTextMeetingTitle.text.toString()
            meetingColor = rootView.meetingColor.selectedItem.toString()
            meetingDate = rootView.meetingDate.text.toString()
            meetingDescription = rootView.editTextDescripton.text.toString()
            meetingLocLink = rootView.editTextLocLink.text.toString()
        }
    }
}
