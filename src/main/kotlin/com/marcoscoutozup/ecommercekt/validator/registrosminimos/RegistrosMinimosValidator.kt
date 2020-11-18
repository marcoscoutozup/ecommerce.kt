package com.marcoscoutozup.ecommercekt.validator.registrosminimos

import javax.validation.ConstraintValidator
import javax.validation.ConstraintValidatorContext

class RegistrosMinimosValidator : ConstraintValidator<RegistrosMinimos, Set<*>> {

    override fun isValid(colecao: Set<*>?, context: ConstraintValidatorContext?): Boolean =
         !colecao.isNullOrEmpty() && colecao!!.size > 2

}