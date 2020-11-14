package com.marcoscoutozup.ecommercekt.usuario

import com.marcoscoutozup.ecommercekt.validator.informacaounica.InformacaoUnica
import javax.validation.constraints.Email
import javax.validation.constraints.NotBlank
import javax.validation.constraints.Size

class UsuarioRequest {

    @NotBlank
    @Email
    @InformacaoUnica(campo = "email", classe = Usuario::class)
    lateinit var email: String

    @NotBlank
    @Size(min=6)
    lateinit var senha: String

                        //1
    fun toUsuario(): Usuario = Usuario(this.email, this.senha)

}
