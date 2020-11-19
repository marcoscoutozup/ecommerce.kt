package com.marcoscoutozup.ecommercekt.categoria

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.mockito.Mock
import org.mockito.MockitoAnnotations.initMocks

class CategoriaTests {

    @Mock
    lateinit var categoria: Categoria

    @BeforeEach
    fun setup() = initMocks(this)

    @Test
    @DisplayName("Deve associar categoria")
    fun deveAssociarCategoria(){
        var categoria = Categoria(String())
        categoria.associarSuperCategoria(this.categoria)
        assertEquals(categoria.categoria, this.categoria)
    }
}