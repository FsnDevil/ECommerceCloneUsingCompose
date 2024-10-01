package com.example.ecommerceclone.ui.theme

import androidx.compose.material3.Typography
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.example.ecommerceclone.R

// Set of Material typography styles to start with
val Typography = Typography(
    bodyLarge = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Normal,
        fontSize = 16.sp,
        lineHeight = 24.sp,
        letterSpacing = 0.5.sp
    ),
    titleMedium = TextStyle(
        fontFamily = FontFamily(Font(R.font.susemedium)),
        fontWeight = FontWeight.Normal,
        fontSize = 22.sp,
        letterSpacing = 0.sp,
        color = TitleColor
    ),
    titleLarge = TextStyle(
        fontFamily = FontFamily(Font(R.font.susesemibold)),
        fontWeight = FontWeight.Normal,
        fontSize = 26.sp,
        letterSpacing = 0.sp,
        color = TitleColor
    ),
    titleSmall = TextStyle(
        fontFamily = FontFamily(Font(R.font.susesemibold)),
        fontWeight = FontWeight.Normal,
        fontSize = 14.sp,
        letterSpacing = 0.sp,
        color = TitleColor
    ),
    labelMedium = TextStyle(
        fontFamily = FontFamily(Font(R.font.susemedium)),
        fontWeight = FontWeight.SemiBold,
        fontSize = 12.sp,
        letterSpacing = 0.sp,
        color = TextColor
    )
)