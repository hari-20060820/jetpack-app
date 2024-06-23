package com.example.myapplication1

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.myapplication1.Additem
import com.example.myapplication1.ui.theme.MyApplication1Theme


data class Starter(val name:String)
data class Maincourse(val name: String)
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

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainContent()
{ val context= LocalContext.current
    val activity=context as? Activity
    Box(modifier = Modifier.fillMaxSize())
    {
        TabLayout()

    FloatingActionButton(onClick = {
                                   context.startActivity(Intent(context, Additem::class.java))
        activity?.finish()
    },
        containerColor = Color.Yellow,
        modifier = Modifier
            .align(Alignment.BottomEnd)
            .padding(15.dp)){
        Icon(Icons.Default.Add, contentDescription = "Add", tint = Color.Black)
    }
}}
@Composable
fun TabLayout() {
    var selectedTabIndex by remember { mutableStateOf(0) }
    val tabs = listOf("Food", "FunScreen")

    Column(modifier = Modifier.fillMaxSize()
    ) {
        TabRow(
            selectedTabIndex,
            modifier = Modifier.fillMaxWidth(),
            containerColor = Color.Black,
            contentColor = Color.White,
        ) {
            tabs.forEachIndexed { index, title ->
                Tab(
                    selected = selectedTabIndex == index,
                    onClick = { selectedTabIndex = index },
                    text = { Text(title) }
                )
            }
        }

    }
}



@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    MyApplication1Theme {
        MainContent()
    }
}
