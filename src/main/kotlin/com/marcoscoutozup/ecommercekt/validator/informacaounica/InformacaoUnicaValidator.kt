package com.marcoscoutozup.ecommercekt.validator.informacaounica

import javax.persistence.EntityManager
import javax.validation.ConstraintValidator
import javax.validation.ConstraintValidatorContext

class InformacaoUnicaValidator(val entityManager: EntityManager): ConstraintValidator<InformacaoUnica, String>{

    lateinit var classe: String
    lateinit var campo: String

    override fun initialize(constraintAnnotation: InformacaoUnica?) {
        this.classe = constraintAnnotation!!.classe.simpleName.toString()
        this.campo = constraintAnnotation!!.campo
    }

    override fun isValid(valor: String?, context: ConstraintValidatorContext?): Boolean =
            valor == null || entityManager
                                .createQuery("SELECT 1 FROM ${this.classe} c WHERE c.${this.campo} = :valor")
                                .setParameter("valor", valor)
                                .resultList
                                .isEmpty()
}