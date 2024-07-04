package com.example.myapplication1
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Button
import androidx.compose.material3.Slider
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.myapplication1.ui.theme.MyApplication1Theme

import kotlinx.coroutines.delay
import kotlin.random.Random

val colorNames = mapOf(
    Color.Red to "Red",
    Color.Yellow to "Yellow",
    Color.Green to "Green",
    Color.Blue to "Blue",
    Color.Magenta to "Magenta",
    Color.Cyan to "Cyan",
    Color.Black to "Black",
    Color.White to "White"
)

@Composable
fun FunScreen() {
    var memberCount by remember { mutableStateOf(3f) } // Initial member count
    var selectedColorIndex by remember { mutableStateOf(0) } // Index of the selected color

    val standardColors = listOf(
        Color.Red, Color.Yellow, Color.Green, Color.Blue,
        Color.Magenta, Color.Cyan, Color.Black, Color.White
    )

    var selectedColorForTreat by remember { mutableStateOf(Color.Black) }
    var treatMessage by remember { mutableStateOf("") }

    LaunchedEffect(Unit) {
        while (true) {
            delay(1000)
            selectedColorIndex = (selectedColorIndex + 1) % standardColors.size
        }
    }

    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Canvas(
            modifier = Modifier
                .size(200.dp)
                .padding(16.dp)
                .background(standardColors[selectedColorIndex], CircleShape)
        ) {
            drawCircle(color = standardColors[selectedColorIndex])
        }

        Spacer(modifier = Modifier.height(32.dp))

        Slider(
            value = memberCount,
            onValueChange = { newValue -> memberCount = newValue },
            valueRange = 1f..10f,
            steps = 9,
            modifier = Modifier.padding(horizontal = 16.dp)
        )

        Spacer(modifier = Modifier.height(16.dp))

        Text("Number of Members: ${memberCount.toInt()}")

        Spacer(modifier = Modifier.height(16.dp))

        Button(
            onClick = {
                selectedColorForTreat = standardColors[Random.nextInt(standardColors.size)]
                treatMessage = "The color ${colorNames[selectedColorForTreat]} should pay!"
            },
            modifier = Modifier.padding(16.dp)
        ) {
            Text("Select Color for Treat")
        }

        Spacer(modifier = Modifier.height(16.dp))

        Text(
            text = treatMessage,
            fontStyle = FontStyle.Italic,
            fontWeight = FontWeight.Bold,
            textAlign = TextAlign.Center,
            modifier = Modifier.padding(horizontal = 16.dp)
        )
    }
}

@Preview(showBackground = true)
@Composable
fun FunScreenPreview() {
    MyApplication1Theme{
        FunScreen()
    }
}
