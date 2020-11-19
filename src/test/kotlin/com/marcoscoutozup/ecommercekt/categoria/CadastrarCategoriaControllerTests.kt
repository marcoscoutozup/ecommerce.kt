package com.marcoscoutozup.ecommercekt.categoria

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertNotNull
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.MockitoAnnotations.initMocks
import org.springframework.http.HttpStatus
import org.springframework.web.util.UriComponentsBuilder
import java.util.*
import javax.persistence.EntityManager

class CadastrarCategoriaControllerTests {

    @Mock
    lateinit var entityManager: EntityManager

    @Mock
    lateinit var categoriaRequest: CategoriaRequest

    @Mock
    lateinit var categoria: Categoria

    @Mock
    lateinit var controller: CadastrarCategoriaController

    @BeforeEach
    fun setup(){
        initMocks(this)
        controller = CadastrarCategoriaController(entityManager)
    }

    @Test
    @DisplayName("Deve cadastrar categoria")
    fun deveCadastrarCategoria() {
        `when`(categoriaRequest.toCategoria(entityManager)).thenReturn(categoria)
        val uuid = UUID.randomUUID()
        `when`(categoria.id).thenReturn(uuid)
        val response = controller.cadastrarCategoria(categoriaRequest, UriComponentsBuilder.newInstance())
        assertEquals(HttpStatus.CREATED, response.statusCode)
        assertNotNull(response.headers["Location"])
    }

}