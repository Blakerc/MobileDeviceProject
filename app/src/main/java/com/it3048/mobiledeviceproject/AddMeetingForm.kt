package com.it3048.mobiledeviceproject

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import com.it3048.mobiledeviceproject.databinding.AddMeetingFormBinding
import kotlinx.android.synthetic.main.add_meeting_form.view.*

import com.it3048.mobiledeviceproject.dto.Meeting

class AddMeetingForm: DialogFragment() {

    private var meeting = Meeting()
    private var _binding: AddMeetingFormBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = AddMeetingFormBinding.inflate(inflater, container, false)
        val view = binding.root
        // Inflate the layout for this fragment

        view.cancelButton.setOnClickListener{
            dismiss()
            clearFields(view)
        }




        view.submitButton.setOnClickListener{
            //this is where the inputs get saved to state
//            var meetingTitle = rootView.editTextMeetingTitle.text.toString()
//            var meetingColor = rootView.meetingColor.selectedItem.toString()
//            var meetingDate = rootView.meetingDate.text.toString()
//            var meetingDescription = rootView.editTextDescripton.text.toString()
//            var meetingLocLink = rootView.editTextLocLink.text.toString()
//
//            Toast.makeText(context, "submitted", Toast.LENGTH_LONG).show()
//            Log.i(meetingTitle, "meetingTitle")
//            Log.i(meetingColor, "meetingColor")
//            Log.i(meetingDate, "meetingDate")
//            Log.i(meetingDescription, "meetingDescription")
//            Log.i(meetingLocLink, "meetingLocLink")

            submitMeeting(view);
            dismiss()
            clearFields(view)
        }



        return view
    }
    // clear the fields in the new meeting form
    private fun clearFields(view: View) {
        view.editTextMeetingTitle.setText("")
        view.meetingColor.selectedItem.toString()
        view.meetingDate.setText("")
        view.editTextDescripton.setText("")
        view.editTextLocLink.setText("")
    }

    private fun submitMeeting(view: View) {
        meeting.apply {
            meetingTitle = view.editTextMeetingTitle.text.toString()
            meetingColor = view.meetingColor.selectedItem.toString()
            meetingDate = view.meetingDate.text.toString()
            meetingDescription = view.editTextDescripton.text.toString()
            meetingLocLink = view.editTextLocLink.text.toString()
        }
    }
}