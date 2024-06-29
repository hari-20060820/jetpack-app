package com.example.myapplication1

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.myapplication1.ui.theme.MyApplication1Theme


@ExperimentalMaterial3Api
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val repository = FoodRepository(context = applicationContext)

        setContent {
            MyApplication1Theme {
                var selectedTab by remember { mutableStateOf(0) }
                var showAddFoodItemScreen by remember { mutableStateOf(false) }
                var foodItems by remember { mutableStateOf(repository.getFoodItems()) }

                Scaffold(
                    floatingActionButton = {
                        if (selectedTab == 0) {
                            FloatingActionButton(onClick = { showAddFoodItemScreen = true }) {
                                Icon(Icons.Default.Add, contentDescription = "Add")
                            }
                        }
                    }
                ) { paddingValues ->
                    Column(modifier = Modifier.padding(paddingValues)) {
                        TabRow(selectedTabIndex = selectedTab) {
                            Tab(selected = selectedTab == 0, onClick = { selectedTab = 0 }) {
                                Text("Food",modifier=Modifier.padding(16.dp), fontWeight = FontWeight.Bold)
                            }
                            Tab(selected = selectedTab == 1, onClick = { selectedTab = 1 }) {
                                Text("Fun Screen", modifier = Modifier.padding(16.dp), fontWeight = FontWeight.Bold)
                            }
                        }
                        if (selectedTab == 0) {
                            FoodScreen(foodItems)
                        } else {
                            FunScreen()
                        }
                    }

                    if (showAddFoodItemScreen) {
                        AddFoodItemDialog(repository) {
                            showAddFoodItemScreen = false
                            foodItems = repository.getFoodItems() // Refresh food items list
                        }
                    }
                }
            }
        }
    }
}

@ExperimentalMaterial3Api
@Preview(showBackground = true)
@Composable
fun MainActivityPreview() {
    MyApplication1Theme {
        // Preview content
    }
}

@Composable
fun FoodScreen(foodItems: List<FoodItem>) {
    val totalPrice = foodItems.sumOf { it.price }

    Column {
        Text("Total Price: $totalPrice", modifier = Modifier.padding(16.dp), fontWeight = FontWeight.Bold, fontStyle = FontStyle.Italic)

        LazyColumn {
            itemsIndexed(foodItems) { index, foodItem ->
                Text(
                    text = "${index + 1}. Name: ${foodItem.name}, Price: ${foodItem.price}, Type: ${foodItem.type}",
                    modifier = Modifier.padding(8.dp)
                )
            }
        }
    }
}

@Composable
fun FunScreen() {
    Column {
        Text("Fun content goes here",  modifier = Modifier.padding(16.dp))
    }
}

@Composable
fun AddFoodItemDialog(repository: FoodRepository, onClose: () -> Unit) {
    var name by remember { mutableStateOf("") }
    var price by remember { mutableStateOf("") }
    var type by remember { mutableStateOf("") }

    AlertDialog(
        onDismissRequest = onClose,
        title = {
            Text(text = "Add Food Item")
        },
        text = {
            Column {
                OutlinedTextField(
                    value = name,
                    onValueChange = { name = it },
                    label = { Text("Name") },
                    modifier = Modifier.fillMaxWidth()
                )
                Spacer(modifier = Modifier.height(8.dp))
                OutlinedTextField(
                    value = price,
                    onValueChange = { price = it },
                    label = { Text("Price") },
                    modifier = Modifier.fillMaxWidth()
                )
                Spacer(modifier = Modifier.height(8.dp))
                OutlinedTextField(
                    value = type,
                    onValueChange = { type = it },
                    label = { Text("Type") },
                    modifier = Modifier.fillMaxWidth()
                )
            }
        },
        confirmButton = {
            Button(
                onClick = {
                    if (name.isNotEmpty() && price.isNotEmpty() && type.isNotEmpty()) {
                        repository.addFoodItem(FoodItem(name, price.toDouble(), type))
                        onClose()
                    }
                }
            ) {
                Text("Save")
            }
        },
        dismissButton = {
            Button(onClick = onClose) {
                Text("Cancel")
            }
        }
    )
}
