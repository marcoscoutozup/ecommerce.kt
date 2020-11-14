package com.marcoscoutozup.ecommercekt.seguranca

import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter
import javax.servlet.FilterChain
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

class FiltroDeAutorizacaoJwt(val userDetailsService: UserDetailsService,
                             val manager: AuthenticationManager,
                             val jwtUtils: JwtUtils
            ): BasicAuthenticationFilter(manager) {

    override fun doFilterInternal(request: HttpServletRequest, response: HttpServletResponse, chain: FilterChain) {
        if(verificarCabecalhoAuthorization(request)){
            val token = request.getHeader("Authorization").substring(7)
            if(jwtUtils.verificarSeTokenEValido(token)){
                val email = jwtUtils.capturarEmailDoUsuarioLogado(token)
                val user = userDetailsService.loadUserByUsername(email)
                val auth = UsernamePasswordAuthenticationToken(user, null, user.authorities)
                SecurityContextHolder.getContext().authentication = auth
            }
        }
        chain.doFilter(request, response)
    }

    fun verificarCabecalhoAuthorization(request: HttpServletRequest): Boolean {
        val header = request.getHeader("Authorization")
        return header != null && header.startsWith("Bearer ")
    }
}