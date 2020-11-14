package com.marcoscoutozup.ecommercekt.seguranca

import com.fasterxml.jackson.databind.ObjectMapper
import org.springframework.security.authentication.AuthenticationCredentialsNotFoundException
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.Authentication
import org.springframework.security.core.AuthenticationException
import org.springframework.security.core.userdetails.User
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter
import java.io.IOException
import javax.servlet.FilterChain
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

class FiltroDeAutenticacaoJwt(val manager: AuthenticationManager, val jwtUtil: JwtUtils):
        UsernamePasswordAuthenticationFilter() {

    override fun attemptAuthentication(request: HttpServletRequest?, response: HttpServletResponse?): Authentication {
        try {
            val usuarioDTO = ObjectMapper().readValue(request?.inputStream, UsuarioDTO::class.java)
            val authToken = UsernamePasswordAuthenticationToken(usuarioDTO.email, usuarioDTO.senha)
            return this.manager.authenticate(authToken)
        } catch (e: IOException) {
            throw AuthenticationCredentialsNotFoundException("Credenciais não encontradas")
        }
    }

    override fun successfulAuthentication(request: HttpServletRequest?, response: HttpServletResponse?, chain: FilterChain?, authResult: Authentication?) {
        val username = (authResult?.principal as User).username
        val token = this.jwtUtil.gerarToken(username)
        response!!.status = 200
        response!!.addHeader("Authorization", token)
    }

    override fun unsuccessfulAuthentication(request: HttpServletRequest?, response: HttpServletResponse?, failed: AuthenticationException?) {
        response!!.status = 401
        response!!.contentType = "application/json"
        response!!.characterEncoding = "UTF-8"
        response!!.writer?.write("{ \"message\":\"${failed?.message}\" }")
    }
}