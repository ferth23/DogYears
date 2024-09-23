package com.example.appdogagecalculator

import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import com.example.appdogagecalculator.ui.theme.AppDogAgeCalculatorTheme
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Face
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.input.KeyboardType
import androidx.core.text.isDigitsOnly

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            AppDogAgeCalculatorTheme {
                Surface (
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    Layout(modifier = Modifier)
                }
            }
        }
    }
}

@Composable
fun Layout(modifier: Modifier = Modifier) {
    val image = painterResource(id = R.drawable.dog)
    var edad by remember { mutableStateOf("") }
    var resultado by remember { mutableStateOf("") }
    val context = LocalContext.current

    Column (
//        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
    ){
        Image (
            painter = image,
            contentDescription = null,
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .size(500.dp, 300.dp)
                .padding(top = 50.dp)
        )
        Text (
            text = "Mis años perrunos",
            style = MaterialTheme.typography.titleMedium,
            fontSize = 35.sp,
            modifier = Modifier
                .padding(0.dp, 30.dp, 0.dp, 10.dp)
        )
        OutlinedTextField (
            value = edad,
            onValueChange = {
                    if (edad.isDigitsOnly()) {
                        edad = it
                    } else {
                        Toast.makeText(context, "Ingresa un número", Toast.LENGTH_SHORT).show()
                    }
                 },
            label = { Text("Edad humana") },
            placeholder = { Text("Edad humana") },
            leadingIcon = {
                Icon (
                    imageVector = Icons.Default.Face, contentDescription = "faceIcon"
                ) },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
            modifier = Modifier
                .width(400.dp)
                .padding(20.dp, 20.dp, 20.dp, 40.dp)
        )
        ElevatedButton(
            onClick = {
                var res = 0
                res = edad.toInt() * 7
                resultado = res.toString()
            },
            modifier = Modifier
                .width(220.dp)
                .height(60.dp)
        ) {
            Text(
                text = "Calcular",
                fontSize = 20.sp
            )
        }
        OutlinedTextField (
            value = resultado,
            onValueChange = { resultado = it },
            label = { Text("Edad perruna") },
            placeholder = { Text("Edad perruna") },
            readOnly = true,
            modifier = Modifier
                .width(400.dp)
                .padding(20.dp, 40.dp, 20.dp, 40.dp)
        )
        ElevatedButton(
            onClick = {
                edad = ""
                resultado = ""
            },
            modifier = Modifier
                .width(220.dp)
                .height(60.dp)
        ) {
            Text(
                text = "Borrar",
                fontSize = 20.sp
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun Preview() {
    AppDogAgeCalculatorTheme {
        Layout()
    }
}