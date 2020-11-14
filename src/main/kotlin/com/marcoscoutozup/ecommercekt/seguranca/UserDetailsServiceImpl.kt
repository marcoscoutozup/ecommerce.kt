package com.marcoscoutozup.ecommercekt.seguranca

import com.marcoscoutozup.ecommercekt.usuario.Usuario
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.security.core.userdetails.User
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.core.userdetails.UsernameNotFoundException
import org.springframework.stereotype.Service
import org.springframework.util.Assert
import javax.persistence.EntityManager

@Service
class UserDetailsServiceImpl(val entityManager: EntityManager): UserDetailsService  {


    val log: Logger = LoggerFactory.getLogger(UserDetailsServiceImpl::class.java)

    override fun loadUserByUsername(email: String?): UserDetails {
        val usuario = entityManager.createNamedQuery("findUsuarioByEmail", Usuario::class.java)
                        .setParameter("email", email)
                        .resultList.stream().findFirst()

        if(usuario.isEmpty()){
            log.warn("Usuário não encontrado {}", usuario)
            throw UsernameNotFoundException("Usuário não encontrado")
        }

        return toUserDetails(usuario.get())
    }

    fun toUserDetails(usuario: Usuario): UserDetails =
            User.builder()
                    .username(usuario.email)
                    .password(usuario.senha)
                    .authorities(arrayListOf())
                    .build()
}