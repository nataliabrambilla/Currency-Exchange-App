package com.example.currencyexchangeapp.util

sealed class Result<T> {
    data class Error<T>(val message: String) : Result<T>()
    data class Success<T>(val value: T) : Result<T>()
}
