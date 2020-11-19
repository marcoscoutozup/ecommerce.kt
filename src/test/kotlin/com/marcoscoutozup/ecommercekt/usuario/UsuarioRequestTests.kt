package com.marcoscoutozup.ecommercekt.usuario

import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test

class UsuarioRequestTests {

    @Test
    @DisplayName("Deve converter request para usuário")
    fun deveConverterRequestParaUsuario(){
        val request = UsuarioRequest()
        request.email = String()
        request.senha = String()
        val usuario = request.toUsuario()
        assertTrue(usuario is Usuario)
    }

}