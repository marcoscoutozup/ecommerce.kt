package com.marcoscoutozup.ecommercekt.usuario

import com.marcoscoutozup.ecommercekt.validator.informacaounica.InformacaoUnica
import javax.validation.constraints.Email
import javax.validation.constraints.Min
import javax.validation.constraints.NotBlank

class UsuarioRequest (

        @NotBlank
        @Email
        @InformacaoUnica(campo = "email", classe = Usuario::class)
        val email: String,

        @NotBlank
        @Min(6)
        val senha: String
) {
                        //1
    fun toUsuario(): Usuario = Usuario(this.email, this.senha)

}
