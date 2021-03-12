/*
 * Copyright 2020 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.example.jetnews.ui.theme

import android.view.Window
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material.MaterialTheme
import androidx.compose.material.darkColors
import androidx.compose.material.lightColors
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

private val LightThemeColors = lightColors(
    primary = Red700,
    primaryVariant = Red900,
    onPrimary = Color.White,
    secondary = Red700,
    secondaryVariant = Red900,
    onSecondary = Color.White,
    error = Red800,
    surface = Red300,
    background = Red200,
    onSurface = Color.Black,
    onBackground = Color.Black

)

private val DarkThemeColors = darkColors(
    primary = BlueGray800Dark,
    primaryVariant = BlueGray900Dark,
    onPrimary = Color.White,
    secondary = BlueGray600Dark,
    secondaryVariant = BlueGray700Dark,
    onSecondary = Color.White,
    error = Red900,
    surface = BlueGray600Dark,
    background = BlueGray700Dark,
    onSurface = Color.White,
    onBackground = Color.White
)

@Composable
fun AppTheme(
    window: Window,
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
) {
    MaterialTheme(
        colors = if (darkTheme) DarkThemeColors else LightThemeColors,
        typography = AppTypography,
        shapes = AppShapes,
        content = content
    )
}
