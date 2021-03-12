package com.example.currencyconverter.ui

import android.view.Window
import android.view.WindowManager
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.CloudOff
import androidx.compose.material.icons.rounded.Refresh
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import com.example.data.models.Currency
import com.example.data.models.CurrencyReport
import com.example.jetnews.ui.theme.AppTheme


@Composable
fun CurrencyConverterAppUI(
    window: Window,
    content: @Composable () -> Unit
) {
    AppTheme(window) {
        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS)
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
        window.statusBarColor = MaterialTheme.colors.primaryVariant.toArgb()

        content()
    }
}

@Composable
fun HomeScreen(
    currencyReportViewModel: CurrencyReportViewModel
) {

    val selectedCurrencyCharCode: String = currencyReportViewModel.selectedCurrencyCharCode
    val rublesSum = currencyReportViewModel.rublesSum
    val currencyReport: CurrencyReport = currencyReportViewModel.currencyReport
    val isLoading = currencyReportViewModel.isLoading
    val rublesSumString = currencyReportViewModel.rublesSumString
    val errorString = currencyReportViewModel.errorString

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(text = "Currency converter")
                },
                backgroundColor = MaterialTheme.colors.primary
            )
        },
        backgroundColor = MaterialTheme.colors.background,
        contentColor = MaterialTheme.colors.onPrimary,
        floatingActionButton = {
            FloatingActionButton(
                onClick = {
                    currencyReportViewModel.refreshCurrencyReport()
                },
                backgroundColor = MaterialTheme.colors.secondary,
                contentColor = MaterialTheme.colors.onSecondary
            ) {
                Icon(
                    Icons.Rounded.Refresh,
                    "refresh currency list",
                    modifier = Modifier.padding(16.dp)
                )
            }
        }) {
        Column(modifier = Modifier.padding(16.dp)) {
            CurrencyConverter(
                rublesSumString,
                rublesSum,
                currencyReport,
                selectedCurrencyCharCode
            ) { inputString ->
                currencyReportViewModel.onRublesSumEntered(inputString)
            }
            Spacer(modifier = Modifier.height(8.dp))
            CurrenciesList(
                currencyReport,
                isLoading,
                errorString
            ) { currency ->
                currencyReportViewModel.selectCurrency(currency.charCode)
            }
        }
    }
}

@Composable
fun CurrencyConverter(
    rublesSumString: String,
    rublesSum: Double,
    currencyReport: CurrencyReport,
    selectedCurrencyCharCode: String,
    onRublesSumEntered: (String) -> Unit
) {
    val selectedCurrency = currencyReport.getByCharCode(selectedCurrencyCharCode)
    val currencySum = selectedCurrency.getActualValue() * rublesSum

    Row(horizontalArrangement = Arrangement.Center) {
        OutlinedTextField(
            colors = TextFieldDefaults.textFieldColors(cursorColor = Color.LightGray),
            trailingIcon = { Text("RUB") },
            value = rublesSumString,
            placeholder = {
                Text(
                    "1.0",
                    style = MaterialTheme.typography.body1,
                    color = Color.LightGray
                )
            },
            onValueChange = onRublesSumEntered,
            modifier = Modifier
                .weight(1f)
                .align(alignment = Alignment.CenterVertically)
                .background(Color.Transparent),
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Phone)
        )
        Text(
            "=", modifier = Modifier
                .align(alignment = Alignment.CenterVertically)
                .padding(horizontal = 8.dp)
        )
        OutlinedTextField(
            trailingIcon = { Text(selectedCurrency.charCode) },
            value = "%.2f".format(currencySum),
            onValueChange = {},
            readOnly = true,
            modifier = Modifier
                .weight(1f)
                .align(alignment = Alignment.CenterVertically)
                .background(Color.Transparent)
        )
    }
}

@Composable
fun CurrenciesList(
    currencyReport: CurrencyReport,
    loading: Boolean,
    errorString: String,
    onCurrencyClick: (currency: Currency) -> Unit
) {
    if (loading) {
        Box(modifier = Modifier.fillMaxSize()) {
            Text(text = "Загрузка...", modifier = Modifier.align(Alignment.Center))
        }
    } else {
        if (errorString == "") {
            Surface(shape = RoundedCornerShape(4.dp), color = Color.Transparent) {
                LazyColumn() {
                    items(currencyReport.currencyRateList) { currency ->
                        Card(
                            shape = RoundedCornerShape(4.dp),
                            modifier = Modifier.padding(vertical = 4.dp),
                            contentColor = MaterialTheme.colors.onPrimary
                        ) {
                            Row(modifier = Modifier
                                .fillMaxWidth()
                                .background(
                                    MaterialTheme.colors.surface
                                )
                                .clickable {
                                    onCurrencyClick(currency)
                                }
                                .padding(8.dp)
                            ) {
                                CurrencyCard(currency)
                            }
                        }
                    }
                }
            }
        } else {
            Box(modifier = Modifier.fillMaxSize()) {
                Column(modifier = Modifier.align(Alignment.Center)) {
                    Icon(
                        Icons.Rounded.CloudOff,
                        null,
                        modifier = Modifier
                            .align(Alignment.CenterHorizontally)
                            .padding(bottom = 8.dp)
                    )
                    Text(text = errorString)
                }
            }
        }

    }
}

@Composable
fun CurrencyCard(currency: Currency) {
    Box {
        Text(
            text = currency.charCode,
            style = MaterialTheme.typography.h5,
            modifier = Modifier.align(alignment = Alignment.CenterStart),
            color = MaterialTheme.colors.onPrimary
        )
        Column(
            modifier = Modifier.padding(start = 80.dp)
        ) {
            Text(
                text = currency.name,
                color = MaterialTheme.colors.onPrimary
            )
            Text(
                text = "${currency.value} RUB за ${currency.nominal} д.е.",
                color = MaterialTheme.colors.onPrimary
            )
        }
    }
}
