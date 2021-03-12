package com.example.currencyconverter.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private val currencyReportViewModel: CurrencyReportViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent{
            CurrencyConverterAppUI(this.window) {
                HomeScreen(currencyReportViewModel = currencyReportViewModel)
            }
        }
    }
}