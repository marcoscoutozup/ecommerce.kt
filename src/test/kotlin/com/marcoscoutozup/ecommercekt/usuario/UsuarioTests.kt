package com.marcoscoutozup.ecommercekt.usuario

import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder

class UsuarioTests {

    @Test
    @DisplayName("Deve criptografar senha")
    fun deveCriptografarSenha(){
        val senha = String()
        val senhaCriptografada = Usuario(String(), String()).criptografarSenha(senha)
        assertTrue(BCryptPasswordEncoder().matches(senha, senhaCriptografada))
    }

}