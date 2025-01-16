package com.sisiame.openweatherapiapp.presentation.ui.theme

import androidx.compose.material3.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.sisiame.openweatherapiapp.R

val fontFamily = FontFamily.Default

val Typography = Typography(
    headlineLarge = TextStyle(
        fontFamily = fontFamily,
        fontWeight = FontWeight.SemiBold,
        fontSize = 70.sp,
        lineHeight = 105.sp,
        letterSpacing = 0.5.sp,
        color = ink
    ),
    headlineMedium = TextStyle(
        fontFamily = fontFamily,
        fontWeight = FontWeight.Bold,
        fontSize = 30.sp,
        lineHeight = 45.sp,
        letterSpacing = 0.5.sp,
        color = ink
    ),
    headlineSmall = TextStyle(
        fontFamily = fontFamily,
        fontWeight = FontWeight.Bold,
        fontSize = 15.sp,
        lineHeight = 22.5.sp,
        letterSpacing = 0.5.sp,
        color = ink
    ),
    labelLarge = TextStyle(
        fontFamily = fontFamily,
        fontWeight = FontWeight.SemiBold,
        fontSize = 15.sp,
        lineHeight = 22.5.sp,
        letterSpacing = 0.5.sp,
        color = gray
    ),
    labelMedium = TextStyle(
        fontFamily = fontFamily,
        fontWeight = FontWeight.Normal,
        fontSize = 15.sp,
        lineHeight = 22.5.sp,
        letterSpacing = 0.5.sp,
        color = lightishGray
    ),
    labelSmall = TextStyle(
        fontFamily = fontFamily,
        fontWeight = FontWeight.SemiBold,
        fontSize = 12.sp,
        lineHeight = 18.sp,
        letterSpacing = 0.5.sp,
        color = gray
    ),
    bodyLarge = TextStyle(
        fontFamily = fontFamily,
        fontWeight = FontWeight.SemiBold,
        fontSize = 60.sp,
        lineHeight = 90.sp,
        letterSpacing = 0.5.sp,
        color = ink
    ),
    bodyMedium = TextStyle(
        fontFamily = fontFamily,
        fontWeight = FontWeight.Bold,
        fontSize = 20.sp,
        lineHeight = 30.sp,
        letterSpacing = 0.5.sp,
        color = ink
    ),
    bodySmall = TextStyle(
        fontFamily = fontFamily,
        fontWeight = FontWeight.Normal,
        fontSize = 15.sp,
        lineHeight = 22.5.sp,
        letterSpacing = 0.5.sp,
        color = ink
    ),
)