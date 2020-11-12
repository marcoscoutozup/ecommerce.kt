package com.marcoscoutozup.ecommercekt.usuario

import com.marcoscoutozup.ecommercekt.validator.EmailUnico
import javax.validation.constraints.Email
import javax.validation.constraints.Min
import javax.validation.constraints.NotBlank

class UsuarioRequest (

        @NotBlank
        @Email
        @EmailUnico
        val email: String,

        @NotBlank
        @Min(6)
        val senha: String
) {
                        //1
    fun toUsuario(): Usuario = Usuario(this.email, this.senha)

}
