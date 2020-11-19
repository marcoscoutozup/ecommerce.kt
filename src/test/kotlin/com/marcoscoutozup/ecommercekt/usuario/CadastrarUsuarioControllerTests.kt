package com.marcoscoutozup.ecommercekt.usuario

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

class CadastrarUsuarioControllerTests {

    @Mock
    lateinit var entityManager: EntityManager

    @Mock
    lateinit var usuarioRequest: UsuarioRequest

    @Mock
    lateinit var usuario: Usuario

    @Mock
    lateinit var controller: CadastrarUsuarioController

    @BeforeEach
    fun setup() {
        initMocks(this)
        controller = CadastrarUsuarioController(entityManager)
    }

    @Test
    @DisplayName("Deve cadastrar usuário")
    fun deveCadastrarUsuario() {
        `when`(usuarioRequest.toUsuario()).thenReturn(usuario)
        val uuid = UUID.randomUUID()
        `when`(usuario.id).thenReturn(uuid)
        val response = controller.cadastrarUsuario(usuarioRequest, UriComponentsBuilder.newInstance())
        assertEquals(HttpStatus.CREATED, response.statusCode)
        assertNotNull(response.headers["Location"])
    }

}