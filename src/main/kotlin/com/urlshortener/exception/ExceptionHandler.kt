package com.urlshortener.exception

import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.RestControllerAdvice

@RestControllerAdvice
class ExceptionHandler {
    @ExceptionHandler(value = [ShortUrlAlreadyExistException::class])
    fun handleExceptionsToBadRequest(ex: Exception): ResponseEntity<String> {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.message.orEmpty())
    }
    @ExceptionHandler(value = [UrlNotFoundException::class])
    fun handleExceptionsToNotFound(ex: Exception): ResponseEntity<String> {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.message.orEmpty())
    }

}