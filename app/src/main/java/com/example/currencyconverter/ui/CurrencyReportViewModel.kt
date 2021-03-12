package com.example.currencyconverter.ui

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.currencyconverter.CurrencyReportLoadingError
import com.example.data.models.Currency
import com.example.data.models.CurrencyReport
import com.example.data.repositories.currency_exchange_report.CurrencyExchangeReportRepo
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import javax.inject.Inject
import kotlin.math.absoluteValue
import kotlin.math.floor

@HiltViewModel
class CurrencyReportViewModel @Inject constructor(
    private val savedStateHandle: SavedStateHandle,
    private val repo: CurrencyExchangeReportRepo
) : ViewModel() {

    var currencyReport: CurrencyReport by mutableStateOf(savedStateHandle["currencyReport"] ?: CurrencyReport.emptyCurrencyReport())
        private set
    var selectedCurrencyCharCode by mutableStateOf(savedStateHandle["selectedCurrencyCharCode"] ?: "USD")
        private set
    var isLoading by mutableStateOf(savedStateHandle["isLoading"] ?: false)
        private set
    var errorString by mutableStateOf("")
        private set
    var rublesSum by mutableStateOf(savedStateHandle["rublesSum"] ?: 1.0)
        private set
    var rublesSumString by mutableStateOf(savedStateHandle["rublesSumString"] ?: "")
        private set

    init {
        refreshCurrencyReport()
    }

    fun refreshCurrencyReport() {
        launchDataLoad {
            setNewCurrencyReport(repo.getCurrencyReport())
        }
    }

    fun selectCurrency(currencyCharCode: String) {
        setNewSelectedCurrencyCharCode(currencyCharCode)
    }

    fun onRublesSumEntered(inputString: String) {
        if (inputString.isEmpty()) {
            clearRublesSum()
        } else {
            inputString.toDoubleOrNull()?.let {
                if (it >= 0 && it < 999_999_999_999) {
                    setNewRublesSum(it)
                    setNewRublesSumString(inputString)
                }
            }
        }
    }

    private fun launchDataLoad(action: suspend () -> Unit): Job {
        return viewModelScope.launch {
            try {
                isLoading = true
                action()
                errorString = ""
            } catch (error: CurrencyReportLoadingError) {
                errorString = "Похоже, вы не в сети."
            } finally {
                isLoading = false
            }
        }
    }

    private fun setNewCurrencyReport(value: CurrencyReport) {
        currencyReport = value
        savedStateHandle["currencyReport"] = currencyReport
    }

    private fun setNewSelectedCurrencyCharCode(value: String) {
        selectedCurrencyCharCode = value
        savedStateHandle["selectedCurrencyCharCode"] = selectedCurrencyCharCode
    }

    private fun setNewRublesSum(value: Double) {
        rublesSum = value
        savedStateHandle["rublesSum"] = rublesSum
    }

    private fun setNewRublesSumString(value: String) {
        rublesSumString = value
        savedStateHandle["rublesSumString"] = rublesSumString
    }

    private fun clearRublesSum() {
        rublesSum = 1.0
        rublesSumString = ""
        savedStateHandle["rublesSum"] = rublesSum
        savedStateHandle["rublesSumString"] = rublesSumString
    }
}