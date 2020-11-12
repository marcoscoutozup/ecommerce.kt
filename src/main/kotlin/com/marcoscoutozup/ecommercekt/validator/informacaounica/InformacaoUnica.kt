package com.marcoscoutozup.ecommercekt.validator.informacaounica

import com.marcoscoutozup.ecommercekt.categoria.Categoria
import java.lang.reflect.Type
import javax.validation.Constraint
import javax.validation.Payload
import kotlin.annotation.AnnotationRetention.RUNTIME
import kotlin.annotation.AnnotationTarget.FIELD
import kotlin.reflect.KClass

@Target(FIELD)
@Retention(RUNTIME)
@MustBeDocumented
@Constraint(validatedBy = arrayOf(InformacaoUnicaValidator::class))
annotation class InformacaoUnica (
    val message: String = "A informação não é única",
    val groups: Array<KClass<Any>> = [],
    val payload: Array<KClass<Payload>> = [],
    val classe: KClass<*>,
    val campo: String
)