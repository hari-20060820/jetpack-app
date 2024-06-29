package com.example.myapplication1

import android.content.Context
import java.io.File

data class FoodItem(val name: String, val price: Double, val type: String)

class FoodRepository(private val context: Context) {

    private val fileName = "food_items.txt"

    fun getFoodItems(): List<FoodItem> {
        val file = File(context.filesDir, fileName)
        if (!file.exists()) return emptyList()

        return file.readLines().map { line ->
            val parts = line.split(",")
            FoodItem(parts[0], parts[1].toDouble(), parts[2])
        }
    }

    fun addFoodItem(foodItem: FoodItem) {
        val file = File(context.filesDir, fileName)
        file.appendText("${foodItem.name},${foodItem.price},${foodItem.type}\n")
    }
}
