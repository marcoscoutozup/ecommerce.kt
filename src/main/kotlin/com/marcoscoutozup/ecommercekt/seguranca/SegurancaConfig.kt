package com.marcoscoutozup.ecommercekt.seguranca

import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.builders.WebSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter
import org.springframework.security.config.http.SessionCreationPolicy.STATELESS
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder

@EnableWebSecurity
class SegurancaConfig(val userDetailsService: UserDetailsService,
                      val jwtUtils: JwtUtils): WebSecurityConfigurerAdapter() {

    val ENDPOINTS_H2 = arrayOf("/h2-console", "/h2-console/**")
    val ENDPOINTS_PUBLICOS = arrayOf("/usuarios")


    override fun configure(web: WebSecurity) {
        web.ignoring().antMatchers(*ENDPOINTS_H2)
    }

    override fun configure(http: HttpSecurity) {
        http.csrf().disable()
                .authorizeRequests()
                .antMatchers(*ENDPOINTS_PUBLICOS).permitAll()
                .anyRequest().authenticated()
                .and()
                .addFilter(FiltroDeAutenticacaoJwt(authenticationManager(), this.jwtUtils))
                .addFilter(FiltroDeAutorizacaoJwt(userDetailsService, authenticationManager(), this.jwtUtils))
                .sessionManagement().sessionCreationPolicy(STATELESS)
    }

    override fun configure(auth: AuthenticationManagerBuilder) {
        auth.userDetailsService(userDetailsService).passwordEncoder(BCryptPasswordEncoder())
    }
}