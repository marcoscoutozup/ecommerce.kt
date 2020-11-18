package com.marcoscoutozup.ecommercekt.validator.registrosminimos

import javax.validation.Constraint
import javax.validation.Payload
import kotlin.reflect.KClass

@Target(AnnotationTarget.FIELD)
@Retention(AnnotationRetention.RUNTIME)
@MustBeDocumented
@Constraint(validatedBy = arrayOf(RegistrosMinimosValidator::class))
annotation class RegistrosMinimos (
        val message: String = "A lista não apresenta o mínimo de registros",
        val groups: Array<KClass<Any>> = [],
        val payload: Array<KClass<Payload>> = []
)