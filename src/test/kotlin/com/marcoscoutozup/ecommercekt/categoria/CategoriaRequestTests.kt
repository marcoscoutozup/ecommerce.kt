package com.marcoscoutozup.ecommercekt.categoria

import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.Mockito.`when`
import org.mockito.Mockito.any
import org.mockito.MockitoAnnotations.initMocks
import java.util.*
import javax.persistence.EntityManager

class CategoriaRequestTests {

    @Mock
    lateinit var entityManager: EntityManager

    @Mock
    lateinit var categoria: Categoria

    @BeforeEach
    fun setup() = initMocks(this)

    @Test
    @DisplayName("Deve converter categoria request para categoria sem supercategoria")
    fun deveConverterCategoriaRequestParaCategoriaSemSuperCategoria() {
        val request = CategoriaRequest()
        request.nome = String()
        val categoria = request.toCategoria(entityManager)
        assertTrue(categoria is Categoria)
    }

    @Test
    @DisplayName("Deve converter categoria request para categoria")
    fun deveConverterCategoriaRequestParaCategoriaComCategoria() {
        val request = CategoriaRequest()
        request.nome = String()
        request.superCategoria = UUID.randomUUID()
        `when`(entityManager.find(Mockito.eq(Categoria::class.java), any())).thenReturn(categoria)
        val categoria = request.toCategoria(entityManager)
        assertTrue(categoria is Categoria)
    }
}