package com.marcoscoutozup.ecommercekt.validator.categoriaexistente

import javax.validation.Constraint
import javax.validation.Payload
import kotlin.annotation.AnnotationRetention.RUNTIME
import kotlin.annotation.AnnotationTarget.FIELD
import kotlin.reflect.KClass

@Target(FIELD)
@Retention(RUNTIME)
@MustBeDocumented
@Constraint(validatedBy = arrayOf(CategoriaExistenteValidator::class))
annotation class CategoriaExistente (
    val message: String = "A categoria não está registrada",
    val groups: Array<KClass<Any>> = [],
    val payload: Array<KClass<Payload>> = []
)