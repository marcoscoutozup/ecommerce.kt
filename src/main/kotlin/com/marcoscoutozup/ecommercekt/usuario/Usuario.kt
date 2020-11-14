package com.marcoscoutozup.ecommercekt.usuario

import org.hibernate.annotations.CreationTimestamp
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import java.time.LocalDateTime
import java.util.*
import javax.persistence.*
import javax.validation.constraints.Email
import javax.validation.constraints.NotBlank
import javax.validation.constraints.Size

@Entity
@NamedQuery(name = "findUsuarioByEmail", query = "select u from Usuario u where email = :email")
class Usuario {

    @Id
    @GeneratedValue(generator = "uuid")
    val id: UUID? = null

    @NotBlank
    @Email
    @Column(unique = true)
    val email: String

    @NotBlank
    @Size(min=6)
    val senha: String

    @CreationTimestamp
    val criadoEm: LocalDateTime? = null

    constructor(email: String, senha: String) {
        this.email = email
        this.senha = criptografarSenha(senha)
    }

    fun criptografarSenha(senha: String): String = BCryptPasswordEncoder().encode(senha)

}