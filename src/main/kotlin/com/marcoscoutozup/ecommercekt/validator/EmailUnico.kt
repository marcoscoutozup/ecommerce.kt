package com.marcoscoutozup.ecommercekt.validator

import javax.validation.Constraint
import javax.validation.Payload
import kotlin.annotation.AnnotationRetention.RUNTIME
import kotlin.annotation.AnnotationTarget.FIELD
import kotlin.reflect.KClass

@Target(FIELD)
@Retention(RUNTIME)
@MustBeDocumented
@Constraint(validatedBy = arrayOf(EmailUnicoValidator::class))
annotation class EmailUnico (
    val message: String = "O email não é único",
    val groups: Array<KClass<Any>> = [],
    val payload: Array<KClass<Payload>> = []
)