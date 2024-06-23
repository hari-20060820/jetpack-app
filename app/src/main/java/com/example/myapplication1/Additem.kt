package com.example.myapplication1

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldColors
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.rememberTopAppBarState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.myapplication1.ui.theme.MyApplication1Theme
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.platform.LocalContext
import com.example.myapplication1.ui.theme.MyApplication1Theme
import com.example.myapplication1.MainActivity
import kotlin.collections.plusAssign


@ExperimentalMaterial3Api
class Additem : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            MyApplication1Theme {
                Maincontente()
            }
        }
    }}

data class Item(val name: String)

// Pre-saved sample data



data class FoodItem(val name: String, val price: Double, val type: String)
@Composable
fun Maincontente() {
    val context= LocalContext.current
    val activity=context as? Activity
    var foodItems = remember { mutableStateOf(listOf<FoodItem>()) }
    var name = remember { mutableStateOf("") }
    var price = remember { mutableStateOf("") }
    var type = remember { mutableStateOf("") }


    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Top,

        ) {
        Text(
            text = " FOOD ",
            fontSize = 50.sp,
            fontWeight = FontWeight.Bold ,
            color = Color.Black,

            )
        Spacer(modifier = Modifier.height(50.dp))
        OutlinedTextField(
            value = name.value,
            onValueChange = {  name.value = it },
            label = { Text("Enter name") },
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
        )
        Spacer(modifier = Modifier.height(3.dp))
        OutlinedTextField(
            value = price.value,
            onValueChange = {  price.value = it },
            label = { Text("Enter Price") },
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
        )
        Spacer(modifier = Modifier.height(3.dp))
        OutlinedTextField(
            value = type.value,
            onValueChange = {  type.value = it },
            label = { Text("Enter Type") },
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
        )
        Spacer(modifier = Modifier.height(10.dp))
       Button(onClick = {
           context.startActivity(Intent(context, MainActivity::class.java))
           activity?.finish() }) {
           Text(text = "Save")

       }

        }
    }




@Composable
fun FoodList(foodItems: List<FoodItem>) {
    Column {
        foodItems.forEach { foodItem ->
            Text(text = "Name: ${foodItem.name}, Price: ${foodItem.price}, Type: ${foodItem.type}")
            Spacer(modifier = Modifier.height(8.dp))
        }
    }
}
