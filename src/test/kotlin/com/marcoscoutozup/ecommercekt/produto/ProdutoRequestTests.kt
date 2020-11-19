package com.marcoscoutozup.ecommercekt.produto

import com.marcoscoutozup.ecommercekt.caracteristica.CaracteristicaRequest
import com.marcoscoutozup.ecommercekt.categoria.Categoria
import com.marcoscoutozup.ecommercekt.usuario.Usuario
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.mockito.Mock
import org.mockito.Mockito.*
import org.mockito.MockitoAnnotations.initMocks
import java.math.BigDecimal
import java.util.*
import javax.persistence.EntityManager

class ProdutoRequestTests {

    @Mock
    lateinit var entityManager: EntityManager

    @Mock
    lateinit var usuario: Usuario

    @Mock
    lateinit var categoria: Categoria

    @Mock
    lateinit var caracteristicas: Set<CaracteristicaRequest>

    @BeforeEach
    fun setup() = initMocks(this)

    @Test
    @DisplayName("Deve converter produto request para produto")
    fun deveConverterProdutoRequestParaProduto(){
        val request = ProdutoRequest()
        request.caracteristicas = this.caracteristicas
        request.categoria = UUID.randomUUID()
        request.descricao = String()
        request.nome = String()
        request.quantidade = Int.MIN_VALUE
        request.valor = BigDecimal.ONE
        `when`(entityManager.find(eq(Categoria::class.java), any())).thenReturn(categoria)
         val produto = request.toProduto(usuario, entityManager)
         assertTrue(produto is Produto)
    }

    @Test
    @DisplayName("Deve converter lista de dto para caracteristicas")
    fun deveConverterListaDeDTOParaCaracteristicas(){
        val request = CaracteristicaRequest()
        request.titulo = String()
        request.descricao = String()
        val caracteristicas = ProdutoRequest().converterListaDeDTOParaCaracteristicas(setOf(request))
        assertTrue(caracteristicas.isNotEmpty())
    }

}