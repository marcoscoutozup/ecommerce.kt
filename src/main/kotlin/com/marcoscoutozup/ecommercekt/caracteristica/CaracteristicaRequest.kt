package com.marcoscoutozup.ecommercekt.caracteristica

import javax.validation.constraints.NotBlank

class CaracteristicaRequest {

    @NotBlank
    lateinit var titulo: String

    @NotBlank
    lateinit var descricao: String

}