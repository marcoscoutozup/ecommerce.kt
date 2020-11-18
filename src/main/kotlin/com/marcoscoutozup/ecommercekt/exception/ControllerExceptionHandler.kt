package com.marcoscoutozup.ecommercekt.exception

import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.context.MessageSource
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.MethodArgumentNotValidException
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.RestControllerAdvice
import java.util.*

@RestControllerAdvice
class ControllerExceptionHandler(val messageSource: MessageSource) {

    val log: Logger = LoggerFactory.getLogger(ControllerExceptionHandler::class.java)

    @ExceptionHandler(MethodArgumentNotValidException::class)
    fun handlerMethodArgumentNotValidException(e: MethodArgumentNotValidException): ResponseEntity<StandardException>{

        val errors = e.bindingResult.fieldErrors
                .map { error -> messageSource.getMessage(error, Locale.getDefault()) }
                .toSet()

        log.warn("[TRATAMENTO DE ERRO] Tratamento de erro: {}", errors)

        val standardException = StandardException(HttpStatus.BAD_REQUEST.value(), errors);
        return ResponseEntity.badRequest().body(standardException)
    }

}