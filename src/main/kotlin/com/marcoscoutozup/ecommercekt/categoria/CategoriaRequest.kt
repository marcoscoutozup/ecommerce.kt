package com.marcoscoutozup.ecommercekt.categoria

import com.marcoscoutozup.ecommercekt.validator.categoriaexistente.CategoriaExistente
import com.marcoscoutozup.ecommercekt.validator.informacaounica.InformacaoUnica
import java.util.*
import javax.persistence.EntityManager
import javax.validation.constraints.NotBlank

class CategoriaRequest {


    @NotBlank
    @InformacaoUnica(campo = "nome", classe = Categoria::class)
    lateinit var nome: String

    @CategoriaExistente
    var superCategoria: UUID? = null

                                                    //1
    fun toCategoria(entityManager: EntityManager): Categoria{

        var categoria = Categoria(this.nome)

        if(this.superCategoria == null ){
            return categoria
        }

        val categoriaProcurada =
                entityManager.find(Categoria::class.java, this.superCategoria)

        categoria.associarSuperCategoria(categoriaProcurada)
        return categoria
    }
}