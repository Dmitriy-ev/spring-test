package com.example.springtest.controller

import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestControllerAdvice

@RestControllerAdvice
class ErrorHandler {

    @ExceptionHandler(RuntimeException::class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    fun handleException(exception: RuntimeException): String {
        return "error"
    }//вернет статус код вместо объекта
}