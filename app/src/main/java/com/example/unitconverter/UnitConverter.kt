package com.example.unitconverter

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.unitconverter.ui.theme.Purple

@Preview(showBackground = true)
@Composable
fun ShowUnitConverter() {

    var inputValue by remember { mutableStateOf("") }
    var expanded1 by remember { mutableStateOf(false) }
    var expanded2 by remember { mutableStateOf(false) }
    var inputUnit by remember { mutableStateOf("Meters") }
    var outputUnit by remember { mutableStateOf("Meters") }
    var finalValue by remember { mutableStateOf("0") }

    @SuppressLint("DefaultLocale")
    fun convertValue() {
        val inputValueDouble: Double
        try {
            inputValueDouble = inputValue.toDouble()
        } catch (e: NumberFormatException) {
            finalValue = "0"  // Set to 0 if input is not a valid number
            return
        }

        var temp = 0.0

        if (inputValue.isEmpty()) {
            finalValue = ""
        } else if (inputUnit == outputUnit) {
            finalValue = inputValue
        } else {
            // Convert input value to meters
            temp = when (inputUnit) {
                "Meters" -> inputValueDouble
                "Centimeters" -> inputValueDouble / 100.0
                "Millimeters" -> inputValueDouble / 1000.0
                "Feet" -> inputValueDouble / 3.28084
                else -> 0.0
            }

            // Convert from meters to the output unit
            val result = when (outputUnit) {
                "Meters" -> temp
                "Centimeters" -> temp * 100.0
                "Millimeters" -> temp * 1000.0
                "Feet" -> temp * 3.28084
                else -> 0.0
            }
            // Show result value to 2 decimal places
            finalValue = String.format("%.2f", result)
        }
    }


    Column(
        modifier = Modifier
            .fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            text = "Unit Converter Tool",
            fontSize = 32.sp,
            style = TextStyle(fontWeight = FontWeight.Bold),
            fontFamily = FontFamily.Serif
        )
        Spacer(modifier = Modifier.height(32.dp))
        // show output value
        Text(
            text = "Result : $finalValue $outputUnit",
            style = MaterialTheme.typography.titleLarge,
            fontFamily = FontFamily.Serif
        )
        Spacer(modifier = Modifier.height(16.dp))
        OutlinedTextField(
            value = inputValue,
            onValueChange = {
                inputValue = it
                convertValue()
            },
            label = { Text(text = "Enter value") },
            colors = OutlinedTextFieldDefaults.colors(
                focusedBorderColor = Purple,
                unfocusedBorderColor = Purple,
                focusedTextColor = Color.Black
            )

        )

        Spacer(modifier = Modifier.height(16.dp))

        Row(
            modifier = Modifier
                .fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center
        ) {

            // Left Button
            Box {
                Button(
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Purple,
                        contentColor = Color.White
                    ),
                    onClick = { expanded1 = true }) {
                    Text(text = inputUnit)
                    Icon(Icons.Default.ArrowDropDown, contentDescription = "")
                }
                DropdownMenu(expanded = expanded1, onDismissRequest = { expanded1 = false }) {
                    DropdownMenuItem(text = { Text(text = "Meters") }, onClick = {
                        expanded1 = false
                        inputUnit = "Meters"
                        convertValue()
                    })
                    DropdownMenuItem(text = { Text(text = "Millimeters") }, onClick = {
                        expanded1 = false
                        inputUnit = "Millimeters"
                        convertValue()
                    })

                    DropdownMenuItem(text = { Text(text = "Centimeters") }, onClick = {
                        expanded1 = false
                        inputUnit = "Centimeters"
                        convertValue()
                    })
                    DropdownMenuItem(text = { Text(text = "Feet") }, onClick = {
                        expanded1 = false
                        inputUnit = "Feet"
                        convertValue()
                    })

                }

            }

            Text(
                text = "To",
                fontWeight = FontWeight.Bold,
                fontSize = 20.sp,
                fontFamily = FontFamily.Serif,
                modifier = Modifier.padding(10.dp, 0.dp)
            )

            //Right Button
            Box {
                Button(
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Purple,
                        contentColor = Color.White
                    ),
                    onClick = { expanded2 = true }) {
                    Text(text = outputUnit)
                    Icon(Icons.Default.ArrowDropDown, contentDescription = "")
                }
                DropdownMenu(expanded = expanded2, onDismissRequest = { expanded2 = false }) {
                    DropdownMenuItem(text = { Text(text = "Meters") }, onClick = {
                        outputUnit = "Meters"
                        expanded2 = false
                        convertValue()
                    })
                    DropdownMenuItem(text = { Text(text = "Millimeters") }, onClick = {
                        outputUnit = "Millimeters"
                        expanded2 = false
                        convertValue()
                    })
                    DropdownMenuItem(text = { Text(text = "Centimeters") }, onClick = {
                        outputUnit = "Centimeters"
                        expanded2 = false
                        convertValue()
                    })
                    DropdownMenuItem(text = { Text(text = "Feet") }, onClick = {
                        outputUnit = "Feet"
                        expanded2 = false
                        convertValue()
                    })

                }
            }
        }
    }
}
