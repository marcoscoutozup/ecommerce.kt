package com.marcoscoutozup.ecommercekt.validator.categoriaexistente

import com.marcoscoutozup.ecommercekt.categoria.Categoria
import java.util.*
import javax.persistence.EntityManager
import javax.validation.ConstraintValidator
import javax.validation.ConstraintValidatorContext

class CategoriaExistenteValidator(val entityManager: EntityManager): ConstraintValidator<CategoriaExistente, UUID> {

    override fun isValid(id: UUID?, context: ConstraintValidatorContext?): Boolean =
        id == null || entityManager.find(Categoria::class.java, id) != null

}