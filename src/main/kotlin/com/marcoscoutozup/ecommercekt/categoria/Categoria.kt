package com.marcoscoutozup.ecommercekt.categoria

import java.util.*
import javax.persistence.*
import javax.validation.constraints.NotBlank

@Entity
class Categoria(@NotBlank @Column(unique = true) val nome: String) {

    @Id
    @GeneratedValue(generator = "uuid")
    val id: UUID? = null

    @OneToOne
    var categoria: Categoria? = null

    fun associarSuperCategoria(categoria: Categoria){
        this.categoria = categoria
    }

}



