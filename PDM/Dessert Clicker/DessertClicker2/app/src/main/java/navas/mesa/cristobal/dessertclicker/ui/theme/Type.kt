package com.example.dessertclicker.ui.theme

import androidx.compose.material3.Typography
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp


val Typography = Typography(
    bodyLarge = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Normal,
        fontSize = 16.sp,
        lineHeight = 24.sp,
        color = Color.Black
    ),
    headlineMedium = TextStyle(
        color = Color(0xFF008577),
        fontWeight = FontWeight.Bold,
        fontSize = 33.sp,
        lineHeight = 40.sp
    ),
    headlineSmall = TextStyle(
        color = Color(0x99000000),
        fontWeight = FontWeight.Bold,
        fontSize = 20.sp,
        lineHeight = 28.sp
    )
)
