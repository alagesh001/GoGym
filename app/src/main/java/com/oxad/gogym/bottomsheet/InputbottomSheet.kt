package com.oxad.gogym.bottomsheet


import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.oxad.gogym.R

class InputBottomSheet : BottomSheetDialogFragment() {

    private lateinit var listener: OnInputListener

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is OnInputListener) {
            listener = context
        } else {
            throw RuntimeException("$context must implement OnInputListener")
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val view = inflater.inflate(R.layout.bottom_sheet_input, container, false)

        val editText = view.findViewById<EditText>(R.id.editTextNumber)
        val buttonSubmit = view.findViewById<Button>(R.id.buttonSubmit)

        buttonSubmit.setOnClickListener {
            val inputText = editText.text.toString()
            if (inputText.isNotEmpty()) {
                val inputValue = inputText.toFloat()
                listener.onInputReceived(inputValue)
                dismiss()
            }
        }

        return view
    }

    interface OnInputListener {
        fun onInputReceived(value: Float)
    }
}
