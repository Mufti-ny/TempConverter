package com.example.tempconverter

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.RadioButton
import android.widget.RadioGroup
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    private lateinit var radioGroupConversion: RadioGroup
    private lateinit var radioButtonFToC: RadioButton
    private lateinit var radioButtonCToF: RadioButton
    private lateinit var inputValueEditable: EditText
    private lateinit var inputValueUnEditable: TextView
    private lateinit var convertButton: Button
    private lateinit var tvHistory: TextView


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        radioGroupConversion = findViewById(R.id.radioGroupConversion)
        radioButtonFToC = findViewById(R.id.radioButtonFToC)
        radioButtonCToF = findViewById(R.id.radioButtonCToF)
        inputValueEditable = findViewById(R.id.inputValueEditable)
        inputValueUnEditable = findViewById(R.id.inputValueUnEditable)
        convertButton = findViewById(R.id.convertButton)
        tvHistory = findViewById(R.id.tvHistory)


        radioButtonFToC.isChecked = true

        convertButton.setOnClickListener {
            conversion()
        }
    }

    private fun conversion() {
        val input = inputValueEditable.text.toString().toDoubleOrNull()
        input?.let {
            val result = if (radioButtonFToC.isChecked) {
                fahrenheitToCelsius(it)
            } else {
                celsiusToFahrenheit(it)
            }
            display(result)
            updateHistory(it, result)
        }
    }

    private fun fahrenheitToCelsius(f: Double): Double {
        return ((f - 32.0) * 5.0 / 9.0).toOneDecimalPlace()
    }

    private fun celsiusToFahrenheit(c: Double): Double {
        return ((c * 9.0 / 5.0) + 32.0).toOneDecimalPlace()
    }

    private fun display(result: Double) {
        inputValueUnEditable.setText(String.format("%.1f", result))
    }


    private fun updateHistory(input: Double, result: Double) {
        val conversionType = if (radioButtonFToC.isChecked) "F to C" else "C to F"
        val historyEntry = "$conversionType: $input --> $result\n"
        tvHistory.text = historyEntry + tvHistory.text
    }

    private fun Double.toOneDecimalPlace(): Double {
        return String.format("%.1f", this).toDouble()
    }
}
