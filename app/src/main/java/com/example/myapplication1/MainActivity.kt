package com.example.myapplication1

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.myapplication1.ui.theme.MyApplication1Theme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MyApplication1Theme {
               MainContent()
            }
        }
    }
}

@Composable
fun MainContent()
{

       TabLayout()
            Buut()

}
@Composable
fun TabLayout() {
    var selectedTabIndex by remember { mutableStateOf(0) }
    val tabs = listOf("Tab 1", "Tab 2", "Tab 3")

    Column(modifier = Modifier.fillMaxSize()
    ) {
        TabRow(
            selectedTabIndex,
            modifier = Modifier.fillMaxWidth(),
            containerColor = Color.Magenta,
            contentColor = Color.Yellow,
        ) {
            tabs.forEachIndexed { index, title ->
                Tab(
                    selected = selectedTabIndex == index,
                    onClick = { selectedTabIndex = index },
                    text = { Text(title) }
                )
            }
        }
        when (selectedTabIndex) {
            0 -> Tabcontent(content = "Content for Tab 1")
            1 -> Tabcontent(content = "Content for Tab 2")
            2 -> Tabcontent(content = "Content for Tab 3")
        }
    }
}

@Composable
fun Tabcontent(content: String) {
    Row(
        modifier = Modifier
            .fillMaxSize()
            .padding(1.dp)
    ) {
        Text(text = content)
    }
}
@Composable
fun Buut()
{
Column(
    modifier = Modifier.fillMaxSize(),
    horizontalAlignment = Alignment.End,
    verticalArrangement = Arrangement.Bottom
)
{
    Button(onClick = { /*TODO*/ },
            modifier = Modifier
                .padding(16.dp)
                .height(48.dp)
                .width(200.dp),
        shape = RoundedCornerShape(16.dp)
    ) {
        Text(text = "+")


}}}
@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    MyApplication1Theme {
        TabLayout()
    }
}
