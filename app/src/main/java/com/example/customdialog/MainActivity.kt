package com.example.customdialog

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Gravity
import android.widget.*
import androidx.appcompat.content.res.AppCompatResources
import androidx.core.content.ContextCompat
import androidx.core.view.ViewCompat
import androidx.databinding.DataBindingUtil
import com.example.customdialog.databinding.ActivityMainBinding
import com.google.android.material.textfield.TextInputEditText
import java.util.ArrayList
import android.widget.LinearLayout
import com.google.android.material.textfield.TextInputLayout

class MainActivity : AppCompatActivity() {

    lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        designView(
            "Information",
            arrayListOf("Get", "Refresh", "Refresh With Wakeup","afafaf"),
            arrayListOf("Cancel", "Send","sds"),
            arrayListOf("VIN","SMS","Old pin","sfsfd")
        )
        designView(
            "Event", arrayListOf("Remote Charge Finished", "Remote Charge Interrupted"),
            arrayListOf("Cancel", "Send"), null
        )
        designView(
            "Command",
            arrayListOf(
                "Charge",
                "Charge deferred time",
                "Preconditioning",
                "Doors",
                "Warning",
                "Horn"
            ),
            arrayListOf("Cancel", "Send"), null
        )
    }

    private fun designView(
        heading: String,
        actions: ArrayList<String>,
        buttons: ArrayList<String>,
        inputs: ArrayList<String>?
    ) {

        /**
         * Parent LinearLayout containing all custom children
         */
        val linearLayout: LinearLayout = binding.linearLayout

        /**
         * Radio Group for actions and its layout params
         */
        val radioGroup = RadioGroup(this)
        val radioGroupParams = LinearLayout.LayoutParams(
            LinearLayout.LayoutParams.MATCH_PARENT,
            LinearLayout.LayoutParams.WRAP_CONTENT
        )
        radioGroupParams.setMargins(5, 5, 5, 5)
        radioGroup.weightSum = actions.size.toFloat()
        radioGroup.orientation = RadioGroup.HORIZONTAL
        radioGroup.layoutParams = radioGroupParams

        /**
         * Adding actions as radio buttons to radio group
         */
        actions.forEach {
            val radioButton = RadioButton(this)
            radioButton.background = ContextCompat.getDrawable(this, R.drawable.radio_selector)
            radioButton.buttonDrawable = null
            radioButton.text = it
            radioButton.setPadding(5, 5, 5, 5)
            radioButton.setTextColor(
                AppCompatResources.getColorStateList(
                    this,
                    R.color.btn_text_color
                )
            )
            radioButton.gravity = Gravity.CENTER
            ViewCompat.setElevation(radioButton, 7f)
            val radioButtonParams = LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.WRAP_CONTENT,
                LinearLayout.LayoutParams.WRAP_CONTENT
            )
            radioButtonParams.weight = 1f
            radioButtonParams.setMargins(5, 5, 5, 5)
            radioButton.layoutParams = radioButtonParams
            radioButton.maxLines = 1
            radioGroup.addView(radioButton)
        }
        /**
         * Added radio group and all actions as radio buttons
         */
        linearLayout.addView(radioGroup)

        /**
         * Input fields
         */

        inputs?.forEach {
            val textInputLayout = TextInputLayout(this)
            textInputLayout.hint = it
            textInputLayout.boxBackgroundColor = ContextCompat.getColor(this,R.color.white)
            textInputLayout.boxBackgroundMode = TextInputLayout.BOX_BACKGROUND_OUTLINE
            textInputLayout.setBoxCornerRadii(5f,5f,5f,5f)
            val textInputLayoutParams = LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.WRAP_CONTENT
            )
            textInputLayoutParams.setMargins(5,5,5,5)

            textInputLayout.layoutParams = textInputLayoutParams
            val textInputEditText = TextInputEditText(textInputLayout.context)
            textInputLayout.addView(textInputEditText)
            linearLayout.addView(textInputLayout)
        }


        val buttonsLinearLayout = LinearLayout(this)
        val buttonsLinearLayoutParams = LinearLayout.LayoutParams(
            LinearLayout.LayoutParams.MATCH_PARENT,
            LinearLayout.LayoutParams.WRAP_CONTENT
        )
        buttonsLinearLayoutParams.gravity = Gravity.CENTER
        buttonsLinearLayout.weightSum = buttons.size.toFloat()
        buttonsLinearLayout.layoutParams = buttonsLinearLayoutParams
        buttonsLinearLayout.orientation = LinearLayout.HORIZONTAL
        buttons.forEach {
            val button = Button(this)
            button.text = it
            val buttonParams = LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.WRAP_CONTENT,
                LinearLayout.LayoutParams.WRAP_CONTENT
            )
            buttonParams.setMargins(5, 5, 5, 5)
            buttonParams.gravity = Gravity.CENTER
            buttonParams.weight = 1f
            button.maxWidth = 100
            button.setTextColor(ContextCompat.getColor(this, R.color.white))
            button.setPadding(5, 5, 5, 5)
            button.layoutParams = buttonParams
            button.setBackgroundColor(ContextCompat.getColor(this, R.color.bleufonce))
            buttonsLinearLayout.addView(button)
        }

        linearLayout.addView(buttonsLinearLayout)
    }
}