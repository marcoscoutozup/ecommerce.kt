package com.marcoscoutozup.ecommercekt.validator

import com.marcoscoutozup.ecommercekt.usuario.Usuario
import javax.persistence.EntityManager
import javax.validation.ConstraintValidator
import javax.validation.ConstraintValidatorContext

class EmailUnicoValidator(val entityManager: EntityManager): ConstraintValidator<EmailUnico, String>{

    override fun isValid(email: String?, context: ConstraintValidatorContext?): Boolean {
        if(email == null) {
            return true;
        }

        var query = entityManager.createNamedQuery("findUsuarioByEmail", Usuario::class.java)
        query.setParameter("email", email)
        return query.resultList.isEmpty()
    }

}