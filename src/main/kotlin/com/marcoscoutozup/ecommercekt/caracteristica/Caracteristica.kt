package com.marcoscoutozup.ecommercekt.caracteristica

import javax.persistence.Embeddable
import javax.validation.constraints.NotBlank

@Embeddable
class Caracteristica(@NotBlank val titulo: String, @NotBlank val descricao: String)