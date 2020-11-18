package com.marcoscoutozup.ecommercekt.produto

import com.marcoscoutozup.ecommercekt.caracteristica.Caracteristica
import com.marcoscoutozup.ecommercekt.caracteristica.CaracteristicaRequest
import com.marcoscoutozup.ecommercekt.categoria.Categoria
import com.marcoscoutozup.ecommercekt.usuario.Usuario
import com.marcoscoutozup.ecommercekt.validator.categoriaexistente.CategoriaExistente
import com.marcoscoutozup.ecommercekt.validator.registrosminimos.RegistrosMinimos
import java.math.BigDecimal
import java.util.*
import java.util.stream.Collectors
import javax.persistence.Column
import javax.persistence.EntityManager
import javax.validation.Valid
import javax.validation.constraints.*

class ProdutoRequest {

    @NotBlank
    lateinit var nome: String

    @NotNull
    @Positive
    lateinit var valor: BigDecimal

    @NotNull
    @PositiveOrZero
    var quantidade: Int = 0

    @NotBlank
    @Size(max = 1000)
    lateinit var descricao: String

    @NotEmpty
    @RegistrosMinimos
    @Valid
    lateinit var caracteristicas: Set<CaracteristicaRequest>

    @NotNull
    @CategoriaExistente
    lateinit var categoria: UUID

    fun toProduto(vendedor: Usuario, entityManager: EntityManager): Produto {
        val categoria = entityManager
                .find(Categoria::class.java, this.categoria)

        return Produto(this.nome,
                this.valor,
                this.quantidade,
                this.descricao,
                converterListaDeDTOParaCaracteristicas(this.caracteristicas),
                categoria,
                vendedor)
    }

    fun converterListaDeDTOParaCaracteristicas(listaDeDTO: Set<CaracteristicaRequest>): Set<Caracteristica> =
            listaDeDTO
                    .stream()
                    .map { caracteristica -> Caracteristica(caracteristica.titulo, caracteristica.descricao) }
                    .collect(Collectors.toSet())
}