package com.marcoscoutozup.ecommercekt.produto

import com.marcoscoutozup.ecommercekt.seguranca.JwtUtils
import com.marcoscoutozup.ecommercekt.usuario.Usuario
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.mockito.Mock
import org.mockito.Mockito.*
import org.mockito.MockitoAnnotations.initMocks
import org.springframework.http.HttpStatus
import org.springframework.web.util.UriComponentsBuilder
import java.util.*
import javax.persistence.EntityManager
import javax.persistence.TypedQuery

class CadastrarProdutoControllerTests {

    @Mock
    lateinit var entityManager: EntityManager

    @Mock
    lateinit var produtoRequest: ProdutoRequest

    @Mock
    lateinit var produto: Produto

    @Mock
    lateinit var controller: CadastrarProdutoController

    @Mock
    lateinit var jwtUtils: JwtUtils

    @Mock
    lateinit var usuario: Usuario

    @Mock
    lateinit var query: TypedQuery<Usuario>

    @BeforeEach
    fun setup() {
        initMocks(this)
        controller = CadastrarProdutoController(entityManager, jwtUtils)
    }

    @Test
    @DisplayName("Não deve cadastrar produto se vendedor não existir")
    fun naoDeveCadastrarProdutoSeVendedorNaoExistir() {
        `when`(entityManager.createNamedQuery(anyString(), eq(Usuario::class.java))).thenReturn(query)
        `when`(query.setParameter(anyString(), any())).thenReturn(query)
        `when`(query.resultList).thenReturn(emptyList())
        `when`(jwtUtils.capturarEmailDoUsuarioLogado(anyString())).thenReturn(String())
        val response = controller.cadastrarNovoProduto(produtoRequest, retornaJwt(), UriComponentsBuilder.newInstance())
        assertEquals(HttpStatus.NOT_FOUND, response.statusCode)
        assertTrue(response.body is HashMap<*, *>)
    }

    @Test
    @DisplayName("Deve cadastrar produto")
    fun deveCadastrarUsuario() {
        `when`(produtoRequest.toProduto(usuario, entityManager)).thenReturn(produto)
        `when`(entityManager.createNamedQuery(anyString(), eq(Usuario::class.java))).thenReturn(query)
        `when`(query.setParameter(anyString(), any())).thenReturn(query)
        `when`(query.resultList).thenReturn(listOf(usuario))
        val uuid = UUID.randomUUID()
        `when`(produto.id).thenReturn(uuid)
        `when`(jwtUtils.capturarEmailDoUsuarioLogado(anyString())).thenReturn(String())
        val response = controller.cadastrarNovoProduto(produtoRequest, retornaJwt(), UriComponentsBuilder.newInstance())
        assertEquals(HttpStatus.CREATED, response.statusCode)
        assertNotNull(response.headers["Location"])
    }

    fun retornaJwt() = "Bearer eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJzdWIiOiIxMjM0NTY3ODkwIiwibmFtZSI6IkpvaG4gRG9lIiwiaWF0IjoxNTE2MjM5MDIyfQ.SflKxwRJSMeKKF2QT4fwpMeJf36POk6yJV_adQssw5c"
}