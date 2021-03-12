package com.example.currencyconverter

import java.lang.Exception

class CurrencyReportLoadingError(
    message: String,
    cause: Throwable
): Exception(message, cause) {
}